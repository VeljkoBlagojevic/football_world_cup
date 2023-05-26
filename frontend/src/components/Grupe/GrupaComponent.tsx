import React from 'react';
import {Grupa} from "../../domain/Grupa";
import "./Grupa.css"

interface GrupaComponentProps {
    grupa: Grupa;
}

const GrupaComponent = ({ grupa }: GrupaComponentProps) => {
    return (
        <div className="grupa">
            <table className="grupa-table">
                <tbody>
                <tr>
                    <td>Grupa: {grupa.naziv}</td>
                </tr>
                <tr>
                    <th>Reprezentacija</th>
                    <th>Zastava</th>
                    <th>Broj odigranih utakmica</th>
                    <th>Broj pobedjenih utakmica</th>
                    <th>Broj neresenih utakmica</th>
                    <th>Broj izgubljenih utakmica</th>
                    <th>Broj datih golova</th>
                    <th>Broj primljenih golova</th>
                    <th>Broj osvojenih poena</th>
                </tr>
                {grupa.statistikeReprezentacija?.map(statistika => {
                    return (
                        <tr key={statistika.id}>
                            <td>{statistika.reprezentacija?.naziv}</td>
                            <td><img src={statistika.reprezentacija?.zastava} alt={statistika.reprezentacija?.troslovniNaziv}/></td>
                            <td>{statistika.brojOdigranihUtakmica}</td>
                            <td>{statistika.brojPobedjenihUtakmica}</td>
                            <td>{statistika.brojNeresenihUtakmica}</td>
                            <td>{statistika.brojIzgubljenihUtakmica}</td>
                            <td>{statistika.brojDatihGolova}</td>
                            <td>{statistika.brojPrimljenihGolova}</td>
                            <td>{statistika.brojOsvojenihPoena}</td>
                        </tr>
                    );
                })}
                </tbody>
            </table>
        </div>
    );
};

export default GrupaComponent;
