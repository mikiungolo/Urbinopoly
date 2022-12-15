// modellazione di una proprietà Stazione
public class Station extends Property {

    // costruttore
    public Station(String name, int price, int[] gain) {
        super(name, price, gain, Type.STATION);
    }

    // ritorna affitto in base al numero di stazioni
    public int getRent(int nStation) {
        return super.getGain()[nStation];
    }
}
