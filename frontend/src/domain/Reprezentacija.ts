
export class Reprezentacija {
    id: number;

    naziv: string;

    dvoslovniNaziv: string;

    troslovniNaziv: string;

    zastava: string;

    constructor(
        id: number,
        naziv: string,
        dvoslovniNaziv: string,
        troslovniNaziv: string,
        zastava: string
    ) {
        this.id = id;
        this.naziv = naziv;
        this.dvoslovniNaziv = dvoslovniNaziv;
        this.troslovniNaziv = troslovniNaziv;
        this.zastava = zastava;
    }
}

