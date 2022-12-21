package Model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Model.ModelGame.Dice.Dice;

public class testDice {
    Dice d = new Dice();

    @Test
    public void testRoll() {
        for (int i = 0; i < 100; i++) {
            d.roll();
            assertTrue(d.getDice()[0] > 0 && d.getDice()[0] <= 6);
            assertTrue(d.getDice()[1] > 0 && d.getDice()[1] <= 6);
        }

    }
}
