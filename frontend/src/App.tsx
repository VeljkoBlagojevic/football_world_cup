import React from 'react';
import {BrowserRouter as Router, Routes, Route, Link} from 'react-router-dom';
import StadioniComponent from "./components/Stadioni/StadioniComponent";
import GrupeComponent from "./components/Grupe/GrupeComponent";
import LandingPage from "./components/Landing/LandingPage";
import ZakazivanjeComponent from "./components/Utakmice/ZakazivanjeComponent";
import UtakmiceComponent from "./components/Utakmice/UtakmiceComponent";
import './App.css';

function App() {
    return (
        <Router>
            <div className="app-container">
                <nav className="navbar">
                    <ul className="nav-menu">
                        <li className="nav-item">
                            <Link to="/" className="nav-link">Početna</Link>
                        </li>
                        <li className="nav-item">
                            <Link to="/stadioni" className="nav-link">Stadioni</Link>
                        </li>
                        <li className="nav-item">
                            <Link to="/grupe" className="nav-link">Grupe</Link>
                        </li>
                        <li className="nav-item">
                            <Link to="/zakazivanje" className="nav-link">Zakazivanje</Link>
                        </li>
                        <li className="nav-item">
                            <Link to="/utakmice" className="nav-link">Utakmice</Link>
                        </li>
                    </ul>
                </nav>

                <Routes>
                    <Route path="/" element={<LandingPage/>}/>
                    <Route path="/stadioni" element={<StadioniComponent/>}/>
                    <Route path="/grupe" element={<GrupeComponent/>}/>
                    <Route path="/zakazivanje" element={<ZakazivanjeComponent/>}/>
                    <Route path="/utakmice" element={<UtakmiceComponent/>}/>
                </Routes>
            </div>
        </Router>
    );
}

export default App;
