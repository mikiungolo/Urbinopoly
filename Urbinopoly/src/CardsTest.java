
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class CardsTest {

    ProbabilityDeck p = new ProbabilityDeck();

    @Test
    public void testGetEvento() {
        Cards.Card corr = new Cards.Card(null, null);
        Cards.Card prec = new Cards.Card(null, null);
        for (int i = 0; i < 20; i++) {
            corr = p.takeCard(p);
            assertNotEquals(corr, prec);
            prec = corr;
        }
    }

}
