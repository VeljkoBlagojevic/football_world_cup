import React, {useEffect, useState} from 'react';
import {Utakmica} from "../../domain/Utakmica";
import axios from "axios";
import UtakmicaComponent from "./UtakmicaComponent";
import "./Utakmice.css";

const UtakmiceComponent = () => {

    const [utakmice, setUtakmice] = useState<Utakmica[]>([]);

    useEffect(() => {
        const getUtakmice = async () => {
            try {
                const response = await axios.get("http://localhost:8080/api/v1/utakmice");
                setUtakmice(response.data as Utakmica[]);
            } catch (error) {
                console.error("Failed to fetch utakmice:", error);
                throw error;
            }
        };

        getUtakmice();

    }, []);

    return (
        <div className="utakmice">
            <div className="utakmica-container">
                {utakmice.map(utakmica => <UtakmicaComponent utakmica={utakmica} key={utakmica.id}></UtakmicaComponent>)}
            </div>
        </div>
    );
};

export default UtakmiceComponent;