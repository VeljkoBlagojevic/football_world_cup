import React from 'react';
import './App.css';
import StadioniComponent from "./components/Stadioni/StadioniComponent";
import GrupeComponent from "./components/Grupe/GrupeComponent";
import LandingPage from "./components/Landing/LandingPage";
import ZakazivanjeComponent from "./components/Utakmice/ZakazivanjeComponent";

function App() {
    return (
        <>
            <LandingPage></LandingPage>
            <StadioniComponent></StadioniComponent>
            <GrupeComponent></GrupeComponent>
            <ZakazivanjeComponent></ZakazivanjeComponent>
        </>
    );
}

export default App;
