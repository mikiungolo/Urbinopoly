import java.util.LinkedList;
import java.util.List;

public class Player {
    // Budget assegnato inizialmente ad ogni singolo giocatore
    private final static int INITIAL_BUDGET = 2000;
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
        if(this.position >= Board.N_SQAURES){
            this.position %= Board.N_SQAURES;
            setPassedGo(true);
            manageBalance(COLLECTION);

        }
    }

    // Permette di spostarti direttamente nella casella indicata 
    public void moveTo(int pos){
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

    // Porta il giocatore in prigione
    public void goToPrison(){
        if(this.position == Board.GO_TO_PRISON){
            moveTo(Board.PRISON);
        }
    }

    // Fa uscire il giocatore di prigione
    public void exitToPrison(){

    }


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
    public void addProperty(Property p){
        this.properties.add(p);
        manageBalance(-p.buyProperty(this));
    }

    // Rimuove le proprietà e le carte se il giocatore perde
    public void losePlayer(){
        if(isBankrupt()){
            for (Property p : properties) {
                this.properties.remove(p);
            }
            for (Cards c : cards) {
                this.cards.remove(c);
            }
        }
    }
    
    // imposta ipoteca
    public void mortageProp(Property p){
        manageBalance(p.mortage());
    }

    // rimozione ipoteca 
    public void removeMortageProp(Property p){
        manageBalance(-p.removeMortage());
    }

 
    // METODI RELATIVI AL BILANCIO DEL GIOCATORE
    // Metodo Getter
    public int getBalance() {
        return balance;
    }

    // Aggiorna il bilancio di ciascun giocatore 
    public void manageBalance(int amount){
        this.balance += amount;
        if(this.balance <= 0){
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
 
       
}
