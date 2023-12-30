import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import StadioniComponent from "./components/Stadioni/StadioniComponent";
import GrupeComponent from "./components/Grupe/GrupeComponent";
import LandingPage from "./components/Landing/LandingPage";
import ZakazivanjeComponent from "./components/Utakmice/ZakazivanjeComponent";
import UtakmiceComponent from "./components/Utakmice/UtakmiceComponent";
import EvidencijaComponent from "./components/Utakmice/EvidencijaComponent";
import ReprezentacijaComponent from "./components/Reprezentacija/ReprezentacijaComponent";
import NavBar from "./components/NavBar/NavBar";
import "./App.css";
import LoginComponent from "./components/Auth/LoginComponent";
import RegisterComponent from "./components/Auth/RegisterComponent";
import React, { useState } from "react";
import { Provider } from "react-redux";
import { createStore } from "redux";
import { rootReducer } from "./redux/reducers/rootReducer";

const store = createStore(
  rootReducer,
  (window as any).__REDUX_DEVTOOLS_EXTENSION__ &&
    (window as any).__REDUX_DEVTOOLS_EXTENSION__()
);

function App() {
  return (
    <React.StrictMode>
      <Provider store={store}>
        <Router>
          <div className="app-container">
            <NavBar />

            <Routes>
              <Route path="/" element={<LoginComponent />} />
              <Route path="/login" element={<LoginComponent />} />
              <Route path="/register" element={<RegisterComponent />} />
              <Route path="/pocetna" element={<LandingPage />} />
              <Route path="/stadioni" element={<StadioniComponent />} />
              <Route path="/grupe" element={<GrupeComponent />} />
              <Route path="/zakazivanje" element={<ZakazivanjeComponent />} />
              <Route path="/utakmice" element={<UtakmiceComponent />} />
              <Route path="/utakmice/:id" element={<EvidencijaComponent />} />
              <Route
                path="/reprezentacije/:id"
                element={<ReprezentacijaComponent />}
              />
            </Routes>
          </div>
        </Router>
      </Provider>
    </React.StrictMode>
  );
}

export default App;
