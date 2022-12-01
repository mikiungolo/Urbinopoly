// Classe astratta per la creazione dei quadrati
public abstract class Square {
    // Campi Square
    private final String name;

    private boolean isBusy;
    
     // Costruttore della classe
    public Square(String name, boolean isBusy) {
        this.name = name;
        this.isBusy = isBusy;
    }

    // Metodi Getter
    public String getName() {
        return name;
    }

    public boolean isBusy() {
        return isBusy;
    }

    // Metodo Setter
    public void setBusy(boolean isBusy) {
        this.isBusy = isBusy;
    }
}
