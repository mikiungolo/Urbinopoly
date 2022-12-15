package ModelGame.Board.Pieces.Property;

import java.util.Optional;

import ModelGame.Board.Pieces.Square;
import ModelGame.Player.Player;

// modellazione di una proprietà
public class Property extends Square {

    // moltiplicatore tassa per disipotecare
    private static final double FEE = 1.1;

    // stato delle proprietà

    private Optional<Player> owner; // Player proprietario

    private boolean isOwner; // ha un proprietario?
    private boolean isMortaged; // è ipotecata?

    private final int price; // prezzo di compera
    private final int[] gain; // indice ricavi

    // costruttore della classe
    public Property(String name, int price, int[] gain, Type t) {
        super(name, t);
        this.owner = Optional.empty();
        this.isOwner = false;
        this.isMortaged = false;
        this.price = price;
        this.gain = gain;
    }

    // gestione proprietario
    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(Player owner) {
        this.owner = Optional.ofNullable(owner);
    }

    public void setOwner(boolean isOwner) {
        this.isOwner = isOwner;
    }

    public Optional<Player> getOwner() {
        return this.owner;
    }

    // si rilascia una proprietà quando un Player perde
    public void releaseProperty() {
        this.owner = Optional.empty();
        this.isOwner = false;
        this.isMortaged = false;
    }

    // gestione acquisti
    public int getPrice() {
        return price;
    }

    // gestione ricavi
    public int[] getGain() {
        return gain;
    }

    public int buyProperty(Player p) {
        setOwner(p);
        setOwner(true);
        return getPrice();
    }

    // gestione ipoteca
    public boolean isMortaged() {
        return isMortaged;
    }

    public void setMortaged(boolean isMortaged) {
        this.isMortaged = isMortaged;
    }

    public int mortage() {
        this.setMortaged(true);
        return (int) (getPrice() / 2);
    }

    public int removeMortage() {
        this.setMortaged(false);
        return (int) (getPrice() / 2 * FEE);
    }
}
