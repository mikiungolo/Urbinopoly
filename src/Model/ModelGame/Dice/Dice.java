package Model.ModelGame.Dice;

import java.util.Random;

// creazione della classe per la creazione dei dadi 
public class Dice implements DiceApi {

    // Costante numero dadi
    private final static int NUM_DICE = 2;
    // Array per rappresentare dadi
    private int[] dice = new int[NUM_DICE];
    private Random rnd;
    private int totalValue;
    private boolean isDouble;

    public Dice() {
        this.rnd = new Random();
        this.totalValue = 0;
    }

    @Override
    public int[] getDice() {
        return dice;
    }

    // Estrae 2 numeri casuali assegnandoli ai dadi
    @Override
    public void roll() {
        setDouble(false);
        setTotalValue(0);
        for (int i = 0; i < NUM_DICE; i++) {
            dice[i] = rnd.nextInt(6) + 1;
            this.totalValue += dice[i];
        }
        if (dice[0] == dice[1])
            isDouble = true;
    }

    // Ritorna se i due numeri casuali estratti sono uguali
    @Override
    public boolean isDouble() {
        return this.isDouble;
    }

    private void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    private void setDouble(boolean isDouble) {
        this.isDouble = isDouble;
    }

    @Override
    public int getTotalValue() {
        return this.totalValue;
    }
}
