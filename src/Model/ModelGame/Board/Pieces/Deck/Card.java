package Model.ModelGame.Board.Pieces.Deck;

// Modellazione della carta
public class Card {

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

    // getter
    public String getMessage() {
        return message;
    }

    public ActionId getId() {
        return id;
    }

    public int getAction() {
        return action;
    }

    // controllo uguaglianza carte
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Card other = (Card) obj;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        return true;
    }
}
