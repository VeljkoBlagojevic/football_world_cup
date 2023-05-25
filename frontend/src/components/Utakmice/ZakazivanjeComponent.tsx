import React, { useState, useEffect, ChangeEvent, FormEvent } from 'react';
import axios from 'axios';

import {Utakmica} from "../../domain/Utakmica";
import {Reprezentacija} from "../../domain/Reprezentacija";
import {Stadion} from "../../domain/Stadion";
import "./Zakazivanje.css"

const ZakazivanjeComponent = () => {
    const [formData, setFormData] = useState<Utakmica>({
    });
    const [reprezentacije, setReprezentacije] = useState<Reprezentacija[]>([]);
    const [stadioni, setStadioni] = useState<Stadion[]>([]);

    useEffect(() => {
        fetchReprezentacije();
        fetchStadioni();
    }, []);

    const fetchReprezentacije = () => {
        axios
            .get('http://localhost:8080/api/v1/reprezentacije')
            .then((response) => {
                setReprezentacije(response.data);
            })
            .catch((error) => {
                console.error(error);
            });
    };

    const fetchStadioni = () => {
        axios
            .get('http://localhost:8080/api/v1/stadioni')
            .then((response) => {
                setStadioni(response.data);
            })
            .catch((error) => {
                console.error(error);
            });
    };

    const handleChange = (e: ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e: FormEvent) => {
        e.preventDefault();

        axios
            .post('/api/utakmice', formData)
            .then((response) => {
                console.log(response.data); // Handle success response
                // Reset form data if needed
                setFormData({});
            })
            .catch((error) => {
                console.error(error); // Handle error response
            });
    };

    return (
        <form className="utakmica-form" onSubmit={handleSubmit}>
            <label>
                Domaćin:
                <select
                    name="domacin"
                    onChange={handleChange}
                >
                    <option value="">Select domacin</option>
                    {reprezentacije.map((reprezentacija) => (
                        <option key={reprezentacija.id} value={reprezentacija.naziv}>
                            {reprezentacija.naziv}
                        </option>
                    ))}
                </select>
            </label>
            <br />
            <label>
                Gost:
                <select name="gost"  onChange={handleChange}>
                    <option value="">Select gost</option>
                    {reprezentacije.map((reprezentacija) => (
                        <option key={reprezentacija.id} value={reprezentacija.naziv}>
                            {reprezentacija.naziv}
                        </option>
                    ))}
                </select>
            </label>
            <br />
            <label>
                Stadion:
                <select name="stadion" onChange={handleChange}>
                    <option value="">Select stadion</option>
                    {stadioni.map((stadion) => (
                        <option key={stadion.id} value={stadion.naziv}>
                            {stadion.naziv}
                        </option>
                    ))}
                </select>
            </label>
            <br />
            <label>
                Početak:
                <input
                    type="datetime-local"
                    name="pocetak"
                    onChange={handleChange}
                />
            </label>
            <br />
            <label>
                Kraj:
                <input
                    type="datetime-local"
                    name="kraj"
                    onChange={handleChange}
                />
            </label>
            <br />
            <button type="submit">Zakazi</button>
        </form>
    );
};

export default ZakazivanjeComponent;
