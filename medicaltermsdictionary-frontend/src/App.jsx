import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import TermDetails from './pages/TermDetails';
import Favorites from './pages/Favorites';
import Header from './components/Header';
import Footer from './components/Footer';

const App = () => (
  <Router>
    <div className="min-h-screen flex flex-col">
      <Header />
      <main className="container mx-auto flex-grow py-8">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/terms/:term" element={<TermDetails />} />
          <Route path="/favorites" element={<Favorites />} />
        </Routes>
      </main>
      <Footer />
    </div>
  </Router>
);

export default App;
