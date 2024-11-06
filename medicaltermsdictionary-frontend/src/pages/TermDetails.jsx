import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import { fetchTermDetails, fetchRelatedTerms, addToFavorites, removeFromFavorites, fetchFavorites } from '../services/api';

const TermDetails = () => {
  const { term } = useParams();
  const [termDetails, setTermDetails] = useState(null);
  const [relatedTerms, setRelatedTerms] = useState([]);
  const [isFavorite, setIsFavorite] = useState(false);

  useEffect(() => {
    fetchTermDetails(term).then(res => setTermDetails(res.data));
    fetchRelatedTerms(term).then(res => setRelatedTerms(res.data.slice(0, 10)));

    fetchFavorites().then(res => {
      const isFav = res.data.some(fav => fav.term === term);
      setIsFavorite(isFav);
    });
  }, [term]);

  const handleAddFavorite = () => {
    addToFavorites(termDetails.id).then(() => {
      alert("Added to favorites!");
      setIsFavorite(true); 
    });
  };

  const handleRemoveFavorite = () => {
    removeFromFavorites(termDetails.id).then(() => {
      alert("Removed from favorites!");
      setIsFavorite(false);
    });
  };

  return termDetails ? (
    <div className="container mx-auto p-4">
      <h1 className="text-3xl font-bold mb-2">{termDetails.term}</h1>
      <p className="mt-2 text-lg">{termDetails.definition}</p>

      {/* Toggle Favorite Button */}
      {isFavorite ? (
        <button
          className="button-remove mt-6"
          onClick={handleRemoveFavorite}
        >
          Remove from Favorites
        </button>
      ) : (
        <button
          className="button-primary mt-6"
          onClick={handleAddFavorite}
        >
          Add to Favorites
        </button>
      )}

      <h2 className="mt-6 text-2xl font-semibold">Related Terms</h2>
      <ul className="list-disc ml-6 mt-2 space-y-1 text-lg">
        {relatedTerms.map(related => (
          <li key={related.id}>
            <Link to={`/terms/${related.term}`} className="text-black-600 hover:underline">
              {related.term}
            </Link>
          </li>
        ))}
      </ul>
    </div>
  ) : null;
};

export default TermDetails;

