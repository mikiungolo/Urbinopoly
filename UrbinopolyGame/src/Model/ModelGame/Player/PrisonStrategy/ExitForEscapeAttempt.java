package ModelGame.Player.PrisonStrategy;

import ModelGame.Player.Player;

public class ExitForEscapeAttempt implements ExitPrisonStrategy {

    /*
     * Controlla che il Player venga scarcerato a
     * seguito dei turni necessari di pena
     */
    @Override
    public void freePrison(Player p) {
        if (p.getEscapeAttempts() == 3) {
            p.setInPrison(false);
            p.setEscapeAttempts(0);
        } else {
            p.setEscapeAttempts(p.getEscapeAttempts() + 1);
        }
    }
}
