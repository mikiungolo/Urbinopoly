package ModelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ModelGame.Board.Pieces.Property.Property;
import ModelGame.Board.Pieces.Square.Type;

public class PropertyTest {
    Property p = new Property("Malato", 100, new int[] { 10, 20, 30, 40, 50, 60 }, Type.LAND);

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
        assertEquals(100 / 2 * 1.1, p.removeMortage(), 0);
        assertEquals(false, p.isMortaged());

    }
}
