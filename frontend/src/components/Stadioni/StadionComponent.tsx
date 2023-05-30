import {Stadion} from "../../domain/Stadion";
import "./Stadion.css"

interface StadionComponentProps {
    stadion: Stadion;
}

const StadionComponent = ({stadion}: StadionComponentProps) => {
    return (
        <div className="stadion">
            <h1>{stadion?.naziv}</h1>
            <h4>Kapacitet: {stadion?.kapacitet}</h4>
            <h4>Lokacija: {stadion?.lokacija}</h4>
            <img src={stadion?.slika} alt={stadion?.naziv}/>
        </div>
    );
};

export default StadionComponent;