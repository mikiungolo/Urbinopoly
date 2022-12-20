package Model.ModelGame.Player.PrisonStrategy;

import Model.ModelGame.Player.Player;

public class ExitWithCaution implements ExitPrisonStrategy {

    /* Scarcerazione mediante pagamento di cauzione */
    @Override
    public void freePrison(Player p) {
        p.setInPrison(false);
        p.manageBalance(Player.getExitPrisonCaution());
    }
}
