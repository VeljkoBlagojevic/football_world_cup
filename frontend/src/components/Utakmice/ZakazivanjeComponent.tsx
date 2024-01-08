import { ChangeEvent, FormEvent, useEffect, useState } from "react";
import { Utakmica } from "../../domain/Utakmica";
import { Reprezentacija } from "../../domain/Reprezentacija";
import { Stadion } from "../../domain/Stadion";
import "./Zakazivanje.css";
import { useNavigate } from "react-router-dom";
import { useMutation, useQuery } from "@apollo/client";
import {
  GET_ALL_REPREZENTACIJE,
  GET_ALL_STADIONI,
} from "../../graphql/queries";
import { ZAKAZI_UTAKMICU } from "../../graphql/mutations";

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
  const [reprezentacije, setReprezentacije] = useState<Reprezentacija[]>([]);
  const [stadioni, setStadioni] = useState<Stadion[]>([]);

  const { data: reprezentacijeData, loading: reprezentacijeLoading } = useQuery(
    GET_ALL_REPREZENTACIJE
  );
  const { data: stadioniData, loading: stadioniLoading } =
    useQuery(GET_ALL_STADIONI);

  const [zakaziUtakmicu, { error: zakaziUtakmicuError }] =
    useMutation(ZAKAZI_UTAKMICU);

  const navigate = useNavigate();

  useEffect(() => {
    if (!reprezentacijeLoading && !stadioniLoading) {
      fetchReprezentacije();
      fetchStadioni();
      if (reprezentacije.length > 0 && stadioni.length > 0) {
        setFormData((prevFormData) => ({
          ...prevFormData,
          domacin: reprezentacije[0],
          gost: reprezentacije[0],
          stadion: stadioni[0],
        }));
      }
    }
  }, [
    reprezentacijeData,
    stadioniData,
    reprezentacijeLoading,
    stadioniLoading,
  ]);

  const fetchReprezentacije = () => {
    if (reprezentacijeData) {
      setReprezentacije(reprezentacijeData.getAllReprezentacije);
    }
  };

  const fetchStadioni = () => {
    if (stadioniData) {
      setStadioni(stadioniData.getAllStadioni);
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

  const handleSubmit = (e: FormEvent) => {
    e.preventDefault();

    const utakmicaInput = {
      domacinId: formData.domacin.id,
      gostId: formData.gost.id,
      stadionId: formData.stadion.id,
      odigrana: false,
      terminInput: {
        pocetak: formData.termin.pocetak,
        kraj: formData.termin.kraj,
      },
    };

    console.log(utakmicaInput);

    zakaziUtakmicu({
      variables: {
        utakmicaInput,
      },
    })
      .then(() => {
        navigate("/utakmice");
      })
      .catch((error) => alert(error))
      .finally(() => {
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
      });
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
          {reprezentacije?.map((reprezentacija) => (
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
          {reprezentacije?.map((reprezentacija) => (
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
          {stadioni?.map((stadion) => (
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
