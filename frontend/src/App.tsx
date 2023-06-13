import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import StadioniComponent from "./components/Stadioni/StadioniComponent";
import GrupeComponent from "./components/Grupe/GrupeComponent";
import LandingPage from "./components/Landing/LandingPage";
import ZakazivanjeComponent from "./components/Utakmice/ZakazivanjeComponent";
import UtakmiceComponent from "./components/Utakmice/UtakmiceComponent";
import EvidencijaComponent from "./components/Utakmice/EvidencijaComponent";
import ReprezentacijaComponent from "./components/Reprezentacija/ReprezentacijaComponent";
import NavBar from "./components/NavBar/NavBar";
import './App.css';
import React from "react";

function App() {
    return (
        <Router>
            <div className="app-container">
                <NavBar/>

                <Routes>
                    <Route path="/" element={<LandingPage/>}/>
                    <Route path="/stadioni" element={<StadioniComponent/>}/>
                    <Route path="/grupe" element={<GrupeComponent/>}/>
                    <Route path="/zakazivanje" element={<ZakazivanjeComponent/>}/>
                    <Route path="/utakmice" element={<UtakmiceComponent/>}/>
                    <Route path="/utakmice/:id" element={<EvidencijaComponent/>}/>
                    <Route path="/reprezentacije/:id" element={<ReprezentacijaComponent/>}/>
                </Routes>
            </div>
        </Router>
    );
}

export default App;
