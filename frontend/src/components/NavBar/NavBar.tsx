import { Link } from "react-router-dom";
import "./NavBar.css";
import { FC } from "react";
import { useSelector } from "react-redux";
import { AppState } from "../../redux/initialState";

const NavBar: FC = () => {
  const loggedInUser = useSelector((state: AppState) => state.loggedInUser);

  return (
    <div>
      <nav className="navbar">
        <ul className="nav-menu">
          {loggedInUser !== undefined && (
            <>
              <li className="nav-item">
                <Link to="/" className="nav-link">
                  Početna
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/stadioni" className="nav-link">
                  Stadioni
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/grupe" className="nav-link">
                  Grupe
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/zakazivanje" className="nav-link">
                  Zakazivanje
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/utakmice" className="nav-link">
                  Utakmice
                </Link>
              </li>
            </>
          )}
          {loggedInUser === undefined && (
            <>
              <li className="nav-item">
                <Link to="/login" className="nav-link">
                  Prijava
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/register" className="nav-link">
                  Registracija
                </Link>
              </li>
            </>
          )}
        </ul>
      </nav>
    </div>
  );
};

export default NavBar;
