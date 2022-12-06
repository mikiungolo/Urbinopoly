import static org.junit.Assume.assumeTrue;

import org.junit.Test;

public class DiceTest {
    Dice d = new Dice();

    @Test
    public void testIsDouble() {
        assumeTrue(d.roll() <= 12 && d.roll() >= 2);
    }

    @Test
    public void testRoll() {

    }
}
