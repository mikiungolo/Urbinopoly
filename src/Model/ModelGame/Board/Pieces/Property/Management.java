package Model.ModelGame.Board.Pieces.Property;

import Model.ModelGame.Player.Player;

// interfaccia per la gestione di una Proprietà 
public interface Management {

    // rilascia la proprietà
    public void releaseProperty();

    // ipoteca proprietà
    public int mortage();

    // rimuove un'ipoteca
    public int removeMortage();

    // compera proprietà
    public int buyProperty(Player p);
}
