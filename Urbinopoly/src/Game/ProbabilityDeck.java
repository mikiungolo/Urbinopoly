// Creazione deck Proprietà
public class ProbabilityDeck extends Cards {

    // costruttore classe
    public ProbabilityDeck() {
        super(Card.TypeCard.PROBABILITY.name());
        // Movimenti
        super.add(new Card(Card.TypeCard.PROBABILITY, Card.ActionID.MOVEMENT,
                "Ritornate a Piazza Della Repubblica."));

        super.add(new Card(Card.TypeCard.PROBABILITY, Card.ActionID.MOVEMENT,
                "Andate fino al via!"));
        // Riscossioni
        super.add(new Card(Card.TypeCard.PROBABILITY, Card.ActionID.BALANCE, 250,
                "Avete vinto un premio di consolazione alla lotteria : ritirate 250 euro."));

        super.add(new Card(Card.TypeCard.PROBABILITY, Card.ActionID.BALANCE, 125,
                "Avete ceduto delle azioni: ricavate 125 euro."));

        // Pagamenti
        super.add(new Card(Card.TypeCard.PROBABILITY, Card.ActionID.BALANCE, 125,
                "Scade il vostro premio di assicurazione: pagate 125 euro."));

        super.add(new Card(Card.TypeCard.PROBABILITY, Card.ActionID.BALANCE, 250,
                "Avete perso una causa: pagate 250 euro."));

        // Multa
        super.add(new Card(Card.TypeCard.PROBABILITY, Card.ActionID.BALANCE, 70,
                "Pagate una multa di 70 euro"));

        // Prigione
        super.add(new Card(Card.TypeCard.PROBABILITY, Card.ActionID.PRISON,
                "Uscite gratis di prigione, se ci siete: potete conservare questo cartoncino sino al momento di servirvene (non si sa mai!) oppure venderlo."));

        super.add(new Card(Card.TypeCard.PROBABILITY, Card.ActionID.PRISON,
                "Andate in prigione direttamente e senza passare dal via."));

        shuffle();
    }
}
