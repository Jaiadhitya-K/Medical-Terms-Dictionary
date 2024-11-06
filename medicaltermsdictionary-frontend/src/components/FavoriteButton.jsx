import React, { useState } from 'react';
import { addToFavorites, removeFromFavorites } from '../services/api';

const FavoriteButton = ({ termId, isFavorite, setIsFavorite }) => {
  const [showAnimation, setShowAnimation] = useState(false);

  const handleAddFavorite = () => {
    addToFavorites(termId).then(() => {
      setShowAnimation(true);
      setIsFavorite(true);
      setTimeout(() => setShowAnimation(false), 1500);
    });
  };

  const handleRemoveFavorite = () => {
    removeFromFavorites(termId).then(() => {
      setShowAnimation(true);
      setIsFavorite(false);
      setTimeout(() => setShowAnimation(false), 1500);
    });
  };

  return (
    <div className="relative">
      {showAnimation && (
        <div className="absolute top-0 right-0 p-2 bg-green-200 text-green-800 rounded-full animate-bounce z-50">
          ✔️
        </div>
      )}
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
    </div>
  );
};

export default FavoriteButton;
