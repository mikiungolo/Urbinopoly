package ModelTest;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import ModelGame.Board.Board;
import ModelGame.Board.Pieces.Deck.Cards;
import ModelGame.Board.Pieces.Deck.ProbabilityDeck;

public class CardsTest {

    ProbabilityDeck p = new ProbabilityDeck(new Board());

    @Test
    public void testGetEvento() {
        Cards.Card corr = new Cards.Card(null, null, 0);
        Cards.Card prec = new Cards.Card(null, null, 0);
        for (int i = 0; i < 20; i++) {
            corr = p.takeCard(p);
            assertNotEquals(corr, prec);
            prec = corr;
        }
    }

}
