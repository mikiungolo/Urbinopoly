public class UnexpectedDeck extends Cards {

    // costruttore classe
    public UnexpectedDeck() {
        super(Card.TypeCard.UNEXPECTED.name());

        // Movimenti
        super.add(new Card(Card.TypeCard.UNEXPECTED,
                "Andate in prigione direttamente e senza passare dal via."));

        super.add(new Card(Card.TypeCard.UNEXPECTED,
                "Fate 3 passi indietro (con tanti auguri)."));

        // Riscossioni
        super.add(new Card(Card.TypeCard.UNEXPECTED,
                "Maturano le cedole delle vostre cartelle di rendita, ritirate 375 euro."));

        super.add(new Card(Card.TypeCard.UNEXPECTED,
                "Avete vinto un terno al lotto: ritirate 250 euro."));

        // Pagamenti
        super.add(new Card(Card.TypeCard.UNEXPECTED,
                "Matrimonio in famiglia: spese impreviste 375 euro."));

        super.add(new Card(Card.TypeCard.UNEXPECTED,
                "Dovete pagare un contributo di miglioria stradale, 100 per ogni casa, 250 euro per ogni albergo che possedete."));

        // Multa
        super.add(new Card(Card.TypeCard.UNEXPECTED,
                "Multa di 40 euro per aver guidato senza patente."));
        // Prigione
        super.add(new Card(Card.TypeCard.UNEXPECTED,
                "Uscite gratis di prigione, se ci siete: Potete conservare questo cartoncino sino al momento di servirvene (non si sa mai!)."));

        super.add(new Card(Card.TypeCard.UNEXPECTED,
                "Andate in prigione direttamente e senza passare dal via."));

        shuffle();
    }
}