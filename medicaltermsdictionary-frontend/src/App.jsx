import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Home from './pages/Home';

const App = () => (
  <Router>
    <div className="min-h-screen flex flex-col">
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

      <main className="container mx-auto flex-grow py-8">
        <Routes>
          <Route path="/" element={<Home />} />
        </Routes>
      </main>

      <footer className="fixed-footer bg-gray-100 text-gray-700 py-4 text-center shadow-inner">
        <p className="text-lg">© 2024 MEDTERMS - Medical Terms Dictionary. All rights reserved.</p>
      </footer>
    </div>
  </Router>
);

export default App;
