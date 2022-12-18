package ModelGame.Player.PrisonStrategy;

import ModelGame.Player.Player;

public class ExitWithCard implements ExitPrisonStrategy {

    @Override
    public void freePrison(Player p) {
        /*
         * se ci sono carte sono esclusivamente carte
         * FREE_PRISON, ovvero le uniche che un
         * giocatore può e deve conservare
         */
        p.getCards().remove(0);
        p.setInPrison(false);
    }

}
