// Creazione deck Proprietà
public class ProbabilityDeck extends Cards {

        // costruttore classe
        public ProbabilityDeck() {
                super(Card.TypeCard.PROBABILITY.name());
                // Movimenti
                super.add(new Card(Card.TypeCard.PROBABILITY,
                                "Ritornate a Piazza Della Repubblica."));

                super.add(new Card(Card.TypeCard.PROBABILITY,
                                "Andate fino al via!"));
                // Riscossioni
                super.add(new Card(Card.TypeCard.PROBABILITY,
                                "Avete vinto un premio di consolazione alla lotteria : ritirate 250 euro."));

                super.add(new Card(Card.TypeCard.PROBABILITY,
                                "Avete ceduto delle azioni: ricavate 125 euro."));

                // Pagamenti
                super.add(new Card(Card.TypeCard.PROBABILITY,
                                "Scade il vostro premio di assicurazione: pagate 125 euro."));

                super.add(new Card(Card.TypeCard.PROBABILITY,
                                "Avete perso una causa: pagate 250 euro."));

                // Multa
                super.add(new Card(Card.TypeCard.PROBABILITY,
                                "Pagate una multa di 70 euro"));

                // Prigione
                super.add(new Card(Card.TypeCard.PROBABILITY,
                                "Uscite gratis di prigione, se ci siete: potete conservare questo cartoncino sino al momento di servirvene (non si sa mai!) oppure venderlo."));

                super.add(new Card(Card.TypeCard.PROBABILITY,
                                "Andate in prigione direttamente e senza passare dal via."));

                shuffle();
        }

        // estrazione carta
        public CardApi extract(Cards m) {
                return super.getEvento();
        }
}