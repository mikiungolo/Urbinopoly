package Model.ModelGame.Player;

import java.util.LinkedList;
import java.util.List;

// modellazione dell'insieme dei giocatori
public class Players {

    private List<Player> players;

    // Costruttore
    public Players() {
        this.players = new LinkedList<>();
    }

    public void buildPlayers(List<String> namePlayers) {
        for (String name : namePlayers) {
            players.add(new Player(name));
        }
    }

    public List<Player> getInGame() {
        return players;
    }

    public Player getNextPlayer(int currentIndex) {
        return players.get((currentIndex + 1) % players.size());
    }

    public void remove(Player p) {
        if (p.isBankrupt()) {
            p.losePlayer();
            players.remove(p);
        }
    }
}
