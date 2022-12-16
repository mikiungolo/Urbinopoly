package ModelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import ModelGame.Urbinopoly;
import ModelGame.Player.Player;
import ModelGame.Player.Players;

public class UrbinopolyTest {

    List<Player> play = new LinkedList<>();

    public List<Player> createPlayers() {
        play.add(new Player("Gio"));
        play.add(new Player("Va"));
        play.add(new Player("ni"));
        return play;
    }

    Players p = new Players(createPlayers());
    Urbinopoly u = new Urbinopoly(p);

    // il metodo è privato, richiede visibilità pubblica per il test
    @Test
    public void testAction() {
        // quadrato di partenza non mi aspetto nulla
        // è stata gia testata la posizione corretta
        Player current = u.getPlayers().getNextPlayer(-1);
        u.doAction(current);

        // TESTO AZIONE SULLE CARTE UNEX/PROB
        current.moveTo(2);
        assertEquals(2, current.getPosition());
        for (int i = 0; i < 100; i++) {
            int balance = current.getBalance();
            int position = current.getPosition();
            int nCard = current.getCards().size();

            u.doAction(current);

            // mi aspetto che per ogni pescata uno dei seguenti valori
            // venga sempre modificato dalla carte prob / unex

            assertTrue((current.getBalance() != balance) || (current.getPosition() != position)
                    || (current.getCards().size() != nCard));

            if (current.getPosition() != 2) {
                current.moveTo(2);
                current.setInPrison(false);
            }
        }

        // TESTO LE AZIONI DELLE TASSE
        current = p.getNextPlayer(p.getInGame().indexOf(current));
        current.moveTo(4);
        for (int i = 0; i < 5; i++) {
            int b = current.getBalance();
            u.doAction(current);
            assertNotEquals(b, current.getBalance());
            b = current.getBalance();
        }

        current.moveTo(38);
        for (int i = 0; i < 5; i++) {
            int b = current.getBalance();
            u.doAction(current);
            assertNotEquals(b, current.getBalance());
            assertEquals(b - 200, current.getBalance());
            b = current.getBalance();
        }
    }

    @Test
    public void testTurn() {

    }
}
