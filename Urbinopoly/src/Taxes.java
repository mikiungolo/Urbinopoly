public class Taxes<T> extends Square{

    // Definizione della costante per le tasse
    private final T rate;

    public Taxes(String name, T rate) {
        super(name);
        this.rate = rate;
    }
    
    public interface TaxApi<T>{
        public T getRate();
    }

    TaxApi<T> value = () -> extracted();

    private T extracted() {
        return this.rate;
    }
}
