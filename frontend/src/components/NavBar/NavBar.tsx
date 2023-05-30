import {Link} from "react-router-dom";
import "./NavBar.css"

const NavBar = () => {
    return (
        <div>
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
        </div>
    );
};

export default NavBar;