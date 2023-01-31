package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Model.ModelGame.Urbinopoly;
import Model.ModelGame.Player.Player;
import View.GUI.BoardGui;
import View.GUI.PrisonGui;
import View.GUI.PropertyAction;

public class ControllerGame {

    // riferimenti alla View del board e al modello
    private BoardGui board;
    private PrisonGui prison;
    private Urbinopoly model;

    // riferimenti al controller del Player
    private ControllerPlayer ctrlP;

    // controllo per il player
    private int indexCurrentPlayer;
    private Player current;
    PropertyAction prop = new PropertyAction();

    // cosrtuttore del ControllerGrame
    public ControllerGame(Urbinopoly model, BoardGui board) {
        this.model = model;
        this.prison = new PrisonGui();
        this.indexCurrentPlayer = -1;
        this.board = board;
        this.current = new Player();
        this.ctrlP = new ControllerPlayer(model, board, prison);
    }

    // getter

    public BoardGui getBoard() {
        return this.board;
    }

    public Urbinopoly getModel() {
        return model;
    }

    public ControllerPlayer getCtrlP() {
        return ctrlP;
    }

    public PropertyAction getProp() {
        return prop;
    }

    public void setProp(PropertyAction prop) {
        this.prop = prop;
    }

    public PrisonGui getPrison() {
        return prison;
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
            if (current.isInPrison()) {
                if (i < 6) {
                    board.getOption()[i].setEnabled(false);
                } else {
                    board.getOption()[i].setEnabled(true);
                }
            } else {
                if (model.validateCommand(i + 1, p)) {
                    board.getOption()[i].setEnabled(true);
                } else {
                    board.getOption()[i].setEnabled(false);
                }
            }
        }
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
        buttonDoAct();
    }

    // definizione di tutti gli ascoltatori
    private void addCautionPrison() {

        // Creazione listener per pulsante EndTurn
        prison.getCautionPrisonButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                current.setOptionCommand(1);
                getModel().playerAction(current);
            }
        });
    }

    private void addLuckPrison() {
        // Creazione listener per pulsante EndTurn
        prison.getRollPrisonButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                current.setOptionCommand(3);
                getModel().playerAction(current);
            }
        });
    }

    private void addCardPrison() {

        // Creazione listener per pulsante EndTurn
        prison.getCardPrisonButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                current.setOptionCommand(2);
                getModel().playerAction(current);
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
                getBoard().getProbTextArea().append("");
                getBoard().getUnexpTextArea().append("");

                current = model.getPlayers().getNextPlayer(indexCurrentPlayer);
                indexCurrentPlayer++;
                getBoard().getHighlight().append("\nTurno del Player: " + current.getName());

                if (current.isInPrison()) {
                    getPrison().setVisible(true);
                }
                validateBoard(board, current);
                getCtrlP().updateAction(current);
            }
        });
    }

    private void addRollListener() {

        // Creazione listener per pulsante Roll
        getBoard().getRollButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                current.setOptionCommand(6);

                int oldPos = current.getPosition();
                getModel().getDice().roll();
                current.move(getModel().getDice().getTotalValue());

                getModel().doAction(current);
                getCtrlP().updateAction(current);
                getCtrlP().updateViewPosition(current, indexCurrentPlayer % model.getPlayers().getInGame().size(),
                        oldPos);

                // controllo sconfitta del Player con eventuale rimozione
                getModel().getPlayers().remove(current);
                if (!current.isInPrison()) {
                    getModel().playerAction(current);
                }

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
                getCtrlP().updateViewBalance(current, indexCurrentPlayer % model.getPlayers().getInGame().size());
                current.setOptionRolled(true);
                validateBoard(board, current);
            }
        });
    }

    private void addRemBuildListener() {

        // Creazione listener per pulsante remove House
        getBoard().getRemHouseButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                current.setOptionCommand(5);
                setProp(showListProperty(current));
                getBoard().getHighlight().append("\n" + current.getName() + " rimuove una casa!");
            }
        });
    }

    private void addBuildListener() {

        // Creazione listener per pulsante Build House
        getBoard().getBuildButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                current.setOptionCommand(4);
                setProp(showListProperty(current));
                getBoard().getHighlight().append("\n" + current.getName() + " costruisce una casa!");
            }
        });
    }

    private void addRemMortgageListener() {

        // Creazione listener per pulsante di rimozione ipoteca
        getBoard().getRemoveMortButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                current.setOptionCommand(3);
                setProp(showListProperty(current));
                getBoard().getHighlight().append("\n" + current.getName() + " rimuove un'ipoteca!");
            }
        });
    }

    private void addMortgageListener() {

        // Creazione listener per pulsante ipoteca
        getBoard().getMortageButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                current.setOptionCommand(2);
                setProp(showListProperty(current));
                getBoard().getHighlight().append("\n" + current.getName() + " ipoteca!");
            }
        });
    }

    private void addBuyListener() {

        // Creazione listener per pulsante per acquisto proprietà
        getBoard().getBuyButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                current.setOptionCommand(1);

                model.playerAction(current);
                getCtrlP().updateViewBalance(current, indexCurrentPlayer % model.getPlayers().getInGame().size());
                validateBoard(board, current);
                // getBoard().getHighlight().append(current.getName() + " compra la proprietà
                // per " +);
            }
        });
    }

    private PropertyAction showListProperty(Player p) {

        if (!p.getProperties().isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) prop.getPlayerPropertyTable().getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                model.removeRow(i);
            }
            for (int i = 0; i < p.getProperties().size(); i++) {
                model.addRow(new Object[] { p.getProperties().get(i).getName() });
            }
        }
        prop.setLocation(300, 250);
        prop.setVisible(true);
        return prop;
    }

    private void buttonDoAct() {

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
                    model.playerAction(current);
                    getCtrlP().updateViewBalance(current, indexCurrentPlayer % model.getPlayers().getInGame().size());
                }
            }
        });
    }
}
