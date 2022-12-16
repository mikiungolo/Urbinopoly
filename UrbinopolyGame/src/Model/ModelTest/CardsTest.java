package ModelTest;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import ModelGame.Board.Board;
import ModelGame.Board.Pieces.Deck.Card;
import ModelGame.Board.Pieces.Deck.ProbabilityDeck;

public class CardsTest {

    ProbabilityDeck p = new ProbabilityDeck(new Board());

    @Test
    public void testGetEvento() {
        Card corr = p.takeCard();
        Card prec = null;
        assertNotEquals(corr, prec);
        prec = corr;

        for (int i = 0; i < 20; i++) {
            corr = p.takeCard();
            assertNotEquals(corr, prec);
            prec = corr;
        }
    }
}
