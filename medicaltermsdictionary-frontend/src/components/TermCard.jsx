import React from 'react';
import { Link } from 'react-router-dom';

const TermCard = ({ term }) => (
  <Link to={`/terms/${term.term}`} className="card bg-white rounded-xl p-6 shadow-lg w-full min-h-[150px] hover:shadow-2xl transform transition-transform duration-200 hover:scale-105">
    <h2 className="text-2xl font-semibold text-teal-600">{term.term}</h2>
    <p className="text-gray-600 mt-2 text-lg line-clamp-3">{term.definition}</p>
  </Link>
);

export default TermCard;
