package Model.ModelGame.Player.PrisonStrategy;

import Model.ModelGame.Player.Player;

// interfaccia di strategia per la scarcerazione di un Player.

public interface ExitPrisonStrategy {

    // strategia algoritmica da implementare
    public void freePrison(Player p);
}
