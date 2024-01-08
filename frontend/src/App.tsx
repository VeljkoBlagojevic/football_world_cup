import "./App.css";
import React from "react";
import { Provider } from "react-redux";
import { createStore } from "redux";
import { rootReducer } from "./redux/reducers/rootReducer";
import MainComponent from "./components/MainComponent";

function App() {
  const store = createStore(
    rootReducer,
    (window as any).__REDUX_DEVTOOLS_EXTENSION__ &&
      (window as any).__REDUX_DEVTOOLS_EXTENSION__()
  );

  return (
    <React.StrictMode>
      <Provider store={store}>
        <MainComponent />
      </Provider>
    </React.StrictMode>
  );
}

export default App;
