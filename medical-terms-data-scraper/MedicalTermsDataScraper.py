# This script scrapes medical terms and their definitions from the Harvard Medical School website
# and stores them in a PostgreSQL database.
#
# Prerequisites:
# 1. Set up PostgreSQL on your local machine.
# 2. Install the required Python packages by running:
#    pip install requests beautifulsoup4 sqlalchemy psycopg2-binary
# 3. Replace the USERNAME and PASSWORD variables with your PostgreSQL credentials.

import requests
from bs4 import BeautifulSoup
from sqlalchemy import create_engine, Column, String, Integer, text
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

# Database credentials (replace with your own)
USERNAME = "postgres"
PASSWORD = "lollollol"  # Replace with your PostgreSQL password
DATABASE_NAME = "medical_terms_db"

# Connect to the PostgreSQL server to create the database (without selecting a specific database)
SERVER_URL = f"postgresql://{USERNAME}:{PASSWORD}@localhost/postgres"
engine = create_engine(SERVER_URL, echo=True)

# Create the database if it doesn't already exist
with engine.connect().execution_options(isolation_level="AUTOCOMMIT") as connection:
    connection.execute(text(f"CREATE DATABASE {DATABASE_NAME}"))

# Switch to the newly created database for further operations
DATABASE_URL = f"postgresql://{USERNAME}:{PASSWORD}@localhost/{DATABASE_NAME}"
engine = create_engine(DATABASE_URL)
Base = declarative_base()


class MedicalTerm(Base):
    __tablename__ = "medical_terms"
    id = Column(Integer, primary_key=True)
    term = Column(String, nullable=False)
    definition = Column(String, nullable=False)


Base.metadata.create_all(engine)
Session = sessionmaker(bind=engine)
session = Session()


def scrape_terms_from_url(url):
    response = requests.get(url)
    soup = BeautifulSoup(response.text, "html.parser")
    try:
        body = soup.body
        print("Accessed body tag snippet:", str(body)[:200])

        app_div = body.find("div", id="app")
        print("Accessed div with id='app' snippet:", str(app_div)[:200])

        main_tag = app_div.find("main")
        print("Accessed main tag snippet:", str(main_tag)[:200])

        article_tag = main_tag.find("article")
        if not article_tag:
            raise ValueError("Article tag within main not found")
        print("Accessed article tag snippet:", str(article_tag)[:200])

        content_div = article_tag.find("div", class_="content-repository-content")
        if not content_div:
            raise ValueError(
                "Content div with class 'content-repository-content' not found"
            )
        print("Accessed content div snippet:", str(content_div)[:200])

        first_h2 = content_div.find("h2")
        paragraphs = first_h2.find_all_next(["p", "h2", "hr"]) if first_h2 else []

        terms_data = []
        parsing_z_terms = False
        parsing_c_terms = False
        parsing_i_terms = False
        parsing_p_terms = False

        for tag in paragraphs:
            if tag.name == "p":
                strong_tag = tag.find("strong")
                if strong_tag:
                    term = strong_tag.get_text(strip=True).rstrip(":")
                    definition = (
                        tag.get_text(strip=True).replace(term, "").lstrip(":").strip()
                    )

                    if term.lower().startswith("c"):
                        parsing_c_terms = True
                    elif parsing_c_terms:
                        break
                    elif term.lower().startswith("i"):
                        parsing_i_terms = True
                    elif parsing_i_terms:
                        break
                    if term.lower().startswith("p"):
                        parsing_p_terms = True
                    elif parsing_p_terms:
                        break
                    elif term.lower().startswith("z"):
                        parsing_z_terms = True
                    elif parsing_z_terms:
                        print(
                            f"Reached non-Z term '{term}' after Z terms. Stopping parsing."
                        )
                        break

                    terms_data.append(MedicalTerm(term=term, definition=definition))
        session.bulk_save_objects(terms_data)
        session.commit()
        print(f"Inserted {len(terms_data)} terms from {url}")

    except ValueError as e:
        print(f"Error processing {url}: {e}")


urls = [
    "https://www.health.harvard.edu/a-through-c",
    "https://www.health.harvard.edu/d-through-i",
    "https://www.health.harvard.edu/j-through-p",
    "https://www.health.harvard.edu/q-through-z",
]
for url in urls:
    scrape_terms_from_url(url)

terms = session.query(MedicalTerm).all()
for term in terms:
    print(f"Term: {term.term}\nDefinition: {term.definition}\n")

# Close the session
session.close()
print("Database setup and data population complete!")
