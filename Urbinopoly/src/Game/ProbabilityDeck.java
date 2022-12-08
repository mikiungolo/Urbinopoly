// Creazione deck Proprietà
public class ProbabilityDeck extends Cards {

    // costruttore classe
    public ProbabilityDeck() {

        // Movimenti
        super.add(new Card("Ritornate a Piazza Della Repubblica.",
                Card.ActionId.ID_REPUBBLICA));

        super.add(new Card("Andate fino al via!",
                Card.ActionId.ID_GO));
        // Riscossioni
        super.add(new Card("Avete vinto un premio di consolazione alla lotteria : ritirate 250 euro.",
                Card.ActionId.ID_LOTTERY));

        super.add(new Card("Avete ceduto delle azioni: ricavate 125 euro.",
                Card.ActionId.ID_ACTION));

        // Pagamenti
        super.add(new Card("Scade il vostro premio di assicurazione: pagate 125 euro.",
                Card.ActionId.ID_ASSICURATION));

        super.add(new Card("Avete perso una causa: pagate 250 euro.",
                Card.ActionId.ID_CAUSE));

        // Multa
        super.add(new Card("Pagate una multa di 70 euro",
                Card.ActionId.ID_FINE));

        // Prigione
        super.add(new Card(
                "Uscite gratis di prigione, se ci siete: potete conservare questo cartoncino sino al momento di servirvene (non si sa mai!) oppure venderlo.",
                Card.ActionId.ID_FREE_PRISON));

        super.add(new Card("Andate in prigione direttamente e senza passare dal via.",
                Card.ActionId.ID_GO_PRISON));

        shuffle();
    }

    // get Card

    // azioni delle carte probabilità
    public void exeProb(Player p) {
        switch (super.takeCard(this).getId()) {
            case ID_REPUBBLICA -> {

            }
            case ID_GO -> {

            }
            case ID_LOTTERY -> {

            }
            case ID_ACTION -> {

            }
            case ID_ASSICURATION -> {

            }
            case ID_CAUSE -> {

            }
            case ID_FINE -> {

            }
            case ID_FREE_PRISON -> {

            }
            case ID_GO_PRISON -> {

            }
        }
    }
}
