import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PropertyTest {
    Property p = new Property("Malato", 100, new int[] { 10, 20, 30, 40, 50, 60 }, Property.TypeProperty.LAND);

    @Test
    public void testMortage() {
        p.setMortaged(false);
        assertEquals(null, 100 / 2, p.mortage(), 0);
        assertTrue(p.isMortaged());
    }

    @Test
    public void testReleaseProperty() {

    }

    @Test
    public void testRemoveMortage() {
        p.releaseProperty();

    }
}