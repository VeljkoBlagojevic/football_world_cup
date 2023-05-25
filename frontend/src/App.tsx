import React from 'react';
import './App.css';
import StadioniComponent from "./components/Stadioni/StadioniComponent";
import GrupeComponent from "./components/Grupe/GrupeComponent";
import LandingPage from "./components/Landing/LandingPage";
import ZakazivanjeComponent from "./components/Utakmice/ZakazivanjeComponent";
import UtakmiceComponent from "./components/Utakmice/UtakmiceComponent";

function App() {
    return (
        <>
            <LandingPage></LandingPage>
            <StadioniComponent></StadioniComponent>
            <GrupeComponent></GrupeComponent>
            <ZakazivanjeComponent></ZakazivanjeComponent>
            <UtakmiceComponent></UtakmiceComponent>
        </>
    );
}

export default App;
