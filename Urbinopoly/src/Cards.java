import java.util.ArrayList;
import java.util.List;

// Gestione di un mazzo di carte 
public class Cards extends Square{
    // Modellazione della carta
    static class Card{
        // Definizione tipi di carte 
        public enum TypeCard{UNEXPECTED, PROBABILITY};

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
    
    // L'outer class (Cards) è una lista di Card
    private List<Card> deck;

    public Cards(String name, boolean isBusy) {
        super(name, isBusy);
        this.deck = new ArrayList<>();
    }
    
    // aggiungi una carta nel deck (metodo da usare nel costruttore di Prob e impr) 
    public void add(Card card){
        deck.add(card);
    }
}
