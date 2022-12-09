import java.util.LinkedList;
import java.util.List;

public class Player {
    // Budget assegnato inizialmente ad ogni singolo giocatore
    private final static int INITIAL_BUDGET = 2000;
    // Somma riscossa tutte le volte che un giocatore passa dal via
    private final static int COLLECTION = 200;
    private final static int EXIT_PRISON_PAYMENT = 125;

    // Campi della classe
    private final String name;
    private int position;
    private int balance;
    private int escapeAttempts;
    private boolean passedGo;
    private boolean bankrupt;
    private boolean isInPrison;
    // Campi di tipo lista della classe
    private List<Cards> cards;
    private List<Property> properties;

    // Costruttore classe
    public Player(String name, int position) {
        this.name = name;
        this.position = position;
        this.balance = INITIAL_BUDGET;
        this.escapeAttempts = 0;
        this.passedGo = false;
        this.bankrupt = false;
        this.isInPrison = false;
        // Composizione
        this.cards = new LinkedList<>();
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
    public int getEscapeAttempts() {
        return escapeAttempts;
    }

    public boolean isInPrison() {
        return isInPrison;
    }

    public void setInPrison(boolean isInPrison) {
        this.isInPrison = isInPrison;
    }

    // Porta il giocatore in prigione
    // public void goToPrison() {
    // if (this.position == Board.GO_TO_PRISON) {
    // moveTo(Board.PRISON);
    // setInPrison(true);
    // }
    // }

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

    //

    // public void exitToPrison(){
    // if(this.pay == true){
    // manageBalance(-EXIT_PAYMENT);
    // }else if(this.hasCard == true){
    // this.cards.remove();
    // }
    // }

    // Metodo Getter
    public List<Cards> getCards() {
        return cards;
    }

    // METODI RELATIVI ALLA PROPRIETA'
    // Metodo Getter
    public List<Property> getProperties() {
        return properties;
    }

    // Aggiunge proprietà in caso di acquisto
    public void addProperty(Property p) {
        this.properties.add(p);
        manageBalance(-p.buyProperty(this));
    }

    // imposta ipoteca
    public void mortageProp(Property p) {
        manageBalance(p.mortage());
    }

    // rimozione ipoteca
    public void removeMortageProp(Property p) {
        manageBalance(-p.removeMortage());
    }

    // numero case/hotel

    // controllo monopolio

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
        if (isBankrupt()) {
            properties.stream().forEach(properties::remove);
            cards.stream().forEach(cards::remove);
        }
    }

    /*
     * il player è responsabile di tutte le azioni
     * che si verificano durante il corso della partita
     * che dovranno essere gestite a seconda dei casi
     */
    public void doAction(Board b) {
        switch (b.getSquare(this.getPosition()).getNature()) {
            case GO -> {

            }
            case GO_TO_PRISON -> {
                this.moveTo(Board.PRISON);
                this.setInPrison(true);
            }
            case INCOME_TAX -> {
                manageBalance(b.getSquare(4).getRate());
            }
            case LAND -> {

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
