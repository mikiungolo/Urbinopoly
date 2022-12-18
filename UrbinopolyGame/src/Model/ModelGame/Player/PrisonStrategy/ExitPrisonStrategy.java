package ModelGame.Player.PrisonStrategy;

import ModelGame.Player.Player;

// interfaccia di strategia per la scarcerazione di un Player.

public interface ExitPrisonStrategy {

    // strategia algoritmica da implementare
    public void freePrison(Player p);
}
