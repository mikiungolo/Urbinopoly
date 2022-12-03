public class Unexpected extends Cards {

    // costruttore classe
    public Unexpected() {
        super(Card.TypeCard.UNEXPECTED.name());

        // Movimenti
        super.add(new Card(Card.TypeCard.UNEXPECTED.name(), Card.TypeCard.UNEXPECTED,
                "Andate in prigione direttamente e senza passare dal via."));
        super.add(new Card(Card.TypeCard.UNEXPECTED.name(), Card.TypeCard.UNEXPECTED,
                "Fate 3 passi indietro (con tanti auguri)."));
        // Riscossioni
        super.add(new Card(Card.TypeCard.UNEXPECTED.name(), Card.TypeCard.UNEXPECTED,
                "Maturano le cedole delle vostre cartelle di rendita, ritirate 375 euro."));
        super.add(new Card(Card.TypeCard.UNEXPECTED.name(), Card.TypeCard.UNEXPECTED,
                "Avete vinto un terno al lotto: ritirate 250 euro."));
        // Pagamenti
        super.add(new Card(Card.TypeCard.UNEXPECTED.name(), Card.TypeCard.UNEXPECTED,
                "Matrimonio in famiglia: spese impreviste 375 euro."));
        super.add(new Card(Card.TypeCard.UNEXPECTED.name(), Card.TypeCard.UNEXPECTED,
                "Dovete pagare un contributo di miglioria stradale, 100 per ogni casa, 250 euro per ogni albergo che possedete."));
        // Multa
        super.add(new Card(Card.TypeCard.UNEXPECTED.name(), Card.TypeCard.UNEXPECTED,
                "Multa di 40 euro per aver guidato senza patente."));
        // Prigione
        super.add(new Card(Card.TypeCard.UNEXPECTED.name(), Card.TypeCard.UNEXPECTED,
                "Uscite gratis di prigione, se ci siete: Potete conservare questo cartoncino sino al momento di servirvene (non si sa mai!)."));
        super.add(new Card(Card.TypeCard.UNEXPECTED.name(), Card.TypeCard.UNEXPECTED,
                "Andate in prigione direttamente e senza passare dal via."));

        shuffle();
    }

    // estrazione carta
    public CardApi extract(Cards m) {
        return super.getEvento();
    }
}