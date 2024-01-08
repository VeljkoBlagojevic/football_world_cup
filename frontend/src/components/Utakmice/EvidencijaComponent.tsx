import React, { ChangeEvent, FormEvent, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import { Utakmica } from "../../domain/Utakmica";
import { EvidencijaUtakmice } from "../../domain/EvidencijaUtakmice";
import { Predaja } from "../../domain/Predaja";
import StadionComponent from "../Stadioni/StadionComponent";
import "./Evidencija.css";
import { AppState } from "../../redux/initialState";
import { useSelector } from "react-redux";

const EvidencijaComponent = () => {
  const token = useSelector((state: AppState) => state.token);
  const [utakmica, setUtakmica] = useState<Utakmica>({} as Utakmica);
  const [evidencijaUtakmice, setEvidencijaUtakmice] =
    useState<EvidencijaUtakmice>({
      brojGolovaDomacina: 0,
      brojGolovaGosta: 0,
      predaja: Predaja.NEMA_PREDAJE,
    });

  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    const getUtakmica = async () => {
      if (id) {
        try {
          const response = await axios.get<Utakmica>(
            "http://localhost:8080/api/v1/utakmice/" + id,
            {
              headers: {
                Authorization: `Bearer ${token}}`,
              },
            }
          );
          setUtakmica(response.data);
        } catch (error) {
          console.error("Failed to fetch utakmica:", error);
        }
      }
    };

    getUtakmica();
  }, [id]);

  const handleChange = (
    e: ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;

    setEvidencijaUtakmice((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  };
  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:8080/api/v1/utakmice/" + id,
        evidencijaUtakmice,
        {
          headers: {
            Authorization: `Bearer ${token}}`,
          },
        }
      );
      alert("Uspesno ste evidentirali utakmicu");
      navigate("/grupe");
      setEvidencijaUtakmice({
        brojGolovaDomacina: 0,
        brojGolovaGosta: 0,
        predaja: Predaja.NEMA_PREDAJE,
      });
    } catch (error: any) {
      alert(error.response.data.body.detail);
      console.error(error); // Handle error response
    }
  };

  function navigateToReprezentacija(id: number | undefined) {
    navigate(`/reprezentacije/${id}`);
  }

  return (
    <div className="utakmica-details">
      <form className="evidencija-form" onSubmit={handleSubmit}>
        <div className="timovi">
          <div className="domacin">
            <h1 onClick={() => navigateToReprezentacija(utakmica.domacin?.id)}>
              Domaćin: {utakmica.domacin?.naziv}
            </h1>
            <img
              src={utakmica.domacin?.zastava}
              alt={utakmica.domacin?.troslovniNaziv}
            />
            <label>
              Broj postignutih golova:
              <input
                type="number"
                name="brojGolovaDomacina"
                onChange={handleChange}
              />
            </label>
            <br />
          </div>
          <div className="gost">
            <h1 onClick={() => navigateToReprezentacija(utakmica.gost?.id)}>
              Gost: {utakmica.gost?.naziv}
            </h1>
            <img
              src={utakmica.gost?.zastava}
              alt={utakmica.gost?.troslovniNaziv}
            />
            <label>
              Broj postignutih golova:
              <input
                type="number"
                name="brojGolovaGosta"
                onChange={handleChange}
              />
            </label>
            <br />
          </div>
        </div>
        <div>
          <StadionComponent stadion={utakmica?.stadion} />
        </div>
        <div className="termin">
          <h3>Početak: {utakmica.termin?.pocetak.toLocaleString()}</h3>
          <h3>Kraj: {utakmica.termin?.kraj.toLocaleString()}</h3>
        </div>
        <div className="predaja">
          <label>
            <select name="predaja" onChange={(e) => handleChange(e)}>
              <option disabled>Izaberite da li je bilo predaje</option>
              {Object.keys(Predaja).map((tipPredaje) => (
                <option key={tipPredaje} value={tipPredaje.valueOf()}>
                  {tipPredaje.valueOf()}
                </option>
              ))}
            </select>
          </label>
          <br />
        </div>
        <button type="submit">Evidentiraj rezultat</button>
      </form>
    </div>
  );
};

export default EvidencijaComponent;
