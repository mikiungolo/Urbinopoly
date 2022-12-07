import java.util.LinkedList;
import java.util.List;

public class Player {
    // Budget assegnato inizialmente ad ogni singolo giocatore
    private final static int INITIAL_BUDGET = 2000;
    // Numero di quadrati che compongono il tabellone
    private final static int NUM_SQUARES = 40;
    // Somma riscossa tutte le volte che un giocatore passa dal via
    private final static int COLLECTION = 200;

    // Campi della classe
    private final String name;
    private int position;
    private int balance;
    private int escapeAttempts;
    private boolean passedGo;
    private boolean bankrupt;
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
    // Composizione
        this.cards = new LinkedList<>();
        this.properties = new LinkedList<>();
    }

    // Metodi Getter
    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getBalance() {
        return balance;
    }

    public int getEscapeAttempts() {
        return escapeAttempts;
    }


    public List<Cards> getCards() {
        return cards;
    }


    public List<Property> getProperties() {
        return properties;
    }
    

    public boolean isPassedGo() {
        return passedGo;
    }

    public void setPassedGo(boolean passedGo) {
        this.passedGo = passedGo;
    }

    public boolean isBankrupt() {
        return bankrupt;
    }

    public void setBankrupt(boolean bankrupt) {
        this.bankrupt = bankrupt;
    }


    // Metodi della classe
    // Permette di far muovere il giocatore in base alla somma estratta dai dadi
    public void move(int addition) {
        this.position += addition;
        if(this.position >= NUM_SQUARES){
            this.position %= NUM_SQUARES;
            setPassedGo(true);
            manageBalance(COLLECTION);

        }
    }

    // Permette di spostarti direttamente nella casella indicata dalla carta estratta
    public void moveTo(int i){
        this.position = i;
    }

    // Aggiorna il bilancio di ciascun giocatore 
    public void manageBalance(int amount){
        this.balance += amount;
        if(this.balance <= 0){
            setBankrupt(true);
        }
    }

    // Aggiunge proprietà in caso di acquisto
    public void addProperty(Property p){
        this.properties.add(p);
    }

    public void goToPrison(){

    }

    public void exitToPrison(){

    }
       
}
