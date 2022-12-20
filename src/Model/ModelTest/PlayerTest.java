package Model.ModelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Model.ModelGame.Board.Board;
import Model.ModelGame.Board.Pieces.Property.Land;
import Model.ModelGame.Dice.Dice;
import Model.ModelGame.Dice.DiceApi;
import Model.ModelGame.Player.Player;

public class PlayerTest {

    Board b = Board.getSingletonBoard();
    Player p = new Player("Giovanni");

    @Test
    public void testAddProperty() {
        Land l1 = (Land) b.getSquare(b.getPositionSquare("Corso Reactive"));
        Land l2 = (Land) b.getSquare(b.getPositionSquare("Corso Gimnasium"));
        Land l3 = (Land) b.getSquare(b.getPositionSquare("Corso MadPlanet"));

        p.addProperty(l1);
        assertTrue(null, p.getProperties().contains(l1));
        assertEquals(null, p.getBalance(), 2000 - l1.getPrice(), 0);
        assertTrue(null, !l1.isUrbinopoly());

        p.addProperty(l2);
        assertTrue(null, p.getProperties().contains(l2));
        assertEquals(null, p.getBalance(), 2000 - l1.getPrice() - l2.getPrice(), 0);
        assertTrue(null, !l1.isUrbinopoly());

        p.addProperty(l3);
        assertTrue(null, p.getProperties().contains(l3));
        assertEquals(null, p.getBalance(), 2000 - l1.getPrice() - l2.getPrice() - l3.getPrice(), 0);
        assertTrue(null, l1.isUrbinopoly());
    }

    @Test
    public void testMove() {
        DiceApi d = new Dice();

        // posizione iniziale del Player
        assertEquals(0, p.getPosition());

        // posizione con lancio dei dadi
        d.roll();
        p.move(d.getTotalValue());
        assertEquals(0 + d.getTotalValue(), p.getPosition());

        // movimento circolare
        p.moveTo(39);
        d.roll();
        p.move(d.getTotalValue());
        assertEquals(39 + d.getTotalValue() - 40, p.getPosition());

        // move to
        p.moveTo(b.getPositionSquare("Via del Salame di Montefeltro"));
        assertEquals(11, p.getPosition());

        // circolare
        p.moveTo(Board.GO);
        for (int i = 0; i < 1000; i++) {
            d.roll();
            p.move(d.getTotalValue());
            assertNotEquals(40, p.getPosition());
        }

    }
}
