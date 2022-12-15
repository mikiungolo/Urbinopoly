package ModelGame.Board.Pieces;

// Classe astratta per la creazione dei quadrati
public abstract class Square {

    // Definizione dei tipi di quadrati
    public enum Type {
        GO,
        PRISON,
        PARKING,
        GO_TO_PRISON,
        LAND,
        SERVICE,
        STATION,
        INCOME_TAX,
        LUXURY_TAX,
        PROBABILITY,
        UNEXPECTED
    };

    // Campi Square
    private final String name; // nome quadrato
    private final Type nature;

    // Costruttore della classe
    public Square(String name, Type nature) {
        this.name = name;
        this.nature = nature;
    }

    // Metodi Getter
    public String getName() {
        return name;
    }

    public Type getNature() {
        return nature;
    }

    // creazione inner class Go
    public static class Go extends Square {
        // costruttore classe
        public Go() {
            super(Type.GO.name(), Type.GO);
        }
    }

    // creazione inner class Prison
    public static class Prison extends Square {
        // costruttore classe
        public Prison() {
            super(Type.PRISON.name(), Type.PRISON);
        }
    }

    // creazione inner class Free Parking
    public static class Parking extends Square {
        // costruttore della classe
        public Parking() {
            super(Type.PARKING.name() + " MERCATALE", Type.PARKING);
        }
    }

    // creazione inner class Go_To_Prison
    public static class GoToPrison extends Square {
        // costruttore della classe
        public GoToPrison() {
            super(Type.GO_TO_PRISON.name(), Type.GO_TO_PRISON);
        }
    }

    // quadrato Probability che indica il mazzo da cui pescare
    public static class Probability extends Square {

        // costruttore
        public Probability() {
            super(Type.PROBABILITY.name(), Type.PROBABILITY);
        }

    }

    // quadrato Unexpected che indica il mazzo da cui pescare
    public static class Unexpected extends Square {

        // costruttore
        public Unexpected() {
            super(Type.UNEXPECTED.name(), Type.UNEXPECTED);
        }
    }
}
