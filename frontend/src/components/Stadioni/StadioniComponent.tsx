import {useEffect, useState} from 'react';
import {Stadion} from "../../domain/Stadion";
import axios from "axios";
import StadionComponent from "./StadionComponent";

const StadioniComponent = () => {

    const [stadioni, setStadioni] = useState<Stadion[]>([]);

    useEffect(() => {
        const getStadioni = async () => {
            try {
                const response = await axios.get<Stadion[]>('http://localhost:8080/api/v1/stadioni');
                setStadioni(response.data);
            } catch (error) {
                console.error('Failed to fetch stadions:', error);
                throw error;
            }
        };

        getStadioni();
    }, []);

    return (
        <div className="stadioni">
            <div className="stadion-container">{stadioni.map(stadion => <StadionComponent stadion={stadion}
                                                                                          key={stadion.id}/>)}</div>
        </div>
    );
};

export default StadioniComponent;