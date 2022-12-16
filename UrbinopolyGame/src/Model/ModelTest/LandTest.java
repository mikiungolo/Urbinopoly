package ModelTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ModelGame.Board.Pieces.Property.Land;

public class LandTest {

    Land l = new Land("MAal", 200, new int[] { 10, 20, 30, 40, 50, 60 }, Land.ColorUrbinopoly.ARANCIONE, 50);

    @Test
    public void testgetRent() {
        l.setUrbinopoly(false);
        assertEquals(null, 10, l.getRent(), 0);
        l.setUrbinopoly(true);
        assertEquals(null, 20, l.getRent(), 0);
        l.setUrbinopoly(false);
        assertEquals(null, 10, l.getRent(), 0);
        l.setUrbinopoly(true);
        l.buildHouse();
        l.buildHouse();
        l.buildHouse();
        assertEquals(null, 40, l.getRent(), 0);
    }

}
