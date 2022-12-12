// classe per la gestione dell'intero gioco
public class Urbinopoly {
    // composizione
    private Board board;
    private Players players;
    private DiceApi dice;

    public Urbinopoly() {
        this.board = new Board();
        this.players = new Players(null);
        this.dice = new Dice();
    }

    // getter
    public Board getBoard() {
        return board;
    }

    public Players getPlayers() {
        return players;
    }

    public DiceApi getDice() {
        return dice;
    }

    // Inizio del primo turno un Player casuale
    public void doAction() {
        Square current = b.getSquare(this.getPosition());
        switch (current.getNature()) {
        case GO -> {

        }
        case GO_TO_PRISON -> {
        this.moveTo(Board.PRISON);
        this.setInPrison(true);
        }
        case INCOME_TAX -> {
        manageBalance((int) (-((Taxes<Double>) current).getRate() *
        this.getBalance()));
        }
        case LAND -> {
        if()
        }
        case LUXURY_TAX -> {

        }
        case PARKING -> {

        }
        case PRISON -> {

        }
        case PROBABILITY -> {

        }
        case SERVICE -> {

        }
        case STATION -> {

        }
        case UNEXPECTED -> {

        }
        default -> {

        }

        }
    }
}
