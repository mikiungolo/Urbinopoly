import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Gestione di un mazzo di carte 
public class Cards implements CardApi{
    // Modellazione della carta
    static class Card extends Square{

        // Definizione tipi di carte 
        public enum TypeCard{UNEXPECTED, PROBABILITY};

        private final TypeCard type;
        private final String message;

        // Costruttore Card
        public Card(String name, boolean isBusy, Cards.Card.TypeCard type, String message) {
            super(name, isBusy);
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

    // L'outer class (Cards) è una lista di Card
    private List<Card> deck;

    private int nCardTaken;

    // Costruttore Cards
    public Cards(String name, boolean isBusy) {
        this.deck = new ArrayList<>();
        this.nCardTaken = 0;
    }
    
    // aggiungi una carta nel deck (metodo da usare nel costruttore di Prob e impr) 
    public void add(Card card){
        deck.add(card);
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    @Override
    public Cards.Card randomEvent(Cards m) {
        if(this.nCardTaken > 9){
            shuffle();
            this.nCardTaken = 0;
        }
        this.nCardTaken++;
        return this.deck.get(0);
    }

}
