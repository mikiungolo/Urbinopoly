package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Model.ModelGame.Urbinopoly;
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

    // variabili di controllo per il board
    private int indexCurrentPlayer;
    private int opt;
    private int bal;
    private int pos;

    // attributi di sincronizzazione Thread
    private final ReentrantLock mutex;
    private final Condition cond;

    // cosrtuttore del ControllerGrame
    public ControllerGame(Urbinopoly model, BoardGui board) {
        this.model = model;
        this.prison = new PrisonGui();
        this.indexCurrentPlayer = -1;
        this.board = board;
        this.mutex = new ReentrantLock();
        this.cond = mutex.newCondition();
    }

    // getter
    public int getOpt() {
        return opt;
    }

    public ReentrantLock getMutex() {
        return mutex;
    }

    public Condition getCond() {
        return cond;
    }

    public BoardGui getBoard() {
        return this.board;
    }

    public int getBal() {
        return bal;
    }

    public int getPos() {
        return pos;
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
        Thread turn;
        Thread view;
        ControllerGame c = this;

        // esecuzione dei turni. Sincronizzazione del modello e controller per il
        // gameplay
        while (!model.isEndGame()) {

            mutex.lock();

            // ottenimento del prossimo player in gioco
            Player currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
            this.opt = currentP.getOptionCommand();
            this.bal = currentP.getBalance();
            this.pos = currentP.getPosition();

            model.setInTurn(true);

            // gestione dell'input in view
            view = new Thread() {
                @Override
                public void run() {

                    // messaggio in Highlights
                    getBoard().getHighlight().append("Turno del Player: " + currentP.getName() + "\n");

                    updateView(currentP, c);
                }
            };
            view.start();

            // gestione del turno/modello
            turn = new Thread() {
                @Override
                public void run() {

                    indexCurrentPlayer = model.turn(currentP, c);
                }
            };
            turn.start();

            while (model.isInTurn()) {
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mutex.lock();
                do {
                    if (opt != currentP.getOptionCommand()) {
                        mutex.unlock();
                        cond.signal();
                    }
                } while (opt == currentP.getOptionCommand() || mutex.isLocked());

            }
            // attesa terminazione thread per il turno corrente
            try {
                turn.join();
                view.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // il gioco deve terminare!!
        if (model.isEndGame()) {
            getBoard().getHighlight()
                    .append("\n Il gioco è terminato. Sviluppatori:\n Ungolo Michelangelo, Sette Miriana " + "\n");
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

    private void updateView(Player p, ControllerGame c) {
        // aggiornamento della view

        while (model.isInTurn()) {
            validateBoard(board, p);
            c.getMutex().lock();

            while (c.getBal() == p.getBalance() && c.getPos() == p.getPosition()) {
                try {
                    validateBoard(board, p);
                    c.getCond().await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            switch (p.getOptionCommand()) {

                case 1, 2, 3, 4, 5 -> {
                    getBoard().setBalance(p.getBalance(), indexCurrentPlayer);
                }
                case 6 -> {
                    getBoard().getDie1().setText(Integer.toString(model.getDice().getDice()[0]));
                    getBoard().getDie2().setText(Integer.toString(model.getDice().getDice()[1]));
                    getBoard().setPos(p.getPosition(), indexCurrentPlayer);
                }
            }
            c.getMutex().unlock();
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
                Player currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
                currentP.setOptionCommand(7);
            }
        });
    }

    private void addRollListener() {

        // Creazione listener per pulsante Roll
        getBoard().getRollButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Player currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
                currentP.setOptionCommand(6);
            }
        });
    }

    private void addRemBuildListener() {

        // Creazione listener per pulsante remove House
        getBoard().getRemHouseButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Player currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
                currentP.setOptionCommand(5);
                PropertyAction prop = showListProperty(currentP);
                buttonDoAct(prop);
            }
        });
    }

    private void addBuildListener() {

        // Creazione listener per pulsante Build House
        getBoard().getBuildButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Player currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
                currentP.setOptionCommand(4);
                PropertyAction prop = showListProperty(currentP);
                buttonDoAct(prop);
            }
        });
    }

    private void addRemMortgageListener() {

        // Creazione listener per pulsante di rimozione ipoteca
        getBoard().getRemoveMortButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Player currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
                currentP.setOptionCommand(3);
                PropertyAction prop = showListProperty(currentP);
                buttonDoAct(prop);
            }
        });
    }

    private void addMortgageListener() {

        // Creazione listener per pulsante ipoteca
        getBoard().getMortageButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Player currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
                currentP.setOptionCommand(2);
                PropertyAction prop = showListProperty(currentP);
                buttonDoAct(prop);
            }
        });
    }

    private void addBuyListener() {

        // Creazione listener per pulsante per acquisto proprietà
        getBoard().getBuyButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Player currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
                currentP.setOptionCommand(1);
                switch (model.getBoard().getSquare(currentP.getPosition()).getNature()) {
                    case LAND -> showLand();
                    case SERVICE -> showService();
                    case STATION -> showStation();
                    default -> {
                    }
                }
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
                Player currentP = model.getPlayers().getNextPlayer(indexCurrentPlayer);
                int row = prop.getPlayerPropertyTable().getSelectedRow();

                if (row < 0) {
                    JOptionPane.showMessageDialog(prop,
                            "No row is selected. Please select one row!",
                            "Select row", JOptionPane.ERROR_MESSAGE);
                } else {
                    currentP.setPropertySelected(row);
                }
            }
        });
    }

    private JFrame showLand() {
        JFrame landCard = new LandCard();
        landCard.setLocation(getBoard().getPropertyPanel().getLocationOnScreen());
        landCard.setVisible(true);
        return landCard;
    }

    private JFrame showService() {
        JFrame service = new SocietyCard();
        service.setLocation(getBoard().getPropertyPanel().getLocationOnScreen());
        service.setVisible(true);
        return service;
    }

    private JFrame showStation() {
        JFrame station = new StationCard();
        station.setLocation(getBoard().getPropertyPanel().getLocationOnScreen());
        station.setVisible(true);
        return station;
    }
}
