import { useEffect, useState } from "react";
import { Grupa } from "../../domain/Grupa";
import axios from "axios";
import GrupaComponent from "./GrupaComponent";
import { AppState } from "../../redux/initialState";
import { useSelector } from "react-redux";
import StatistikaGrupeComponent from "./StatistikaGrupeComponent";

const GrupeComponent = () => {
  const [grupe, setGrupe] = useState<Grupa[]>([]);
  const token = useSelector((state: AppState) => state.token);

  useEffect(() => {
    const getGrupe = async () => {
      try {
        const response = await axios.get<Grupa[]>(
          "http://localhost:8080/api/v1/grupe",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        setGrupe(response.data);
      } catch (error) {
        console.error("Failed to fetch stadions:", error);
        throw error;
      }
    };

    getGrupe();
  }, []);

  return (
    <div className="grupe">
      <div className="grupa-container">
        {grupe.map((grupa) => (
          <>
            <GrupaComponent grupa={grupa} key={grupa.id} />
            <StatistikaGrupeComponent
              statistike={grupa.statistikeReprezentacija}
              key={grupa.id}
            />
          </>
        ))}
      </div>
    </div>
  );
};

export default GrupeComponent;
