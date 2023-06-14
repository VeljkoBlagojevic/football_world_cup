export class Stadion {
    id: number;

    naziv: string;

    kapacitet: number;

    slika: string;

    constructor(
        id: number,
        naziv: string,
        kapacitet: number,
        slika: string
    ) {
        this.id = id;
        this.naziv = naziv;
        this.kapacitet = kapacitet;
        this.slika = slika;
    }

}
