// modellazione i una proprietà
public class Property extends Square {

    // moltiplicatore tassa per disipotecare
    private static final double FEE = 1.1;

    // stato delle proprietà
    private String owner; // nome proprietario
    private boolean isOwner; // ha un proprietario?
    private boolean isMortaged; // è ipotecata?
    private final int price; // prezzo di compera
    private final int[] gain; // indice ricavi

    // costruttore della classe
    public Property(String name, int price, int[] gain) {
        super(name, false);
        this.owner = "";
        this.isOwner = false;
        this.isMortaged = false;
        this.price = price;
        this.gain = gain;
    }

    // utility getter e setter
    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setOwner(boolean isOwner) {
        this.isOwner = isOwner;
    }

    // gestione acquisti
    public int getPrice() {
        return price;
    }

    public void buy() {

    }

    // gestione ricavi
    public int[] getGain() {
        return gain;
    }

    // gestione ipoteca
    public boolean isMortaged() {
        return isMortaged;
    }

    public void setMortaged(boolean isMortaged) {
        this.isMortaged = isMortaged;
    }

    public double mortage() {
        this.setMortaged(true);
        return (double) getPrice() / 2;
    }

    public double removeMortage() {
        this.setMortaged(false);
        return (double) getPrice() / 2 * FEE;
    }
}
