import java.util.LinkedList;
import java.util.List;

// Gestione di un mazzo di carte 
public class Cards extends Square{
    // definizione dei 2 tipi di carte
    public enum Card{
        PROBABILITIES, 
        UNEXPECTED;

        // private final String message;
        // private final String name;
        
        // private Card(String message, String name) {
        //     this.message = message;
        //     this.name = name;
        // }
    }
    
    private List<Card> deck = new LinkedList<>();

    public Cards(String name, boolean isBusy, List<Cards.Card> deck) {
        super(name, isBusy);
        this.deck = deck;
    }
    
    // aggiungi una carta nel deck 
    
}
