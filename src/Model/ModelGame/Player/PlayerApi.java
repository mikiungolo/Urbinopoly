package Model.ModelGame.Player;

import Model.ModelGame.Board.Pieces.Property.Property;

// interfaccia per modellazione di un Player 
public interface PlayerApi {

    public void move(int add);

    public void moveTo(int pos);

    public boolean goPrisonForTripleTurn();

    public void manipulateProperty(Property p, boolean cond, int amount);

    public void manageBalance(int amount);
}
