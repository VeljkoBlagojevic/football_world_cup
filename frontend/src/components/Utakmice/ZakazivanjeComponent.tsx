import { ChangeEvent, FormEvent, useEffect, useState } from "react";
import axios from "axios";

import { Utakmica } from "../../domain/Utakmica";
import { Reprezentacija } from "../../domain/Reprezentacija";
import { Stadion } from "../../domain/Stadion";
import "./Zakazivanje.css";
import { useNavigate } from "react-router-dom";
import { AppState } from "../../redux/initialState";
import { useSelector } from "react-redux";

const ZakazivanjeComponent = () => {
  const [formData, setFormData] = useState<Utakmica>({
    domacin: {} as Reprezentacija,
    gost: {} as Reprezentacija,
    stadion: {} as Stadion,
    odigrana: false,
    termin: {
      pocetak: new Date(),
      kraj: new Date(),
    },
  });
  const token = useSelector((state: AppState) => state.token);
  const [reprezentacije, setReprezentacije] = useState<Reprezentacija[]>([]);
  const [stadioni, setStadioni] = useState<Stadion[]>([]);

  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      await fetchReprezentacije();
      await fetchStadioni();
    };

    fetchData();
  }, []);

  const fetchReprezentacije = async () => {
    try {
      const response = await axios.get<Reprezentacija[]>(
        "http://localhost:8080/api/v1/reprezentacije",
        {
          headers: {
            Authorization: `Bearer ${token}}`,
          },
        }
      );
      setReprezentacije(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  const fetchStadioni = async () => {
    try {
      const response = await axios.get<Stadion[]>(
        "http://localhost:8080/api/v1/stadioni",
        {
          headers: {
            Authorization: `Bearer ${token}}`,
          },
        }
      );
      setStadioni(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  const handleChange = (
    e: ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;

    // Check if the field belongs to the nested `termin` object
    if (name.startsWith("termin.")) {
      const terminField = name.split(".")[1]; // Get the field name after 'termin.'
      setFormData((prevFormData) => ({
        ...prevFormData,
        termin: {
          ...prevFormData.termin,
          [terminField]: value,
        },
      }));
    } else {
      setFormData((prevFormData) => ({
        ...prevFormData,
        [name]: value,
      }));
    }
  };

  const handleReprezentacijaChange = (
    e: ChangeEvent<HTMLSelectElement>,
    property: string
  ) => {
    const { value } = e.target;
    const selectedReprezentacija = reprezentacije.find(
      (reprezentacija) => reprezentacija.naziv === value
    );
    setFormData((prevFormData) => ({
      ...prevFormData,
      [property]: selectedReprezentacija,
    }));
  };

  const handleStadionChange = (e: ChangeEvent<HTMLSelectElement>) => {
    const { value } = e.target;
    const selectedStadion = stadioni.find((stadion) => stadion.naziv === value);

    setFormData((prevFormData) => ({
      ...prevFormData,
      stadion: selectedStadion ?? ({} as Stadion),
    }));
  };

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();

    console.log("FORM DATA");
    console.log(formData);

    try {
      const response = await axios.post(
        "http://localhost:8080/api/v1/utakmice",
        formData,
        {
          headers: {
            Authorization: `Bearer ${token}}`,
          },
        }
      );
      console.log(response.data); // Handle success response
      alert("Uspesno ste zakazali novu utakmicu");
      navigate("/utakmice");
      setFormData({
        domacin: {} as Reprezentacija,
        gost: {} as Reprezentacija,
        stadion: {} as Stadion,
        odigrana: false,
        termin: {
          pocetak: new Date(),
          kraj: new Date(),
        },
      });
    } catch (error: any) {
      alert(error.response.data.body.detail);
      console.error(error); // Handle error response
    }
  };

  return (
    <form className="utakmica-form" onSubmit={handleSubmit}>
      <label>
        Domaćin:
        <select
          name="domacin"
          onChange={(e) => handleReprezentacijaChange(e, "domacin")}
        >
          <option disabled>Izaberite reprezentaciju domaćina</option>
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
        <select
          name="gost"
          onChange={(e) => handleReprezentacijaChange(e, "gost")}
        >
          <option disabled>Izaberite reprezentaciju gosta</option>
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
          <option disabled>Izaberite stadion</option>
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
          name="termin.pocetak"
          onChange={handleChange}
        />
      </label>
      <br />
      <label>
        Kraj:
        <input
          type="datetime-local"
          name="termin.kraj"
          onChange={handleChange}
        />
      </label>
      <br />
      <button type="submit">Zakaži</button>
    </form>
  );
};

export default ZakazivanjeComponent;
