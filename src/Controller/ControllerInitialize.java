package Controller;

import Model.ModelGame.Urbinopoly;
import View.GUI.BoardGui;
import View.GUI.StartGui;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Controller che gestirà la comunicazione tra modello e view
public class ControllerInitialize {

    // riferimenti a view e modello
    private final Urbinopoly model;
    private final StartGui viewStart;
    private BoardGui board;
    private ControllerGame game;

    // variabili di controllo
    int indexCurrentPlayer;

    // costruttore
    public ControllerInitialize(Urbinopoly model, StartGui viewStart) {
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

    public BoardGui getBoard() {
        return board;
    }

    public ControllerGame getGame() {
        return game;
    }

    // metodo del controllore che gestirà l'intera esecuzione
    public void init() {
        addFirstListener();
        showGui();
    }

    private void addFirstListener() {
        viewStart.getStartButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addStartListener();
                viewStart.getViewPlayer().setVisible(true);
                viewStart.dispose();
            }
        });
    }

    // aggiunge ascoltatore al bottone start
    private void addStartListener() {

        getViewStart().getViewPlayer().getStartGamePlayerButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (getViewStart().getViewPlayer().getPlayerNameTable().getRowCount() < 2) {
                    JOptionPane.showMessageDialog(getViewStart().getViewPlayer(), "Player must be at least two!!",
                            "Impossible",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    getViewStart().getViewPlayer().setNames(getViewStart().getViewPlayer().getNamePlayers());

                    model.getPlayers().buildPlayers(getViewStart().getViewPlayer().getNames());
                    getViewStart().getViewPlayer().dispose();

                    // creazione del tabellone di gioco
                    board = new BoardGui(getViewStart().getViewPlayer().getNames());
                    game = new ControllerGame(model, board);
                    game.game();
                }
            }
        });
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
}
