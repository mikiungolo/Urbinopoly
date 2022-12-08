// Creazione deck Proprietà
public class ProbabilityDeck extends Cards {

    // costruttore classe
    public ProbabilityDeck(Board b) {

        // Movimenti
        super.add(new Card("Ritornate a Piazza Della Repubblica.",
                Card.ActionId.ID_MOVE_TO, b.getPositionSquare("Piazza della Repubblica")));

        super.add(new Card("Andate fino al via!",
                Card.ActionId.ID_MOVE_TO, b.getPositionSquare("GO")));
        // Riscossioni
        super.add(new Card("Avete vinto un premio di consolazione alla lotteria : ritirate 250 euro.",
                Card.ActionId.ID_BALANCE, 250));

        super.add(new Card("Avete ceduto delle azioni: ricavate 125 euro.",
                Card.ActionId.ID_BALANCE, 125));

        // Pagamenti
        super.add(new Card("Scade il vostro premio di assicurazione: pagate 125 euro.",
                Card.ActionId.ID_BALANCE, -125));

        super.add(new Card("Avete perso una causa: pagate 250 euro.",
                Card.ActionId.ID_BALANCE, -250));

        // Multa
        super.add(new Card("Pagate una multa di 70 euro",
                Card.ActionId.ID_BALANCE, -70));

        // Prigione
        super.add(new Card(
                "Uscite gratis di prigione, se ci siete: potete conservare questo cartoncino sino al momento di servirvene (non si sa mai!) oppure venderlo.",
                Card.ActionId.ID_FREE_PRISON, 0));

        super.add(new Card("Andate in prigione direttamente e senza passare dal via.",
                Card.ActionId.ID_MOVE_TO, b.getPositionSquare("PRISON")));

        shuffle();
    }

    // azioni delle carte probabilità
    // public void exeProb(Player p) {
    // switch (super.takeCard(this).getId()) {
    // case ID_MOVE_TO -> {

    // }
    // }
    // }
}
