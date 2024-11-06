import React, { useEffect, useState } from 'react';
import { fetchAllTerms, searchTerms } from '../services/api';
import SearchBar from '../components/SearchBar';
import TermCard from '../components/TermCard';

const Home = () => {
  const [terms, setTerms] = useState([]);
  const [query, setQuery] = useState("");

  useEffect(() => {
    if (query) {
      searchTerms(query).then(res => setTerms(res.data));
    } else {
      fetchAllTerms().then(res => setTerms(res.data));
    }
  }, [query]);

  return (
    <div className="container mx-auto p-6">
      <SearchBar onSearch={setQuery} />
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {terms.map(term => (
          <TermCard key={term.id} term={term} />
        ))}
      </div>
    </div>
  );
};

export default Home;