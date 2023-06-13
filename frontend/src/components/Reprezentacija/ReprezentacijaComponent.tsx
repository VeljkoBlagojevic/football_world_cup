import React, {useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';
import axios from 'axios';
import {Reprezentacija} from '../../domain/Reprezentacija';
import {Utakmica} from '../../domain/Utakmica';
import UtakmicaComponent from '../Utakmice/UtakmicaComponent';
import './Reprezentacija.css';

const ReprezentacijaComponent = () => {
    const { id } = useParams();
    const [reprezentacija, setReprezentacija] = useState<Reprezentacija>();
    const [utakmice, setUtakmice] = useState<Utakmica[]>();

    useEffect(() => {
        const fetchReprezentacija = async () => {
            try {
                const response = await axios.get<Reprezentacija>(
                    `http://localhost:8080/api/v1/reprezentacije/${id}`
                );
                setReprezentacija(response.data);
            } catch (error) {
                console.error('Failed to fetch reprezentacija:', error);
                throw error;
            }
        };
        fetchReprezentacija();
        fetchUtakmice();
    }, [id]);

    const fetchUtakmice = async () => {
        try {
            const response = await axios.get<Utakmica[]>(
                `http://localhost:8080/api/v1/utakmice/reprezentacija/${id}`
            );
            setUtakmice(response.data);
        } catch (error) {
            console.error('Failed to fetch utakmice', error);
            throw error;
        }
    };

    return (
        <div>
            <div className="reprezentacija-info-container">
                <div className="info-row">
                    <span className="naziv">{reprezentacija?.naziv}</span>
                </div>
                <div className="info-row alpha-codes">
                    <span className="info-label">Dvoslovni naziv (alpha code 2):</span>
                    <span className="alpha-code">{reprezentacija?.dvoslovniNaziv}</span>
                </div>
                <div className="info-row alpha-codes">
                    <span className="info-label">Troslovni naziv (alpha code 3):</span>
                    <span className="alpha-code">{reprezentacija?.troslovniNaziv}</span>
                </div>
                <img className="zastava" src={reprezentacija?.zastava} alt={reprezentacija?.troslovniNaziv} />
            </div>
            <div className="reprezentacija-utakmice-container">
                {utakmice?.map(utakmica => (
                    <UtakmicaComponent utakmica={utakmica} key={utakmica.id} />
                ))}
            </div>
        </div>
    );
};

export default ReprezentacijaComponent;
