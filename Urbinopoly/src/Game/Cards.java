import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Gestione di un mazzo di carte 
public class Cards {
    // Modellazione della carta
    public static class Card {

        // Definizione tipi di carte tramite enum
        public enum TypeCard {
            UNEXPECTED, PROBABILITY
        };

        // quadrato Probability che indica il mazzo da cui pescare
        public static class Probability extends Square {

            // costruttore
            public Probability() {
                super(Cards.Card.TypeCard.PROBABILITY.name());
            }

        }

        // quadrato Unexpected che indica il mazzo da cui pescare
        public static class Unexpected extends Square {

            // costruttore
            public Unexpected() {
                super(Cards.Card.TypeCard.UNEXPECTED.name());
            }

        }

        // dichiarazione attributi della classe Card
        private final TypeCard type;
        private final String message;

        // Costruttore Card
        public Card(Cards.Card.TypeCard type, String message) {
            this.type = type;
            this.message = message;
        }

        // Metodi Getter
        public TypeCard getType() {
            return type;
        }

        public String getMessage() {
            return message;
        }
    }

    // L'outer class (Cards) è una lista di Card per composizione
    private List<Card> deck;
    // numero carte pescate da un mazzo di carte
    private int nCardTaken;

    // Costruttore Cards
    public Cards(String name) {
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
    public void add(Card card) {
        deck.add(card);
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    // Interfaccia funzionale per estrazione di un carta
    @FunctionalInterface
    public interface CardApi {
        Cards.Card randomEvent(Cards m);
    }

    // espressione lambda per estrazione di una carta dal mazzo
    CardApi evento = m -> {
        if (m.getnCardTaken() > 10) {
            m.setnCardTaken(0);
            m.shuffle();
        }
        m.setnCardTaken(m.getnCardTaken() + 1);
        return m.getDeck().get(0);
    };

    public CardApi getEvento() {
        return evento;
    }
}
