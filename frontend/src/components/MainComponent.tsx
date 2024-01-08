import {
  ApolloProvider,
  ApolloClient,
  InMemoryCache,
  HttpLink,
  from,
} from "@apollo/client";
import NavBar from "./NavBar/NavBar";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import LoginComponent from "./Auth/LoginComponent";
import RegisterComponent from "./Auth/RegisterComponent";
import LandingPage from "./Landing/LandingPage";
import StadioniComponent from "./Stadioni/StadioniComponent";
import GrupeComponent from "./Grupe/GrupeComponent";
import ZakazivanjeComponent from "./Utakmice/ZakazivanjeComponent";
import UtakmiceComponent from "./Utakmice/UtakmiceComponent";
import EvidencijaComponent from "./Utakmice/EvidencijaComponent";
import ReprezentacijaComponent from "./Reprezentacija/ReprezentacijaComponent";
import { onError } from "@apollo/client/link/error";
import { setContext } from "@apollo/client/link/context";
import { useSelector } from "react-redux";
import { AppState } from "../redux/initialState";

const MainComponent = () => {
  const token = useSelector((state: AppState) => state.token);

  const authLink = setContext((_, { headers }) => {
    console.log("proba", token);
    return {
      headers: {
        ...headers,
        Authorization: `Bearer ${token}`,
      },
    };
  });

  const errorLink = onError(({ graphQLErrors }) => {
    if (graphQLErrors) {
      graphQLErrors.forEach(({ message }) => {
        console.log(`Graphql error ${message}`);
      });
    }
  });

  const link = from([
    authLink,
    errorLink,
    new HttpLink({ uri: `http://localhost:8080/graphql` }),
  ]);

  const client = new ApolloClient({
    link: link,
    cache: new InMemoryCache(),
  });

  return (
    <ApolloProvider client={client}>
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
    </ApolloProvider>
  );
};

export default MainComponent;
