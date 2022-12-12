// Modellazione di una proprietà Servizio
public class Service extends Property {

    // costruttore
    public Service(String name, int price, int[] gain) {
        super(name, price, gain, Type.SERVICE);
    }

    // n-servizi * valore dei dadi
    public int getRent(int nService, int valueDice) {
        return super.getGain()[nService] * valueDice;
    }
}
