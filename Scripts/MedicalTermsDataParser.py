import requests
from bs4 import BeautifulSoup
from sqlalchemy import create_engine, Column, String, Integer
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

DATABASE_URL = "postgresql://postgres:lollollol@localhost/medical_terms_db"
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
