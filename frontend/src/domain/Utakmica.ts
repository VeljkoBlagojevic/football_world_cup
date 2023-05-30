import {Reprezentacija} from "./Reprezentacija";
import {Stadion} from "./Stadion";
import {EvidencijaUtakmice} from "./EvidencijaUtakmice";

export class Utakmica {
    public id?: number;
    public domacin: Reprezentacija;
    public gost: Reprezentacija;
    public evidencijaUtakmice?: EvidencijaUtakmice;
    public stadion: Stadion;
    public odigrana: boolean;
    public termin: {
        pocetak: Date;
        kraj: Date;
    };

    constructor(id: number, domacin: Reprezentacija, gost: Reprezentacija, evidencijaUtakmice: EvidencijaUtakmice, stadion: Stadion, odigrana:boolean, termin: {
        pocetak: Date;
        kraj: Date
    }) {
        this.id = id;
        this.domacin = domacin;
        this.gost = gost;
        this.evidencijaUtakmice = evidencijaUtakmice;
        this.stadion = stadion;
        this.odigrana = odigrana;
        this.termin = termin;
    }
}
