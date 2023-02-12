package Model.ModelGame.Player;

import Model.ModelGame.Board.Pieces.Property.Property;

// interfaccia per modellazione di un Player 
public interface PlayerApi {

    // modellazione del movimento del player
    public void move(int add);

    // salto di posizione del player
    public void moveTo(int pos);

    // carcerazione per doppio valore dei dadi
    public boolean goPrisonForTripleTurn();

    // modellazione della proprietà
    public void manipulateProperty(Property p, boolean cond, int amount);

    // gestione del bilancio
    public void manageBalance(int amount);
}
