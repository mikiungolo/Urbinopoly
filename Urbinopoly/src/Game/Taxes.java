// classe generalizzata che permette di definire diverse tipolgie di tasse
public class Taxes<T> extends Square {

    // Definizione della costante per le tasse
    private final T rate;

    public Taxes(String name, T rate) {
        super(name);
        this.rate = rate;
    }

    // restituisce la tassa
    public T getRate() {
        return rate;
    }
}
