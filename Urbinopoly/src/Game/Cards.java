import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

// Gestione di un mazzo di carte 
public class Cards implements CardsApi {
    // Modellazione della carta
    public static class Card {

        // Definizione tipi di carte tramite enum
        public enum TypeCard {
            UNEXPECTED, PROBABILITY
        };

        // Ogni carta ha un codice a cui corrisponde un'azione
        public enum ActionID {
            MOVEMENT,
            BALANCE,
            PRISON;
        }

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
        private final ActionID nature;
        private final Optional<Integer> action;

        // Costruttore Card
        public Card(Cards.Card.TypeCard type, Cards.Card.ActionID a, int action, String message) {
            this.type = type;
            this.message = message;
            this.nature = a;
            this.action = Optional.ofNullable(action);
        }

        public Card(Cards.Card.TypeCard type, Cards.Card.ActionID a, String message) {
            this.type = type;
            this.message = message;
            this.nature = a;
            this.action = Optional.empty();
        }

        // Metodi Getter
        public TypeCard getType() {
            return type;
        }

        public String getMessage() {
            return message;
        }

        public ActionID getNature() {
            return nature;
        }

        public Optional<Integer> getAction() {
            return action;
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
