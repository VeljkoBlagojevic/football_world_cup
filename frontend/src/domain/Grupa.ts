import {Reprezentacija} from "./Reprezentacija";
import {StatistikaReprezentacije} from "./StatistikaReprezentacije";

export class Grupa {
    public id?: number;
    public naziv?: string;
    public reprezentacije?: Reprezentacija[];
    public statistikeReprezentacija?: StatistikaReprezentacije[];

    constructor(grupa: Grupa) {
        this.id = grupa.id;
        this.naziv = grupa.naziv;
        this.reprezentacije = grupa.reprezentacije;
        this.statistikeReprezentacija = grupa.statistikeReprezentacija;
    }
}
