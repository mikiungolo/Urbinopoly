package ModelGame.Player.PrisonStrategy;

import ModelGame.Dice.*;
import ModelGame.Player.Player;

public class ExitForEscapeAttempt implements ExitPrisonStrategy {

    /*
     * Controlla che il Player venga scarcerato a
     * seguito dei turni necessari di pena, o
     * provocando un doppio valore ai dadi
     */
    @Override
    public void freePrison(Player p) {
        DiceApi d = new Dice();
        d.roll();
        if (p.getEscapeAttempts() == 3 || d.isDouble()) {
            p.setInPrison(false);
            p.setEscapeAttempts(0);
        } else {
            p.setEscapeAttempts(p.getEscapeAttempts() + 1);
            p.setOptionRolled(true);
        }
    }
}
