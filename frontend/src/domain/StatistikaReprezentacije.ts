import {Reprezentacija} from "./Reprezentacija";

export class StatistikaReprezentacije {
    public id?: number;
    public reprezentacija?: Reprezentacija;
    public brojOdigranihUtakmica?: number;
    public brojPobedjenihUtakmica?: number;
    public brojNeresenihUtakmica?: number;
    public brojIzgubljenihUtakmica?: number;
    public brojDatihGolova?: number;
    public brojPrimljenihGolova?: number;
    public brojOsvojenihPoena?: number;

    constructor(statistikaReprezentacije: StatistikaReprezentacije) {
        this.id = statistikaReprezentacije.id;
        this.reprezentacija = statistikaReprezentacije.reprezentacija;
        this.brojOdigranihUtakmica = statistikaReprezentacije.brojOdigranihUtakmica;
        this.brojPobedjenihUtakmica = statistikaReprezentacije.brojPobedjenihUtakmica;
        this.brojNeresenihUtakmica = statistikaReprezentacije.brojNeresenihUtakmica;
        this.brojIzgubljenihUtakmica = statistikaReprezentacije.brojIzgubljenihUtakmica;
        this.brojDatihGolova = statistikaReprezentacije.brojDatihGolova;
        this.brojPrimljenihGolova = statistikaReprezentacije.brojPrimljenihGolova;
        this.brojOsvojenihPoena = statistikaReprezentacije.brojOsvojenihPoena;
    }
}
