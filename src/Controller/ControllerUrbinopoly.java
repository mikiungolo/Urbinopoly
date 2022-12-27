package Controller;

import Model.ModelGame.Urbinopoly;
import Model.ModelGame.Player.Player;
import View.GUI.BoardGui;
import View.GUI.LandCard;
import View.GUI.PrisonGui;
import View.GUI.PropertyAction;
import View.GUI.SocietyCard;
import View.GUI.StartGui;
import View.GUI.StationCard;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Controller che gestirà la comunicazione tra modello e view
public class ControllerUrbinopoly {

    // riferimenti a view e modello
    private final Urbinopoly model;
    private final StartGui viewStart;
    private BoardGui board;
    private PrisonGui prison;

    // Creazione di listener per i pulsanti principali del gioco
    ActionListener start;
    ActionListener buy;
    ActionListener mortgage;
    ActionListener remMortgage;
    ActionListener build;
    ActionListener remBuild;
    ActionListener roll;
    ActionListener endTurn;
    ActionListener caution;
    ActionListener card;
    ActionListener luckPrison;

    // variabili di controllo
    int indexCurrentPlayer;
    int rowsArea;

    // costruttore
    public ControllerUrbinopoly(Urbinopoly model, StartGui viewStart) {
        this.model = model;
        this.viewStart = viewStart;
        this.indexCurrentPlayer = -1;
        this.rowsArea = 0;
        this.prison = new PrisonGui();
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

    // metodo del controllore che gestirà l'intera esecuzione
    public void game() {
        showGui();
        addStartListener();
    }

    private void addListener() {
        // Creazione listener pulsanti del board game

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

    private void addCautionPrison() {
        Player currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
        // Creazione listener per pulsante EndTurn
        caution = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                currentP.setOptionCommand(1);
            }
        };
        prison.getCautionPrisonButton().addActionListener(caution);
    }

