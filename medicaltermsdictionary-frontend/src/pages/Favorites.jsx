import React, { useEffect, useState } from 'react';
import TermCard from '../components/TermCard';

const Favorites = () => {
  const [favorites, setFavorites] = useState([]);

  useEffect(() => {
    fetchFavorites().then(res => setFavorites(res.data));
  }, []);


  return (
    <div className="container mx-auto p-6">
      <h1 className="text-2xl font-bold mb-4">Favorites</h1>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {favorites.map(term => (
            <TermCard term={term} />
        ))}
      </div>
    </div>
  );
};

export default Favorites;
