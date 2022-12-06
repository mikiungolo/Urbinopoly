// Interfaccia pubblica relative ai mazzi di carte
public interface CardsApi {
    // seleziona un evento estraendo una carta dal mazzo
    public Cards.Card randomEvent(Cards m);

    // cambia l'ordine delle carte nel mazzo
    public void shuffle();

    // aggiunge una carta al mazzo
    public void add(Cards.Card c);
}
