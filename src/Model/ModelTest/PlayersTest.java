package Model.ModelTest;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import Model.ModelGame.Player.Player;
import Model.ModelGame.Player.Players;

public class PlayersTest {

    List<String> play = new LinkedList<>();

    public List<String> createPlayers() {
        play.add(("Gio"));
        play.add(("Va"));
        play.add(("ni"));
        return play;
    }

    Players p = new Players(createPlayers());

    // stessa logica applicata poi in Urbinopoly per test
    @Test
    public void testGetNextPlayer() {
        int firstIndex = -1;
        for (int i = 0; i < 100; i++) {
            Player pa = p.getNextPlayer(firstIndex);
            assertTrue(p.getInGame().indexOf(pa) != firstIndex);
            firstIndex = p.getInGame().indexOf(pa);
        }
    }
}
