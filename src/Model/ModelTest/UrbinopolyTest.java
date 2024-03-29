package Model.ModelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import Model.ModelGame.Urbinopoly;
import Model.ModelGame.Board.Board;
import Model.ModelGame.Board.Pieces.Property.Land;
import Model.ModelGame.Board.Pieces.Property.Station;
import Model.ModelGame.Player.Player;
import Model.ModelGame.Player.Players;

public class UrbinopolyTest {

    List<String> play = new LinkedList<>();

    public List<String> createPlayers() {
        play.add(("Gio"));
        play.add(("Va"));
        play.add(("ni"));
        return play;
    }

    Players p = new Players();

    Urbinopoly u = new Urbinopoly();

    @Test
    public void testAction1() {
        p.buildPlayers(createPlayers());
        Player current = p.getNextPlayer(-1);
        u.doAction(current);

        // TESTO AZIONE SULLE CARTE UNEX/PROB
        current.moveTo(2);
        assertEquals(2, current.getPosition());
        for (int i = 0; i < 100; i++) {
            int balance = current.getBalance();
            int position = current.getPosition();
            int nCard = current.getCards().size();

            u.doAction(current);

            // mi aspetto che per ogni pescata uno dei seguenti valori
            // venga sempre modificato dalla carte prob / unex

            assertTrue((current.getBalance() != balance) || (current.getPosition() != position)
                    || (current.getCards().size() != nCard));

            if (current.getPosition() != 2) {
                current.moveTo(2);
                current.setInPrison(false);
            }
        }
    }

    @Test
    public void testAction2() {
        p.buildPlayers(createPlayers());
        Player current = p.getNextPlayer(-1);
        u.doAction(current);

        // TESTO LE AZIONI DELLE TASSE
        current = p.getNextPlayer(p.getInGame().indexOf(current));
        current.moveTo(4);
        for (int i = 0; i < 10; i++) {
            int b = current.getBalance();
            u.doAction(current);
            assertNotEquals(b, current.getBalance());
            b = current.getBalance();
        }

        current.moveTo(38);
        current.manageBalance(2000);
        for (int i = 0; i < 10; i++) {
            int b = current.getBalance();
            u.doAction(current);
            assertNotEquals(b, current.getBalance());
            assertEquals(b - 200, current.getBalance());
            b = current.getBalance();
        }
    }

    @Test
    public void testAction3() {
        // TEST SULLE AZIONI DELLE PROPRIETA'

        // la lista di Players in questo caso è da 3
        p.buildPlayers(createPlayers());
        Player current = p.getNextPlayer(5);

        current.moveTo(39);

        // player compra la proprietà e mi assicuro che i soldi
        // vengono scalati dal bilancio
        current.setOptionCommand(1);
        u.playerAction(current);
        assertNotEquals(2000, current.getBalance());

        // passo al prossimo player
        current = p.getNextPlayer(p.getInGame().indexOf(current));
        current.moveTo(39);
        // ha ancora 2000 prima del metodo
        assertEquals(2000, current.getBalance());
        u.doAction(current);
        // verifica che la proprietà appartiene ad un altro player
        // e per regola il bilancio viene diminuito
        assertTrue(current.getBalance() < 2000);

        // verifico che al proprietario non accade nulla
        current = p.getNextPlayer(5);
        current.moveTo(39);

        int balance = current.getBalance();
        u.doAction(current);
        assertEquals(balance, current.getBalance());

        // verifico che se il proprietario è in prigione i soldi non vengono scalati
        current.moveTo(Board.PRISON);
        u.doAction(current);

        current = p.getNextPlayer(p.getInGame().indexOf(current));
        current.moveTo(39);
        balance = current.getBalance();
        u.doAction(current);
        assertEquals(balance, current.getBalance());
    }

