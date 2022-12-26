package Controller;

import Model.ModelGame.Urbinopoly;
import View.GUI.StartGui;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Controller che gestirà la comunicazione tra modello e view
public class ControllerUrbinopoly {

    // riferimenti a view e modello
    Urbinopoly model;
    StartGui viewStart;

    // Creazione di listener per i pulsanti principali del gioco
    ActionListener start;
    ActionListener buy;
    ActionListener mortgage;
    ActionListener remMortgage;
    ActionListener build;
    ActionListener remBuild;
    ActionListener roll;
    ActionListener endTurn;

    // costruttore
    public ControllerUrbinopoly(Urbinopoly model, StartGui viewStart) {
        this.model = model;
        this.viewStart = viewStart;
    }

    // getter
    public Urbinopoly getModel() {
        return model;
    }

    public StartGui getViewStart() {
        return viewStart;
    }

    // metodo del controllore che gestirà l'intera esecuzione
    public void game() {
        addListener();
        showGui();

        // gameplay
        gameplay();
    }

    private void addListener() {
        // Creazione listener pulsante start
        addStartListener();
        addBuyListener();
        addMortgageListener();
        addRemMortgageListener();
        addBuildListener();
        addRemBuildListener();
        addRollListener();
        addEndTurnListener();
    }

    private void addEndTurnListener() {
        // Creazione listener per pulsante EndTurn
        endTurn = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                model
                
            }
            
        };
    }

    private void addRollListener() {
        // Creazione listener per pulsante Roll
        roll = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }

        };
    }

    private void addRemBuildListener() {
        // Creazione listener per pulsante remove House
        remBuild = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }

        };
    }

    private void addBuildListener() {
        // Creazione listener per pulsante Build House
        build = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }

        };
    }

    private void addRemMortgageListener() {
        // Creazione listener per pulsante di rimozione ipoteca
        remMortgage = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }

        };
    }

    private void addMortgageListener() {
        // Creazione listener per pulsante ipoteca
        mortgage = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }

        };
    }

    private void addBuyListener() {
        // Creazione listener per pulsante per acquisto proprietà
        buy = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }

        };
    }

    private void addStartListener() {
        // Creazione istener pulasante start

        start = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (getViewStart().getViewPlayer().getPlayerNameTable().getRowCount() < 2) {
                    JOptionPane.showMessageDialog(getViewStart(), "Player must be at least two!!", "Impossible",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    getViewStart().getViewPlayer().setNames(getViewStart().getViewPlayer().getNamePlayers());
                    getViewStart().getViewPlayer().setNamesInBoardGui(getViewStart().getViewPlayer().getNames())
                            .setVisible(true);
                    getViewStart().dispose();
                    model.getPlayers().buildPlayers(getViewStart().getViewPlayer().getNamePlayers());
                }
            }
        };
        getViewStart().getViewPlayer().getStartGamePlayerButton().addActionListener(start);
    }

    // controllo inizializzazione della gui
    private void showGui() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        viewStart.setVisible(true);
    }

    // struttura dell'intero gameplay
    public void gameplay() {

        int indexCurrentPlayer = -1;
        // esecuzione dei turni. Sincronizzazione del modello e controller per il
        // gameplay
        while (!model.isEndGame()) {
            // prima della logica del turno aggiorno la view

            indexCurrentPlayer = model.turn(model.getPlayers().getNextPlayer(indexCurrentPlayer));
        }

        // il gioco deve terminare!!
        if (model.isEndGame()) {
            System.out.println("Gioco terminato");
        }
    }
}
