// Classe astratta per la creazione dei quadrati
public abstract class Square {
    // Campi Square
    private final String name; // nome quadrato

    private boolean isBusy; // è nel quadrato?

    // Costruttore della classe
    public Square(String name) {
        this.name = name;
        this.isBusy = false;
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

    // creazione inner class Go
    public static class Go extends Square {
        // costruttore classe
        public Go() {
            super("Go");
        }
    }

    // creazione inner class Prison
    public static class Prison extends Square {
        // costruttore classe
        public Prison() {
            super("Prison");
        }
    }

    // creazione inner class Free Parking
    public static class Parking extends Square {
        // costruttore della classe
        public Parking() {
            super("Parking");
        }
    }

    // creazione inner class Go_To_Prison
    public static class GoToPrison extends Square {
        // costruttore della classe
        public GoToPrison() {
            super("Go to Prison");
        }
    }
}
