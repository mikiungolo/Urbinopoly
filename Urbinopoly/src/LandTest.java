import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LandTest {

    Land l = new Land("MAal", 200, new int[] { 10, 20, 30, 40, 50, 60 }, Land.ColorUrbinopoly.ARANCIONE, 50);

    @Test
    public void testBuildHouse() {
        l.setUrbinopoly(false);
        assertEquals(null, 0, l.buildHouse(), 0);
        l.setUrbinopoly(true);
        assertEquals(null, l.getPriceHouse(), l.buildHouse(), 0);
    }

    @Test
    public void testRemoveHouse() {
        l.setnHouse(0);
        assertEquals(null, 0, l.removeHouse(), 0);
        l.setnHouse(3);
        assertEquals(null, l.getPriceHouse() / 2, l.removeHouse(), 0);
    }
}
