import {useEffect, useState} from 'react';
import {Grupa} from "../../domain/Grupa";
import axios from "axios";
import GrupaComponent from "./GrupaComponent";

const GrupeComponent = () => {

    const [grupe, setGrupe] = useState<Grupa[]>([]);

    useEffect(() => {
        const getGrupe = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/v1/grupe');
                console.log(response.data);
                setGrupe(response.data as Grupa[]);
            } catch (error) {
                console.error('Failed to fetch stadions:', error);
                throw error;
            }
        };

        getGrupe();
    }, []);

    return (
        <div className="grupe">
            <div className="grupa-container">{grupe.map(grupa => <GrupaComponent grupa={grupa} key={grupa.id}/>)}</div>
        </div>
    );
};

export default GrupeComponent;