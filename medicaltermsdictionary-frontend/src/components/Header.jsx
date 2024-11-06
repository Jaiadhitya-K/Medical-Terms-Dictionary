import React from 'react';
import { Link } from 'react-router-dom';

const Header = () => (
  <header className="bg-teal-600 text-white shadow p-4">
    <nav className="container mx-auto flex justify-between items-center">
      <Link to="/" className="flex items-center gap-2">
        <img src="/src/assets/logo.png" alt="Medical Terms Dictionary Logo" className="h-14 w-15" />
      </Link>
      <div>
        <Link to="/" className="px-4 hover:text-gray-200">Home</Link>
        <Link to="/favorites" className="px-4 hover:text-gray-200">Favorites</Link>
      </div>
    </nav>
  </header>
);

export default Header;
