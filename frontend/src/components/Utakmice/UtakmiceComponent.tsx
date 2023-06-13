import {useEffect, useState} from 'react';
import axios from "axios";
import {Utakmica} from "../../domain/Utakmica";
import UtakmicaComponent from "./UtakmicaComponent";
import "./Utakmice.css";

const UtakmiceComponent = () => {

    const [utakmice, setUtakmice] = useState<Utakmica[]>([]);

    useEffect(() => {
        fetchAllUtakmice();
    }, []);

    async function fetchAllUtakmice() {
        try {
            const response = await axios.get<Utakmica[]>("http://localhost:8080/api/v1/utakmice");
            setUtakmice(response.data);
        } catch (error) {
            console.error("Failed to fetch utakmice:", error);
            throw error;
        }
    }

    async function fetchUtakmice(odigrane: boolean) {
        try {
            const response = await axios.get<Utakmica[]>("http://localhost:8080/api/v1/utakmice", {params: {odigrana: odigrane}});
            setUtakmice(response.data);
        } catch (error) {
            console.error("Failed to fetch utakmice:", error);
            throw error;
        }
    }

    return (
        <>
            <div className="button-container">
                <button onClick={() => fetchUtakmice(true)}>Odigrane</button>
                <button onClick={() => fetchUtakmice(false)}>Zakazane</button>
                <button onClick={() => fetchAllUtakmice()}>Sve</button>
            </div>
            <div className="utakmice">
                <div className="utakmica-container">
                    {utakmice.map(utakmica => (
                        <UtakmicaComponent
                            utakmica={utakmica}
                            key={utakmica.id}
                        />
                    ))}
                </div>
            </div>
        </>

    );

};

export default UtakmiceComponent;