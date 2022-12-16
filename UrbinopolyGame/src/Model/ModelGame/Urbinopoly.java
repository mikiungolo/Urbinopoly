package ModelGame;

import ModelGame.Board.Board;
import ModelGame.Board.Pieces.Square;
import ModelGame.Board.Pieces.Deck.Card;
import ModelGame.Board.Pieces.Property.*;
import ModelGame.Board.Pieces.Taxes.Taxes;
import ModelGame.Dice.*;
import ModelGame.Player.*;

// classe per la gestione dell'intero gioco
public class Urbinopoly {
    // composizione
    private final Board board;
    private final Players players;
    private final DiceApi dice;

    private boolean inTurn;
    private boolean endGame;

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
    public void turn(Player p /* int option, int decision */) {
        // se è l'unico Player in gioco ha vinto
        winner();

        inTurn = true;

        // la decisione (come la prop in questione) non può essere presa prima del
        // turno. Sistemare!!!
        do {
            // if (decision > 0 && decision <= 4)
            // playerAction(p, decision);

            dice.roll();
            p.move(dice.getTotalValue());

            doAction(p);
            // controllo sconfitta del Player con eventuale rimozione
            getPlayers().remove(p);

            // il Player vorrebbe rifare una sua azione personale (?)
            // playerAction(p);

            /*
             * fin tanto che il player lanciando i dadi riceve facciate
             * uguali deve giocare un ulteriore turno, altrimenti toccato
             * il limite dei massimi turni consecutivi finirà in prigione.
             * Tale operazione avviene in chiamata ricorsiva.
             */
            if (!p.goPrisonForTripleTurn()) {
                turn(p);
            }

            // ci deve essere l'attesa del player per la fine del turno
            // in modo tale che inTurn si setti falsa e il turno termini

        } while (inTurn);
    }

    private void winner() {
        if (getPlayers().getInGame().size() == 1) {
            this.endGame = true;
        }
    }

    /*
     * azioni che si verificano all'acquisizione
     * del player corrente in corrispondenza
     * del suo posizionamento sul tabellone.
     * Tali azioni sono automatiche poichè esenti da
     * ogni optione dei Players
     */
    public void doAction(Player p) {
        Square currentSquare = getBoard().getSquare(p.getPosition());

        switch (currentSquare.getNature()) {
            case GO_TO_PRISON -> {
                p.moveTo(Board.PRISON);
                p.setInPrison(true);
                // si inziano a contare i turni in prigione
                p.exitPrisonForEscapeAttempt();
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
                p.exitPrisonForEscapeAttempt();
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
                return;
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
                if (p.getBalance() > Player.getExitPrisonCaution())
                    p.exitPrisonToCaution();
            }
            case 2 -> p.exitPrisonToCard();
        }
    }

    /*
     * Modellazione di tutte quelle le azioni in cui
     * è il Player, a determinate condizioni,
     * il protagonista dell'evolversi
     * della partita di gioco
     */
    public void playerAction(Player p, Property prop, int option) {
        // se il player è in prigione decide come e se uscire
        if (p.isInPrison()) {
            prisonAction(p, option);
        } else {
            /*
             * in ogni turno il player può prendere optioni
             * sulle sue proprietà a condizioni soddisfatte
             */
            switch (option) {
                case 1 -> {
                    // opzione di ipoteca
                    p.manipulateProperty(prop, !prop.isMortaged(), prop.mortage());
                }
                case 2 -> {
                    // opzione di rimozione ipoteca
                    p.manipulateProperty(prop, prop.isMortaged(), prop.removeMortage());
                }
                case 3 -> {
                    // opzione di costruzione casa
                    Land l = (Land) prop;
                    p.manipulateProperty(l, l.build(), l.buildHouse());
                }
                case 4 -> {
                    Land l = (Land) prop;
                    p.manipulateProperty(l, l.remove(), l.removeHouse());
                }
            }
        }

    }
}
