import {Utakmica} from "../../domain/Utakmica";
import "./Utakmica.css";
import {useNavigate} from "react-router-dom";

interface UtakmicaComponentProps {
    utakmica: Utakmica;
}

const UtakmicaComponent = ({utakmica}: UtakmicaComponentProps) => {
    const navigate = useNavigate();

    const handleClick = () => {
        navigate('/utakmice/' + utakmica.id);
    };

    return (
        <div className="utakmica" onClick={handleClick}>
            <div className="timovi">
                <div className="team-info">
                    <p>Domaćin: {utakmica.domacin.naziv}</p>
                    <img src={utakmica.domacin.zastava} alt={utakmica.domacin.naziv}/>
                </div>
                <div className="team-info">
                    <p>Gost: {utakmica.gost.naziv}</p>
                    <img src={utakmica.gost.zastava} alt={utakmica.gost.naziv}/>
                </div>
            </div>
            <div className="stadion-info">
                <p>Stadion: {utakmica.stadion.naziv}</p>
                <img src={utakmica.stadion.slika} alt={utakmica.stadion.naziv}/>
            </div>
            <p>Početak: {utakmica.termin.pocetak.toLocaleString()}</p>
            <p>Kraj: {utakmica.termin.kraj.toLocaleString()}</p>
        </div>
    );
};

export default UtakmicaComponent;