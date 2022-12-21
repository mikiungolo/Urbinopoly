package Model.ModelGame.Dice;

// Interfaccia per implementazione dadi
public interface DiceApi {

    public void roll();

    public boolean isDouble();

    public int getTotalValue();
}
