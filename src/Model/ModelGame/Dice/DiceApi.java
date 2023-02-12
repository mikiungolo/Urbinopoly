package Model.ModelGame.Dice;

// Interfaccia per implementazione dadi
public interface DiceApi {

    // rolla i dadi
    public void roll();

    // indica se i daddi ottengono doppio valore
    public boolean isDouble();

    // risultato ottenuto
    public int getTotalValue();

    // getter
    public int[] getDice();
}
