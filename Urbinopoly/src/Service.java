// Modellazione di una proprietà Servizio
public class Service extends Property {

    // costruttore
    public Service(String name, int price, int[] gain) {
        super(name, price, gain, TypeProperty.SERVICE);
    }

    // seguirà al guadagno di un servizio il moltiplicatore dei dadi
    @Override
    public int getRent(int nService) {
        return super.getGain()[nService];
    }
}
