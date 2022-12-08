import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Gestione di un mazzo di carte 
public class Cards implements CardsApi {
    // Modellazione della carta
    public static class Card {

        // quadrato Probability che indica il mazzo da cui pescare
        public static class Probability extends Square {

            // costruttore
            public Probability() {
                super(Type.PROBABILITY.name(), Type.PROBABILITY);
            }

        }

        // quadrato Unexpected che indica il mazzo da cui pescare
        public static class Unexpected extends Square {

            // costruttore
            public Unexpected() {
                super(Type.UNEXPECTED.name(), Type.UNEXPECTED);
            }
        }

        // definizione del codice id di una carta
        public enum ActionId {
            ID_MOVE_TO, ID_MOVE, ID_BALANCE, ID_FREE_PRISON
        }

        // dichiarazione attributi della classe Card
        private final String message;
        private final ActionId id;
        private final int action;

        // Costruttore Card
        public Card(String message, ActionId id, int act) {
            this.message = message;
            this.id = id;
            this.action = act;
        }

        public String getMessage() {
            return message;
        }

        public ActionId getId() {
            return id;
        }

        public int getAction() {
            return action;
        }
    }

    // L'outer class (Cards) è una lista di Card per composizione
    private List<Card> deck;
    // numero carte pescate da un mazzo di carte
    private int nCardTaken;

    // Costruttore Cards
    public Cards() {
        this.nCardTaken = 0;
        this.deck = new ArrayList<>();
    }

    // getter and setter
    public int getnCardTaken() {
        return nCardTaken;
    }

    public void setnCardTaken(int nCardTaken) {
        this.nCardTaken = nCardTaken;
    }

    public List<Card> getDeck() {
        return deck;
    }

    // aggiungi una carta nel deck (metodo da usare nel costruttore di Prob e impr)
    @Override
    public void add(Card card) {
        deck.add(card);
    }

    @Override
    public void shuffle() {
        Collections.shuffle(deck);
    }

    @Override
    public Cards.Card takeCard(Cards m) {
        if (m.getnCardTaken() > 8) {
            m.setnCardTaken(0);
            m.shuffle();
        }
        m.setnCardTaken(m.getnCardTaken() + 1);
        return m.getDeck().get(m.getnCardTaken() - 1);
    }
}
