import {Reprezentacija} from "./Reprezentacija";
import {Stadion} from "./Stadion";
import {EvidencijaUtakmice} from "./EvidencijaUtakmice";

export class Utakmica {
    public id?: number;
    public domacin?: Reprezentacija;
    public gost?: Reprezentacija;
    public evidencijaUtakmice?: EvidencijaUtakmice;
    public stadion?: Stadion;
    public termin?: {
        pocetak?: Date;
        kraj?: Date;
    };
}
