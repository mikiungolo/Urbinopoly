package Model.ModelTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Model.ModelGame.Board.Pieces.Taxes.Taxes;

public class TaxesTest {
    Taxes<Integer> Luxury = new Taxes<Integer>("Luxury Tax", 200, null);
    Taxes<Double> Income = new Taxes<Double>("Income Tax", 1.1, null);

    @Test
    public void testGetRate() {

        assertEquals(200, Luxury.getRate(), 0);
        assertEquals(1.1, Income.getRate(), 0);
    }
}
