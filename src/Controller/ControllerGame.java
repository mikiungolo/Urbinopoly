package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Model.ModelGame.Urbinopoly;
import Model.ModelGame.Board.Pieces.Property.Property;
import Model.ModelGame.Player.Player;
import View.GUI.BoardGui;
import View.GUI.LandCard;
import View.GUI.PrisonGui;
import View.GUI.PropertyAction;
import View.GUI.SocietyCard;
import View.GUI.StationCard;

public class ControllerGame {
    // riferimenti alla View del board e al modello
    private BoardGui board;
    private PrisonGui prison;
    private Urbinopoly model;

    // controllo per il player
    private int indexCurrentPlayer;
    private Player current;

    // cosrtuttore del ControllerGrame
    public ControllerGame(Urbinopoly model, BoardGui board) {
        this.model = model;
        this.prison = new PrisonGui();
        this.indexCurrentPlayer = -1;
        this.board = board;
        this.current = new Player();
    }

    // getter

    public BoardGui getBoard() {
        return this.board;
    }

    public Urbinopoly getModel() {
        return model;
    }

    public void game() {
        // dopo l'inizializzazione dall'apposito controller
        // si producono gli ascoltatori per il gioco

        addListener();
        board.setVisible(true);

        // avvio gameplay
        new Thread() {

            @Override
            public void run() {
                gameplay();
            }
        }.start();

    }

    // struttura dell'intero gameplay
    public void gameplay() {

        // ottenimento del prossimo player in gioco
        current = model.getPlayers().getNextPlayer(indexCurrentPlayer);

        model.setInTurn(true);
        validateBoard(board, current);
        getBoard().getHighlight().append("Turno del Player: " + current.getName() + "\n");
        indexCurrentPlayer++;

        while (!model.isEndGame()) {

            if (model.isEndGame()) {
                getBoard().getHighlight().append("\n Urbinopoly temrinato.\nSviluppatori: Ungolo, Sette");
            }
        }
    }

    // calcola i Jbutton che possono essere utilizzati al momento i-esimo
    private void validateBoard(BoardGui board, Player p) {
        for (int i = 0; i < 7; i++) {
            if (model.validateCommand(i + 1, p)) {
                board.getOption()[i].setEnabled(true);
            } else {
                board.getOption()[i].setEnabled(false);
            }
        }
    }

    private void updateViewBalance(Player p) {
        getBoard().setBalance(p.getBalance(), indexCurrentPlayer);
    }

    private void updateViewPosition(Player p) {
        getBoard().getDie1().setText(Integer.toString(model.getDice().getDice()[0]));
        getBoard().getDie2().setText(Integer.toString(model.getDice().getDice()[1]));
        getBoard().setPos(p.getPosition(), indexCurrentPlayer);
    }

    // Creazione listener pulsanti del board game
    private void addListener() {
        addBuyListener();
        addMortgageListener();
        addRemMortgageListener();
        addBuildListener();
        addRemBuildListener();
        addRollListener();
        addEndTurnListener();
        addCardPrison();
        addLuckPrison();
        addCautionPrison();
    }

