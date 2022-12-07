import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TaxesTest {
    Taxes<Integer> Luxury = new Taxes<Integer>("Luxury Tax", 200);
    Taxes<Double> Income = new Taxes<Double>("Income Tax", 1.1);

    @Test
    public void testGetRate() {

        assertEquals(200, Luxury.getRate(), 0);
        assertEquals(1.1, Income.getRate(), 0);
    }
}