    @Test
    public void testPlayerAction() {
        // la lista di Players in questo caso è da 3
        p.buildPlayers(createPlayers());
        Player current = p.getNextPlayer(5);
        current.moveTo(1);

        // TESTO LA COMPERA E LA COSTRUZIONE/RIMOZIONE DI CASE SU LAND

        // player compra la proprietà e mi assicuro che i soldi
        // vengono scalati dal bilancio
        int balance = current.getBalance();
        current.setOptionCommand(1);
        u.playerAction(current);
        assertNotEquals(balance, current.getBalance());

        // provo a costruire casa senza monopolio, quindi non deve scalare soldi
        // perchè questa operazione non è possibile
        balance = current.getBalance();
        current.setOptionCommand(4);
        u.playerAction(current);
        assertEquals(balance, current.getBalance());

        // verifico un monopolio con l'acquisto di un'altra casa di quel colore
        current.moveTo(3);
        current.setOptionCommand(1);
        u.playerAction(current);
        assertNotEquals(balance, current.getBalance());
        Land l = (Land) u.getBoard().getSquare(3);
        assertTrue(l.isUrbinopoly());

        // testo un maggior guadagno se si ha il monopolio
        current = p.getNextPlayer(p.getInGame().indexOf(current));
        current.moveTo(3);
        balance = current.getBalance();
        u.doAction(current);
        assertTrue(current.getBalance() == (balance - 8));

        // provo a costruire con il monopolio, quindi deve diminuire il bilancio
        current = p.getNextPlayer(5);
        balance = current.getBalance();
        /*
         * in questo caso costruisce direttamente sulla sua prima proprietà comprata
         * poichè il valore intero della proprietà selezionata è 0 dato che non viene
         * inizializzato all'interno della classe Player.
         * Tecnicamente tale valore dovrà essere indicato dal controller mediante
         * l'interfaccia utente.
         * Imposto manualmente la proprietà in cui voglio costruire
         */
        current.setPropertySelected(1);
        current.setOptionCommand(4);
        u.playerAction(current);
        assertTrue(current.getBalance() < balance);
        // verifico che il terreno ha una casa costruita
        assertEquals(1, l.getnHouse());

        // testo che il guadagno con una casa è ancora più elevato di prima
        current = p.getNextPlayer(p.getInGame().indexOf(current));
        current.moveTo(3);
        balance = current.getBalance();
        u.doAction(current);
        assertEquals((balance - 20), current.getBalance());

        // testo la rimozione di una casa
        current = p.getNextPlayer(5);
        balance = current.getBalance();
        current.setOptionCommand(5);
        u.playerAction(current);
        // adesso il bilancio è aumentato avendo rimosso una casa
        assertTrue(current.getBalance() > balance);
        // verifico che il terreno non ha una casa costruita
        assertEquals(0, l.getnHouse());
    }

    @Test
    public void testPlayerAction2() {
        // la lista di Players in questo caso è da 3
        p.buildPlayers(createPlayers());
        Player current = p.getNextPlayer(5);
        current.moveTo(26);

        // TESTO L'IPOTECA E LA SUA RIMOZIONE DI UNA PROPRIETA'

        // player compra la proprietà e mi assicuro che i soldi
        // vengono scalati dal bilancio
        int balance = current.getBalance();
        current.setOptionCommand(1);
        u.playerAction(current);

        Land p = (Land) u.getBoard().getSquare(26);
        assertTrue(current.getBalance() == (balance - 260));

        // testo la rimozione dell'ipoteca senza che essa è ipotecata
        // quindi non dovrei ricevere nessun ricavo dall'ipoteca
        balance = current.getBalance();
        current.setOptionCommand(3);
        u.playerAction(current);
        assertEquals(p.isMortaged(), false);
        assertEquals(balance, current.getBalance());

        // ipoteco, quindi il bilancio deve salire
        current.setOptionCommand(2);
        u.playerAction(current);
        assertEquals(balance + 130, current.getBalance());
        assertEquals(p.isMortaged(), true);
        balance = current.getBalance();

        // ipoteco con la proprietà gia ipotecata, quindi non cambierà nulla
        current.setOptionCommand(2);
        u.playerAction(current);
        assertTrue(balance == current.getBalance());

        // rimuovo l'ipoteca, quindi controllo se il bilancio è aumentato
        current.setOptionCommand(3);
        u.playerAction(current);
        assertEquals(p.isMortaged(), false);
        assertTrue(current.getBalance() < balance);

        // TESTO IPOTECA SU STAZIONI / SERVIZI
        current.moveTo(5);
        balance = current.getBalance();
        current.setOptionCommand(1);
        u.playerAction(current);

        Station p1 = (Station) u.getBoard().getSquare(5);
        assertTrue(current.getBalance() == (balance - 200));

        // ipoteco, quindi il bilancio deve salire
        balance = current.getBalance();
        current.setPropertySelected(1);
        current.setOptionCommand(2);
        u.playerAction(current);
        assertEquals(p1.isMortaged(), true);
        assertEquals(balance + 100, current.getBalance());

        // rimozione ipoteca su stazione
        balance = current.getBalance();
        current.setOptionCommand(3);
        u.playerAction(current);
        assertEquals((int) (balance - p1.getPrice() / 2 * 1.1), current.getBalance());

    }
}
