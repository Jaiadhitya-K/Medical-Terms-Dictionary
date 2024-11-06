import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import { fetchTermDetails, fetchRelatedTerms, fetchFavorites } from '../services/api';
import FavoriteButton from '../components/FavoriteButton';

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

  return termDetails ? (
    <div className="container mx-auto p-4">
      <h1 className="text-3xl font-bold mb-2">{termDetails.term}</h1>
      <p className="mt-2 text-lg">{termDetails.definition}</p>

      {/* Toggle Favorite Button */}
      <FavoriteButton termId={termDetails.id} isFavorite={isFavorite} setIsFavorite={setIsFavorite} />

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
