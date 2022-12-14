import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Player {
    // Budget assegnato inizialmente ad ogni singolo giocatore
    private final static int INITIAL_BUDGET = 2000;
    // Somma riscossa tutte le volte che un giocatore passa dal via
    private final static int COLLECTION = 200;
    // Cauzione per la fuoruscita dalla prigione
    private final static int EXIT_PRISON_CAUTION = 125;
    // Round consecutivi per la carcerazione
    private final static int ROUND_GO_PRISON = 3;

    // Campi della classe
    private final String name;

    private int position;
    private int balance;
    private int escapeAttempts;
    private int nService;
    private int nStation;
    private int nHouseLand;
    private int consecutiveRound;

    private boolean passedGo;
    private boolean bankrupt;
    private boolean isInPrison;

    // Campi di tipo lista della classe
    private Optional<List<Cards.Card>> cards;
    private List<Property> properties;

    // Costruttore classe
    public Player(String name) {
        this.name = name;
        this.position = Board.GO;
        this.balance = INITIAL_BUDGET;
        this.escapeAttempts = 0;
        this.nHouseLand = 0;
        this.nService = 0;
        this.nStation = 0;
        this.passedGo = false;
        this.bankrupt = false;
        this.isInPrison = false;
        // Composizione
        this.cards = Optional.empty();
        this.properties = new LinkedList<>();
    }

    // Metodo Getter
    public String getName() {
        return name;
    }

    // METODI RELATIVI AL MOVIMENTO
    // Metodo Getter
    public int getPosition() {
        return position;
    }

    // Permette di far muovere il giocatore dalla posizione corrente
    public void move(int addition) {
        this.position += addition;
        if (this.position >= Board.N_SQAURES) {
            this.position %= Board.N_SQAURES;
            setPassedGo(true);
            manageBalance(COLLECTION);
        }
    }

    // Permette di spostarti direttamente nella casella indicata
    public void moveTo(int pos) {
        this.position = pos;
    }

    // Metodi Getter e Setter
    public boolean isPassedGo() {
        return passedGo;
    }

    public void setPassedGo(boolean passedGo) {
        this.passedGo = passedGo;
    }

    // METODI RELATIVI ALLA PRIGIONE
    // Metodo Getter

    public int getConsecutiveRound() {
        return consecutiveRound;
    }

    public void setConsecutiveRound(int round) {
        this.consecutiveRound = round;
    }

    public void countConsecutiveRound() {
        this.consecutiveRound++;
    }

    public boolean goPrisonForTripleTurn() {
        countConsecutiveRound();
        if (getConsecutiveRound() == ROUND_GO_PRISON) {
            moveTo(Board.PRISON);
            // reset turni consecutivi
            setConsecutiveRound(0);
            return true;
        }
        return false;
    }

    public int getEscapeAttempts() {
        return escapeAttempts;
    }

    public static int getExitPrisonCaution() {
        return EXIT_PRISON_CAUTION;
    }

    public boolean isInPrison() {
        return isInPrison;
    }

    public void setInPrison(boolean isInPrison) {
        this.isInPrison = isInPrison;
    }

    // Controlla se il giocatore ha effettuato i tentativi di evasione dalla
    // prigione
    public void exitPrisonForEscapeAttempt() {
        if (getEscapeAttempts() == 3) {
            setInPrison(false);
            this.escapeAttempts = 0;
        } else {
            countTurnInPrison();
        }
    }

    // Conta i turni in prigione
    private void countTurnInPrison() {
        this.escapeAttempts++;
    }

    // Uscita di prigione

    // mediante cauzione
    public void exitPrisonToCaution() {
        setInPrison(false);
        manageBalance(-EXIT_PRISON_CAUTION);
    }

    // mediante carta trovata
    public void exitPrisonToCard() {
        /*
         * se ci sono carte sono esclusivamente carte
         * FREE_PRISON, ovvero le uniche che un
         * giocatore può e deve conservare
         */
        cards.ifPresent(cards -> removeCard());
    }

    // Metodo relativi alle carte
    public Optional<List<Cards.Card>> getCards() {
        return cards;
    }

    public void addCard(Cards.Card c) {
        cards.get().add(c);
    }

    private void removeCard() {
        cards.get().remove(0);
        setInPrison(false);
    }

    // METODI RELATIVI ALLA PROPRIETA'
    // Metodi Getter
    public List<Property> getProperties() {
        return properties;
    }

    public int getnService() {
        return nService;
    }

    public int getnStation() {
        return nStation;
    }

    public int getnHouseLand() {
        return nHouseLand;
    }

    // Aggiunge proprietà in caso di acquisto
    public void addProperty(Property p) {
        this.properties.add(p);
        manageBalance(-p.buyProperty(this));
        if (p instanceof Land)
            controlUrbinopoly((Land) p);
        else if (p instanceof Station)
            this.nStation++;
        else
            this.nService++;
    }

    // imposta ipoteca
    public void mortageProp(Property p) {
        manageBalance(p.mortage());
    }

    // rimozione ipoteca
    public void removeMortageProp(Property p) {
        manageBalance(-p.removeMortage());
    }

    // controllo monopolio
    private void controlUrbinopoly(Land p) {
        if (!p.isUrbinopoly()) {
            List<Property> urbinopoly = properties.stream().filter(x -> x instanceof Land)
                    .filter(x -> ((Land) x).getColor().equals(p.getColor()))
                    .collect(Collectors.toList());
            if (urbinopoly.size() == p.getColor().getnLandsColor()) {
                urbinopoly.stream()
                        .forEach(x -> ((Land) x).setUrbinopoly(true));
            }
        }
    }
    //

    // METODI RELATIVI AL BILANCIO DEL GIOCATORE
    // Metodo Getter
    public int getBalance() {
        return balance;
    }

    // Aggiorna il bilancio di ciascun giocatore
    public void manageBalance(int amount) {
        this.balance += amount;
        if (this.balance <= 0) {
            setBankrupt(true);
        }
    }

    // Metodi Getter e Setter
    public boolean isBankrupt() {
        return bankrupt;
    }

    public void setBankrupt(boolean bankrupt) {
        this.bankrupt = bankrupt;
    }

    // Rimuove le proprietà e le carte se il giocatore perde
    public void losePlayer() {
        properties.stream().forEach(p -> p.releaseProperty());
        properties.clear();
        cards.ifPresent(cards -> cards.clear());
    }
}
