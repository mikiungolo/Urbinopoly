package ModelGame;

import ModelGame.Board.Board;
import ModelGame.Board.Pieces.Square;
import ModelGame.Board.Pieces.Deck.Card;
import ModelGame.Board.Pieces.Property.*;
import ModelGame.Board.Pieces.Taxes.Taxes;
import ModelGame.Dice.*;
import ModelGame.Player.*;
import ModelGame.Player.PrisonStrategy.ExitForEscapeAttempt;
import ModelGame.Player.PrisonStrategy.ExitWithCard;
import ModelGame.Player.PrisonStrategy.ExitWithCaution;

// classe per la gestione dell'intero gioco
public class Urbinopoly {
    // composizione
    private final Board board;
    private final Players players;
    private final DiceApi dice;

    private boolean inTurn;
    private boolean endGame;

    public Urbinopoly(Players players) {
        this.board = Board.getSingletonBoard();
        this.players = players;
        this.dice = new Dice();

        this.inTurn = true;
        this.endGame = false;
    }

    // getter e setter
    public Board getBoard() {
        return board;
    }

    public Players getPlayers() {
        return players;
    }

    public DiceApi getDice() {
        return dice;
    }

    public boolean isInTurn() {
        return inTurn;
    }

    public void setInTurn(boolean inTurn) {
        this.inTurn = inTurn;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    // struttura dell'intero gameplay
    public void gameplay() {

        // esecuzione dei turni
        while (!endGame()) {
            turn(players.getNextPlayer(-1));
        }

        // il gioco deve terminare!!
        if (endGame()) {

        }
    }

    /* gestione di un turno generalizzato */
    public void turn(Player p) {

        setInTurn(true);
        p.setOptionRolled(false);

        /*
         * fin tanto che il player corrente non ha selezionato
         * il tiro dei dadi e non ha espresso la fine del proprio turno può continuare
         * le proprie mosse di gioco
         */
        while (!p.isOptionRolled() || isInTurn()) {

            /*
             * nel corso del proprio turno il Player corrente può
             * optare per tutte le opzioni a lui disponibili
             */
            playerAction(p, p.getOptionCommand());

            // se indica l'opzione di tiro si esegue il giro
            if (p.isOptionRolled()) {
                dice.roll();
                p.move(dice.getTotalValue());

                doAction(p);
                // controllo sconfitta del Player con eventuale rimozione
                getPlayers().remove(p);

                /*
                 * fin tanto che il player lanciando i dadi riceve facciate
                 * uguali deve giocare un ulteriore turno, altrimenti toccato
                 * il limite dei massimi turni consecutivi finirà in prigione.
                 * Tale operazione avviene in chiamata ricorsiva.
                 */
                if (!p.goPrisonForTripleTurn() && dice.isDouble()) {
                    turn(p);
                }
            }

        }
    }

    private boolean endGame() {
        if (getPlayers().getInGame().size() == 1) {
            this.endGame = true;
        }
        return this.endGame;
    }

    /*
     * azioni che si verificano all'acquisizione
     * del player corrente in corrispondenza
     * del suo posizionamento sul tabellone.
     * Tali azioni sono automatiche poichè esenti da
     * ogni decisione dei Players
     */
    public void doAction(Player p) {
        Square currentSquare = getBoard().getSquare(p.getPosition());

        switch (currentSquare.getNature()) {
            case GO_TO_PRISON -> {
                p.moveTo(Board.PRISON);
                p.setInPrison(true);
                // si inziano a contare i turni in prigione
                p.setPrisonStrategy(new ExitForEscapeAttempt());
                p.applyStrategy();
            }
            case INCOME_TAX -> {
                IncomeAction(currentSquare, p);
            }
            case LAND -> {
                propertyAction(p, currentSquare, ((Land) currentSquare).getRent());
            }
            case LUXURY_TAX -> {
                LuxuryAction(currentSquare, p);
            }
            case PRISON -> {
                // si iniziano a contare i turni in prigione
                p.setPrisonStrategy(new ExitForEscapeAttempt());
                p.applyStrategy();
                // if (p.getEscapeAttempts() > 1)
                // prisonAction(p);
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
    private void cardAction(Card c, Player p) {
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
                p.getCards().add(c);
            }
        }
    }

    @SuppressWarnings({ "unchecked" })
    // azioni tasse
    private void IncomeAction(Square s, Player p) {
        if (s instanceof Taxes<?>) {
            p.manageBalance((int) ((((Taxes<Double>) s).getRate()) * p.getBalance()));
        }
    }

    @SuppressWarnings({ "unchecked" })
    private void LuxuryAction(Square s, Player p) {
        if (s instanceof Taxes<?>) {
            p.manageBalance(-((Taxes<Integer>) s).getRate());
        }
    }

    // azioni proprietà
    private void propertyAction(Player p, Square s, int amount) {
        /*
         * per avvenire un pagamento/riscossione la proprietà
         * fissata deve avere un proprietario diverso da quello
         * corrente che non è in prigione.
         */
        if (((Property) s).isOwner() && !p.controlOwned((Property) s) &&
                !((Property) s).getOwner().get().isInPrison()) {
            p.manageBalance(-amount);
            ((Property) s).getOwner().get().manageBalance(amount);
        }
    }

    // azione prigione
    private void prisonAction(Player p, int option) {
        /*
         * Quando il Player è in prigione può decidere
         * se scagionarsi prematuramente pagando una
         * cauzione o utilizzando carte speciali
         */
        switch (option) {
            case 1 -> {
                if (p.getBalance() > Player.getExitPrisonCaution()) {
                    p.setPrisonStrategy(new ExitWithCaution());
                    p.applyStrategy();
                }
            }
            case 2 -> {
                p.setPrisonStrategy(new ExitWithCard());
                p.applyStrategy();
            }
            default -> {
            }
        }
    }

    /*
     * Modellazione di tutte quelle azioni in cui
     * è il Player, a determinate condizioni,
     * il protagonista dell'evolversi
     * della partita di gioco
     */
    public void playerAction(Player p, int option) {
        // se il player è in prigione decide come e se uscire
        if (p.isInPrison()) {
            prisonAction(p, p.getOptionCommand());
        } else {
            /*
             * in ogni turno il player può prendere decisioni
             * sulle sue proprietà a condizioni soddisfatte
             */

            switch (p.getOptionCommand()) {
                case 1 -> {
                    Property prop = p.getProperties().get(p.getPropertySelected());
                    // opzione di acquisto proprietà
                    if (p.getPosition() == getBoard().getPositionSquare(prop.getName())
                            && !prop.isOwner()) {
                        p.addProperty(prop);
                    }
                }
                case 2 -> {
                    // opzione di ipoteca
                    Property prop = p.getProperties().get(p.getPropertySelected());
                    p.manipulateProperty(prop, !prop.isMortaged(), prop.mortage());
                }
                case 3 -> {
                    // opzione di rimozione ipoteca
                    Property prop = p.getProperties().get(p.getPropertySelected());
                    p.manipulateProperty(prop, prop.isMortaged(), prop.removeMortage());
                }
                case 4 -> {
                    // opzione di costruzione casa
                    Land l = (Land) p.getProperties().get(p.getPropertySelected());
                    ;
                    p.manipulateProperty(l, l.build(), l.buildHouse());
                }
                case 5 -> {
                    // opzione di rimozione casa
                    Land l = (Land) p.getProperties().get(p.getPropertySelected());
                    ;
                    p.manipulateProperty(l, l.remove(), l.removeHouse());
                }
                case 6 -> {
                    // opzione di lancio dadi
                    p.setOptionRolled(true);
                }
                case 7 -> {
                    // opzione di fine turno
                    if (p.isOptionRolled()) {
                        setInTurn(false);
                    }
                }
                default -> {
                }
            }
        }
    }
}
