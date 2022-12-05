// Classe astratta per la creazione dei quadrati
public abstract class Square {
    // Campi Square
    private final String name; // nome quadrato

    // Costruttore della classe
    public Square(String name) {
        this.name = name;
    }

    // Metodi Getter
    public String getName() {
        return name;
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
            super("Parcheggio Mercatale");
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
