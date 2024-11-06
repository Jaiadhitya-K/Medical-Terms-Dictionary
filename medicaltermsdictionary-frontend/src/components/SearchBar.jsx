import React from 'react';

const SearchBar = ({ onSearch }) => (
  <div className="mb-6">
    <input
      type="text"
      className="search-input w-full p-4 rounded-full text-lg placeholder-gray-500 focus:outline-none border-2 border-gray-200"
      placeholder="Search medical terms..."
      onChange={(e) => onSearch(e.target.value)}
    />
  </div>
);

export default SearchBar;
