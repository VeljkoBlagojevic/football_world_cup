import React, { useState, useEffect, ChangeEvent, FormEvent } from 'react';
import axios from 'axios';

import { Utakmica } from '../../domain/Utakmica';
import { Reprezentacija } from '../../domain/Reprezentacija';
import { Stadion } from '../../domain/Stadion';
import './Zakazivanje.css';

const ZakazivanjeComponent = () => {
    const [formData, setFormData] = useState<Utakmica>({
        domacin: new Reprezentacija(0, '', '', '', ''),
        gost: new Reprezentacija(0, '', '', '', ''),
        stadion: new Stadion(0, '', '', 0, ''),
        termin: {
            pocetak: new Date(),
            kraj: new Date(),
        },
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
                setReprezentacije(response.data as Reprezentacija[]);
            })
            .catch((error) => {
                console.error(error);
            });
    };

    const fetchStadioni = () => {
        axios
            .get('http://localhost:8080/api/v1/stadioni')
            .then((response) => {
                setStadioni(response.data as Stadion[]);
            })
            .catch((error) => {
                console.error(error);
            });
    };

    const handleChange = (e: ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value } = e.target;

        // Check if the field belongs to the nested `termin` object
        if (name.startsWith('termin.')) {
            const terminField = name.split('.')[1];

            setFormData((prevFormData) => ({
                ...prevFormData,
                termin: {
                    ...prevFormData.termin,
                    [terminField]: new Date(value), // Convert input value to a Date object
                },
            }));
        } else {
            setFormData((prevFormData) => ({
                ...prevFormData,
                [name]: value,
            }));
        }
    };


    const handleReprezentacijaChange = (e: ChangeEvent<HTMLSelectElement>, property: string) => {
        const { value } = e.target;
        const selectedReprezentacija = reprezentacije.find((reprezentacija) => reprezentacija.naziv === value);
        setFormData((prevFormData) => ({
            ...prevFormData,
            [property]: selectedReprezentacija || new Reprezentacija(0, '', '', '', ''),
        }));
    };

    const handleStadionChange = (e: ChangeEvent<HTMLSelectElement>) => {
        const { value } = e.target;
        const selectedStadion = stadioni.find((stadion) => stadion.naziv === value);
        setFormData((prevFormData) => ({
            ...prevFormData,
            stadion: selectedStadion || new Stadion(0, '', '', 0, ''),
        }));
    };

    const handleSubmit = (e: FormEvent) => {
        e.preventDefault();

        console.log('FORM DATA');
        console.log(formData);

        axios
            .post('http://localhost:8080/api/v1/utakmice', formData)
            .then((response) => {
                console.log(response.data); // Handle success response
                setFormData({
                    domacin: new Reprezentacija(0, '', '', '', ''),
                    gost: new Reprezentacija(0, '', '', '', ''),
                    stadion: new Stadion(0, '', '', 0, ''),
                    termin: {
                        pocetak: new Date(),
                        kraj: new Date(),
                    },
                });
            })
            .catch((error) => {
                alert(error.response.data.body.detail)
                console.error(error); // Handle error response
            });
    };


    return (
        <form className="utakmica-form" onSubmit={handleSubmit}>
            <label>
                Domaćin:
                <select name="domacin" onChange={(e) => handleReprezentacijaChange(e, 'domacin')}>
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
                <select name="gost" onChange={(e) => handleReprezentacijaChange(e, 'gost')}>
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
                <select name="stadion" onChange={handleStadionChange}>
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
                <input type="datetime-local" name="termin.pocetak" onChange={handleChange} />
            </label>
            <br />
            <label>
                Kraj:
                <input type="datetime-local" name="termin.kraj" onChange={handleChange} />
            </label>
            <br />
            <button type="submit">Zakazi</button>
        </form>
    );
};

export default ZakazivanjeComponent;
