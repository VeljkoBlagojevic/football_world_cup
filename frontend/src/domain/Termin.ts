export class Termin {
    public pocetak?: Date;
    public kraj?: Date;


    constructor(pocetak: Date, kraj: Date) {
        this.pocetak = pocetak;
        this.kraj = kraj;
    }
}
