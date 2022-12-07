public class UnexpectedDeck extends Cards {

    // costruttore classe
    public UnexpectedDeck() {
        super(Card.TypeCard.UNEXPECTED.name());

        // Movimenti
        super.add(new Card(Card.TypeCard.UNEXPECTED, Card.ActionID.MOVEMENT,
                "Andate in prigione direttamente e senza passare dal via."));

        super.add(new Card(Card.TypeCard.UNEXPECTED, Card.ActionID.MOVEMENT, 3,
                "Fate 3 passi indietro (con tanti auguri)."));

        // Riscossioni
        super.add(new Card(Card.TypeCard.UNEXPECTED, Card.ActionID.BALANCE, 375,
                "Maturano le cedole delle vostre cartelle di rendita, ritirate 375 euro."));

        super.add(new Card(Card.TypeCard.UNEXPECTED, Card.ActionID.BALANCE, 250,
                "Avete vinto un terno al lotto: ritirate 250 euro."));

        // Pagamenti
        super.add(new Card(Card.TypeCard.UNEXPECTED, Card.ActionID.BALANCE, 375,
                "Matrimonio in famiglia: spese impreviste 375 euro."));

        super.add(new Card(Card.TypeCard.UNEXPECTED, Card.ActionID.BALANCE,
                "Dovete pagare un contributo di miglioria stradale, 100 per ogni casa, 250 euro per ogni albergo che possedete."));

        // Multa
        super.add(new Card(Card.TypeCard.UNEXPECTED, Card.ActionID.BALANCE, 40,
                "Multa di 40 euro per aver guidato senza patente."));
        // Prigione
        super.add(new Card(Card.TypeCard.UNEXPECTED, Card.ActionID.PRISON,
                "Uscite gratis di prigione, se ci siete: Potete conservare questo cartoncino sino al momento di servirvene (non si sa mai!)."));

        super.add(new Card(Card.TypeCard.UNEXPECTED, Card.ActionID.PRISON,
                "Andate in prigione direttamente e senza passare dal via."));

        shuffle();
    }
}