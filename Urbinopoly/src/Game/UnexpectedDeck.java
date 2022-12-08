public class UnexpectedDeck extends Cards {

    // costruttore classe
    public UnexpectedDeck() {

        // Movimenti
        super.add(new Card("Avete vinto un contest in Via della Casciotta. Correte a vedere cosa vi spetta!",
                Card.ActionId.ID_CASCIOTTA));

        super.add(new Card("Fate 3 passi indietro (con tanti auguri).",
                Card.ActionId.ID_UNDER_3));

        // Riscossioni
        super.add(new Card("Maturano le cedole delle vostre cartelle di rendita, ritirate 375 euro.",
                Card.ActionId.ID_INCOME));

        super.add(new Card("Avete vinto un terno al lotto: ritirate 250 euro.",
                Card.ActionId.ID_LOTTO));

        // Pagamenti
        super.add(new Card("Matrimonio in famiglia: spese impreviste 375 euro.",
                Card.ActionId.ID_MARRIAGE));

        super.add(new Card(
                "Dovete pagare un contributo di miglioria stradale, 100 per ogni casa, 250 euro per ogni albergo che possedete.",
                Card.ActionId.ID_WAY));

        // Multa
        super.add(new Card("Multa di 40 euro per aver guidato senza patente.",
                Card.ActionId.ID_DRIVE));

        // Prigione
        super.add(new Card(
                "Uscite gratis di prigione, se ci siete: Potete conservare questo cartoncino sino al momento di servirvene (non si sa mai!).",
                Card.ActionId.ID_FREE_PRISON));

        super.add(new Card("Andate in prigione direttamente e senza passare dal via.",
                Card.ActionId.ID_GO_PRISON));

        shuffle();
    }

    // azioni delle carte probabilità
    public void exeUnex(Player p) {
        switch (super.takeCard(this).getId()) {
            case ID_CASCIOTTA -> {

            }
            case ID_UNDER_3 -> {

            }
            case ID_INCOME -> {

            }
            case ID_LOTTO -> {

            }
            case ID_MARRIAGE -> {

            }
            case ID_WAY -> {

            }
            case ID_DRIVE -> {

            }
            case ID_FREE_PRISON -> {

            }
            case ID_GO_PRISON -> {

            }
        }
    }
}