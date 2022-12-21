package Controller;

import Model.ModelGame.Urbinopoly;
import View.GUI.BoardGui;
import View.GUI.InsertPlayers;
import View.GUI.StartGui;

// Controller che gestirà la comunicazione tra modello e view
public class ControllerUrbinopoly {

    // riferimenti a view e modello
    Urbinopoly model;
    StartGui viewStart;
    InsertPlayers viewPlayers;
    BoardGui viewBoard;

    // costruttore
    public ControllerUrbinopoly(Urbinopoly model, StartGui viewStart, InsertPlayers viewPlayers, BoardGui viewBoard) {
        this.model = model;
        this.viewStart = viewStart;
        this.viewPlayers = viewPlayers;
        this.viewBoard = viewBoard;

        addListener();
    }

    // getter
    public Urbinopoly getModel() {
        return model;
    }

    public StartGui getViewStart() {
        return viewStart;
    }

    public InsertPlayers getViewPlayers() {
        return viewPlayers;
    }

    public BoardGui getViewBoard() {
        return viewBoard;
    }

    // metodo del controllore che gestirà l'intera esecuzione
    public void game() {
        showGui();

    }

    private void addListener() {
        getViewPlayers().addStartListener(new startListener());
    }

    // inizializzazione della gui
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
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartGui().setVisible(true);
            }
        });
    }
}
