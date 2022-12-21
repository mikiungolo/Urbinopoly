package Model.ModelGame.Board.Pieces.Deck;

import Model.ModelGame.Board.Board;

public class UnexpectedDeck extends Cards {

        // costruttore classe
        public UnexpectedDeck(Board b) {

                // Movimenti
                super.add(new Card("Avete vinto un contest in Via della Casciotta. Correte a vedere cosa vi spetta!",
                                Card.ActionId.ID_MOVE_TO, b.getPositionSquare("Via della Casciotta")));

                super.add(new Card("Fate 3 passi indietro (con tanti auguri).",
                                Card.ActionId.ID_MOVE, -3));

                // Riscossioni
                super.add(new Card("Maturano le cedole delle vostre cartelle di rendita, ritirate 375 euro.",
                                Card.ActionId.ID_BALANCE, 375));

                super.add(new Card("Avete vinto un terno al lotto: ritirate 250 euro.",
                                Card.ActionId.ID_BALANCE, 250));

                // Pagamenti
                super.add(new Card("Matrimonio in famiglia: spese impreviste 375 euro.",
                                Card.ActionId.ID_BALANCE, -375));

                super.add(new Card(
                                "Dovete pagare un contributo di miglioria stradale, 100 per ogni casa.",
                                Card.ActionId.ID_BALANCE, 100));

                // Multa
                super.add(new Card("Multa di 40 euro per aver guidato senza patente.",
                                Card.ActionId.ID_BALANCE, -40));

                // Prigione
                super.add(new Card(
                                "Uscite gratis di prigione, se ci siete: Potete conservare questo cartoncino sino al momento di servirvene (non si sa mai!).",
                                Card.ActionId.ID_FREE_PRISON, 0));

                super.add(new Card("Andate in prigione direttamente e senza passare dal via.",
                                Card.ActionId.ID_MOVE_TO, b.getPositionSquare("PRISON")));

                shuffle();
        }

        public Card takeCard() {
                return super.takeCard(this);
        }
}