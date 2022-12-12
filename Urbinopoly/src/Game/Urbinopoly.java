// classe per la gestione dell'intero gioco
public class Urbinopoly {
    // composizione
    private Board board;
    private Players players;
    private DiceApi dice;

    private boolean inTurn;

    public Urbinopoly(Players players) {
        this.board = new Board();
        this.players = players;
        this.dice = new Dice();

        this.inTurn = true;
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

    /* gestione di un turno generalizzato */
    public void turn(Player p) {
        inTurn = true;
        do {
            dice.roll();
            p.move(dice.getTotalValue());
            doAction(p);
            // ci deve essere l'attesa del player per la fine del turno
            // in modo taleche inTurn si setti falsa e il turno termini
        } while (inTurn);
    }

    /*
     * azioni che si verificano all'acquisizione
     * del player corrente in corrispondenza
     * del suo posizionamento sul tabellone
     */
    private void doAction(Player p) {
        Square currentSquare = getBoard().getSquare(p.getPosition());

        switch (currentSquare.getNature()) {
            case GO_TO_PRISON -> {
                p.moveTo(Board.PRISON);
                p.setInPrison(true);
            }
            case INCOME_TAX -> {
                taxAction(currentSquare, p);
            }
            case LAND -> {
                propertyAction(p, currentSquare, ((Land) currentSquare).getRent());
            }
            case LUXURY_TAX -> {
                taxAction(currentSquare, p);
            }
            case PRISON -> {

            }
            case PROBABILITY -> {
                cardAction(getBoard().getProb().takeCard(), p);
            }
            case SERVICE -> {
                propertyAction(p, currentSquare,
                        ((Service) currentSquare).getRent(((Service) currentSquare).getOwner().get().getnService(), 0));
            }
            case STATION -> {
                propertyAction(p, currentSquare,
                        ((Station) currentSquare).getRent(((Station) currentSquare).getOwner().get().getnStation()));
            }
            case UNEXPECTED -> {
                cardAction(getBoard().getUnex().takeCard(), p);
            }
            default -> {
            }
        }
    }

    // azioni carte
    private void cardAction(Cards.Card c, Player p) {
        switch (c.getId()) {
            case ID_BALANCE -> {
                p.manageBalance(c.getAction());
            }
            case ID_MOVE -> {
                p.move(c.getAction());
            }
            case ID_MOVE_TO -> {
                p.moveTo(c.getAction());
            }
            case ID_FREE_PRISON -> {
                p.addCard(c);
            }
        }
    }

    // azioni tasse
    private void taxAction(Square s, Player p) {
        p.manageBalance(-(int) ((Taxes<?>) s).getRate() * p.getBalance());

    }

    // azioni proprietà
    private void propertyAction(Player p, Square s, int amount) {
        if (((Property) s).isOwner() && !p.getProperties().contains(s)) {
            p.manageBalance(-amount);
            ((Property) s).getOwner().get().manageBalance(amount);
        }
    }
}