    private void addLuckPrison() {
        Player currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
        // Creazione listener per pulsante EndTurn
        luckPrison = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                currentP.setOptionCommand(3);
            }
        };
        prison.getRollPrisonButton().addActionListener(luckPrison);
    }

    private void addCardPrison() {
        Player currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
        // Creazione listener per pulsante EndTurn
        card = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                currentP.setOptionCommand(2);
            }
        };
        prison.getCardPrisonButton().addActionListener(card);
    }

    private void addEndTurnListener() {
        Player currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
        // Creazione listener per pulsante EndTurn
        endTurn = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                currentP.setOptionCommand(7);
            }
        };
        board.getEndTurn().addActionListener(endTurn);
    }

    private void addRollListener() {
        Player currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
        // Creazione listener per pulsante Roll
        roll = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                currentP.setOptionCommand(6);
            }
        };
        board.getRollButton().addActionListener(roll);
    }

    private void addRemBuildListener() {
        Player currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
        // Creazione listener per pulsante remove House
        remBuild = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                currentP.setOptionCommand(5);
                JFrame prop = showListProperty();
            }
        };
        board.getRemHouseButton().addActionListener(remBuild);
    }

    private void addBuildListener() {
        Player currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
        // Creazione listener per pulsante Build House
        build = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                currentP.setOptionCommand(4);
                JFrame prop = showListProperty();
            }
        };
        board.getBuildButton().addActionListener(build);
    }

    private void addRemMortgageListener() {
        Player currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
        // Creazione listener per pulsante di rimozione ipoteca
        remMortgage = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                currentP.setOptionCommand(3);
                JFrame prop = showListProperty();
            }
        };
        board.getRemoveMortButton().addActionListener(remMortgage);
    }

    private void addMortgageListener() {
        Player currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
        // Creazione listener per pulsante ipoteca
        mortgage = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                currentP.setOptionCommand(2);
                JFrame prop = showListProperty();
            }
        };
        board.getMortageButton().addActionListener(mortgage);
    }

    private void addBuyListener() {
        Player currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
        // Creazione listener per pulsante per acquisto proprietà
        buy = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                currentP.setOptionCommand(1);
                switch (model.getBoard().getSquare(currentP.getPosition()).getNature()) {
                    case LAND -> showLand();
                    case SERVICE -> showService();
                    case STATION -> showStation();
                    default -> {
                    }
                }
            }
        };
        board.getBuyButton().addActionListener(buy);
    }

    private JFrame showListProperty() {
        JFrame prop = new PropertyAction();
        prop.setLocation(300, 250);
        prop.setVisible(true);
        return prop;
    }

    private JFrame showLand() {
        JFrame landCard = new LandCard();
        landCard.setLocation(board.getPropertyPanel().getLocationOnScreen());
        landCard.setVisible(true);
        return landCard;
    }

    private JFrame showService() {
        JFrame service = new SocietyCard();
        service.setLocation(board.getPropertyPanel().getLocationOnScreen());
        service.setVisible(true);
        return service;
    }

    private JFrame showStation() {
        JFrame station = new StationCard();
        station.setLocation(board.getPropertyPanel().getLocationOnScreen());
        station.setVisible(true);
        return station;
    }

    private void addStartListener() {
        // Creazione istener pulasante start

        start = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (getViewStart().getViewPlayer().getPlayerNameTable().getRowCount() < 2) {
                    JOptionPane.showMessageDialog(getViewStart().getViewPlayer(), "Player must be at least two!!",
                            "Impossible",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    getViewStart().getViewPlayer().setNames(getViewStart().getViewPlayer().getNamePlayers());
                    board = getViewStart().getViewPlayer()
                            .setNamesInBoardGui(getViewStart().getViewPlayer().getNames());
                    board.setVisible(true);
                    getViewStart().getViewPlayer().dispose();
                    model.getPlayers().buildPlayers(getViewStart().getViewPlayer().getNamePlayers());
                    // dopo aver ottenuto le informazioni sui Players fornisco gli ascoltatori per
                    // il gioco
                    addListener();
                    // gameplay
                    gameplay();
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

        Player currentP;

        // esecuzione dei turni. Sincronizzazione del modello e controller per il
        // gameplay
        while (!model.isEndGame()) {

            // ottenimento del prossimo player in gioco
            currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
            // messaggio in Highlights
            getBoard().getHighlight().insert("Turno del Player: " + currentP.getName(), rowsArea);
            rowsArea++;

            // settaggio dei comandi
            model.setInTurn(true);
            currentP.setOptionRolled(false);

            /*
             * fin tanto che il player corrente non ha selezionato
             * il tiro dei dadi e non ha espresso la fine del proprio turno può continuare
             * le proprie mosse di gioco
             */
            while (model.isInTurn()) {
                /*
                 * nel corso del proprio turno il Player corrente può
                 * optare per tutte le opzioni a lui disponibili.
                 */
                if (!currentP.isInPrison()) {
                    if (model.validateCommand(currentP.getOptionCommand(), currentP)) {
                        model.playerAction(currentP, currentP.getOptionCommand());
                    }
                } else {
                    currentP.setOptionCommand(0);
                    prison.setVisible(true);
                    while (currentP.getOptionCommand() == 0)
                        ;
                    prison.dispose();
                    model.playerAction(currentP, currentP.getOptionCommand());
                }

                /*
                 * se indica l'opzione di tiro si esegue il giro
                 * si controlla che il Player non abbia scelto la terminazione
                 * del turno e che non sia in prigione
                 */
                if (currentP.isOptionRolled() && !currentP.isInPrison() && model.isInTurn()) {
                    // azione di roll
                    indexCurrentPlayer = model.turn(model.getPlayers().getNextPlayer(indexCurrentPlayer));
                    board.setPos(currentP.getPosition(), indexCurrentPlayer);
                    board.setBalance(currentP.getBalance(), indexCurrentPlayer);
                }
            }
        }

        // il gioco deve terminare!!
        if (model.isEndGame()) {
            System.out.println("Gioco terminato");
        }
    }

}
