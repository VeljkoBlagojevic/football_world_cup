import {Reprezentacija} from "./Reprezentacija";
import {StatistikaReprezentacije} from "./StatistikaReprezentacije";

export interface Grupa {
    id?: number;
    naziv?: string;
    reprezentacije?: Reprezentacija[];
    statistikeReprezentacija?: StatistikaReprezentacije[];
}
