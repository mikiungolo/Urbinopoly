package Model.ModelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Model.ModelGame.Board.Pieces.Property.Property;
import Model.ModelGame.Board.Pieces.Square.Type;

public class PropertyTest {
    Property p = new Property("Mala", 100, new int[] { 10, 20, 30, 40, 50, 60 }, Type.LAND);

    @Test
    public void testMortage() {
        p.setMortaged(false);
        assertEquals(null, 100 / 2, p.mortage(), 0);
        assertTrue(p.isMortaged());
    }

    @Test
    public void testRemoveMortage() {
        p.setMortaged(true);
        assertEquals(true, p.isMortaged());
        assertEquals(Math.round(-(100 / 2 * 1.1)), p.removeMortage(), 0);
        assertEquals(false, p.isMortaged());

    }
}
