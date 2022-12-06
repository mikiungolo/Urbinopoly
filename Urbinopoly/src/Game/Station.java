// modellazione di una proprietà Stazione
public class Station extends Property {

    // costruttore
    public Station(String name, int price, int[] gain) {
        super(name, price, gain, TypeProperty.STATION);
    }

    // ritorna affitto
    @Override
    public int getRent(int nStation) {
        return super.getGain()[nStation];
    }
}