    // definizione di tutti gli ascoltatori
    private void addCautionPrison() {

        // Creazione listener per pulsante EndTurn
        prison.getCautionPrisonButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Player currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
                currentP.setOptionCommand(1);
            }
        });
    }

    private void addLuckPrison() {
        // Creazione listener per pulsante EndTurn
        prison.getRollPrisonButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Player currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
                currentP.setOptionCommand(3);
            }
        });
    }

    private void addCardPrison() {

        // Creazione listener per pulsante EndTurn
        prison.getCardPrisonButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Player currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
                currentP.setOptionCommand(2);
            }
        });
    }

    private void addEndTurnListener() {

        // Creazione listener per pulsante EndTurn
        getBoard().getEndTurn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                getBoard().getHighlight().append("Turno terminato per il Player: " + current.getName() + "\n");
                current.setOptionCommand(7);
                current.setOptionRolled(false);
                current = model.getPlayers().getNextPlayer(indexCurrentPlayer);
                indexCurrentPlayer++;
                getBoard().getHighlight().append("\nTurno del Player: " + current.getName());

                validateBoard(board, current);
            }
        });
    }

    private void addRollListener() {

        // Creazione listener per pulsante Roll
        getBoard().getRollButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                current.setOptionCommand(6);

                getModel().getDice().roll();
                current.move(getModel().getDice().getTotalValue());
                updateViewPosition(current);

                getModel().doAction(current);
                // controllo sconfitta del Player con eventuale rimozione
                getModel().getPlayers().remove(current);

                getModel().playerAction(current);
                /*
                 * fin tanto che il player lanciando i dadi riceve facciate
                 * uguali deve giocare un ulteriore turno, altrimenti toccato
                 * il limite dei massimi turni consecutivi finirà in prigione.
                 * Tale operazione avviene in chiamata ricorsiva.
                 */
                if (getModel().getDice().isDouble() && !current.isInPrison()) {
                    if (current.goPrisonForTripleTurn()) {
                        getBoard().getHighlight().append(current.getName() + " finisce in prigione per aver " +
                                "truccato i dadi per ben tre volte!\n");
                    }
                }
                updateViewBalance(current);
                current.setOptionRolled(true);
                validateBoard(board, current);
            }
        });
    }

    private void actionProperty() {
        PropertyAction prop = showListProperty(current);
        buttonDoAct(prop);
        model.playerAction(current);
        updateViewBalance(current);
    }

    private void addRemBuildListener() {

        // Creazione listener per pulsante remove House
        getBoard().getRemHouseButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                current.setOptionCommand(5);
                actionProperty();
            }
        });
    }

    private void addBuildListener() {

        // Creazione listener per pulsante Build House
        getBoard().getBuildButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                current.setOptionCommand(4);
                actionProperty();
            }
        });
    }

    private void addRemMortgageListener() {

        // Creazione listener per pulsante di rimozione ipoteca
        getBoard().getRemoveMortButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                current.setOptionCommand(3);
                actionProperty();
            }
        });
    }

    private void addMortgageListener() {

        // Creazione listener per pulsante ipoteca
        getBoard().getMortageButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                current.setOptionCommand(2);
                actionProperty();
            }
        });
    }

    private void addBuyListener() {

        // Creazione listener per pulsante per acquisto proprietà
        getBoard().getBuyButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                current.setOptionCommand(1);
                switch (model.getBoard().getSquare(current.getPosition()).getNature()) {
                    case LAND -> showLand();
                    case SERVICE -> showService();
                    case STATION -> showStation();
                    default -> {
                    }
                }
                model.playerAction(current);
                updateViewBalance(current);
            }
        });
    }

    private PropertyAction showListProperty(Player p) {
        PropertyAction prop = new PropertyAction();
        prop.setLocation(300, 250);
        prop.setVisible(true);

        if (!p.getProperties().isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) prop.getPlayerPropertyTable().getModel();
            for (int i = 0; i < p.getProperties().size(); i++) {
                model.addRow(new Object[] { p.getProperties().get(i).getName() });
            }
        }
        return prop;
    }

    private void buttonDoAct(PropertyAction prop) {

        prop.getDoActButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int row = prop.getPlayerPropertyTable().getSelectedRow();

                if (row < 0) {
                    JOptionPane.showMessageDialog(prop,
                            "No row is selected. Please select one row!",
                            "Select row", JOptionPane.ERROR_MESSAGE);
                } else {
                    current.setPropertySelected(row);
                }
            }
        });
    }

    private void showLand() {
        LandCard landCard = new LandCard();
        landCard.setNameLand(((Property) model.getBoard().getSquare(current.getPosition())).getName());
        landCard.setOneHouse(((Property) model.getBoard().getSquare(current.getPosition())).getGain()[1]);
        landCard.setTwoHouse(((Property) model.getBoard().getSquare(current.getPosition())).getGain()[2]);
        landCard.setThreeHouse(((Property) model.getBoard().getSquare(current.getPosition())).getGain()[3]);
        landCard.setFourHouse(((Property) model.getBoard().getSquare(current.getPosition())).getGain()[4]);
        landCard.setHomePrice(((Property) model.getBoard().getSquare(current.getPosition())).getPrice());
        landCard.setZeroPrice(((Property) model.getBoard().getSquare(current.getPosition())).getGain()[0]);
        landCard.setHotel(((Property) model.getBoard().getSquare(current.getPosition())).getGain()[5]);
        landCard.setMortgageValue(((Property) model.getBoard().getSquare(current.getPosition())).getPrice() / 2);
        landCard.setLocation(getBoard().getPropertyPanel().getLocationOnScreen());
        landCard.setVisible(true);
    }

    private void showService() {
        SocietyCard service = new SocietyCard();
        service.setAnniuties(((Property) model.getBoard().getSquare(current.getPosition())).getGain()[0]);
        service.setWithDoubleService(((Property) model.getBoard().getSquare(current.getPosition())).getGain()[0]);
        service.setMortgageValue(((Property) model.getBoard().getSquare(current.getPosition())).getPrice() / 2);
        service.setNameSociety(((Property) model.getBoard().getSquare(current.getPosition())).getName() +
                ". Si moltiplica per il valore ottenuto dai dadi");
        service.setLocation(getBoard().getPropertyPanel().getLocationOnScreen());
        service.setVisible(true);
    }

    private void showStation() {
        StationCard station = new StationCard();
        station.setOneStation(((Property) model.getBoard().getSquare(current.getPosition())).getGain()[0]);
        station.setTwoStation(((Property) model.getBoard().getSquare(current.getPosition())).getGain()[1]);
        station.setThreeStation(((Property) model.getBoard().getSquare(current.getPosition())).getGain()[2]);
        station.setFourStation(((Property) model.getBoard().getSquare(current.getPosition())).getGain()[3]);
        station.setPrice(((Property) model.getBoard().getSquare(current.getPosition())).getPrice());
        station.setMortgageValue(((Property) model.getBoard().getSquare(current.getPosition())).getPrice() / 2);
        station.setLocation(getBoard().getPropertyPanel().getLocationOnScreen());
        station.setVisible(true);
    }
}
