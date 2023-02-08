package Model.ModelGame.Board.Pieces.Property;

import Model.ModelGame.Player.Player;

// interfaccia per la gestione di una Proprietà 
public interface Management {

    public void releaseProperty();

    public int mortage();

    public int removeMortage();

    public int buyProperty(Player p);
}
