
export class Stadion {
    id: number;

    naziv: string;

    lokacija: string;

    kapacitet: number;

    slika: string;

    constructor(
        id: number,
        naziv: string,
        lokacija: string,
        kapacitet: number,
        slika: string
    ) {
        this.id = id;
        this.naziv = naziv;
        this.lokacija = lokacija;
        this.kapacitet = kapacitet;
        this.slika = slika;
    }

}
