export class Termin {
    public pocetak?: Date;
    public kraj?: Date;

    constructor(termin: Termin) {
        this.pocetak = termin.pocetak;
        this.kraj = termin.kraj;
    }
}
