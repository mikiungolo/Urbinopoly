package Controller;

import Model.ModelGame.Urbinopoly;
import Model.ModelGame.Board.Pieces.Property.Property;
import Model.ModelGame.Player.Player;
import View.GUI.BoardGui;
import View.GUI.LandCard;
import View.GUI.PrisonGui;
import View.GUI.SocietyCard;
import View.GUI.StationCard;

/* classe controller per la gestione di tutte le situazioni legate 
 * alla posizione, bilancio e azioni del Player
 */
public class ControllerPlayer {

    // attributi del controller per il player
    private Urbinopoly model;
    private BoardGui board;
    private PrisonGui prison;

    // costruttore
    public ControllerPlayer(Urbinopoly model, BoardGui board, PrisonGui p) {
        this.model = model;
        this.board = board;
        this.prison = p;
    }

    // getter
    public Urbinopoly getModel() {
        return model;
    }

    public BoardGui getBoard() {
        return board;
    }

    public PrisonGui getPrison() {
        return prison;
    }

    // metodi per la visualizzazione delle carte proprietà

    // visualizza terreno
    private void showLand(Player p) {
        LandCard landCard = new LandCard();
        landCard.setNameLand(((Property) model.getBoard().getSquare(p.getPosition())).getName());
        landCard.setOneHouse(((Property) model.getBoard().getSquare(p.getPosition())).getGain()[1]);
        landCard.setTwoHouse(((Property) model.getBoard().getSquare(p.getPosition())).getGain()[2]);
        landCard.setThreeHouse(((Property) model.getBoard().getSquare(p.getPosition())).getGain()[3]);
        landCard.setFourHouse(((Property) model.getBoard().getSquare(p.getPosition())).getGain()[4]);
        landCard.setHomePrice(((Property) model.getBoard().getSquare(p.getPosition())).getPrice());
        landCard.setZeroPrice(((Property) model.getBoard().getSquare(p.getPosition())).getGain()[0]);
        landCard.setHotel(((Property) model.getBoard().getSquare(p.getPosition())).getGain()[5]);
        landCard.setMortgageValue(((Property) model.getBoard().getSquare(p.getPosition())).getPrice() / 2);
        landCard.setLocation(getBoard().getPropertyPanel().getLocationOnScreen());
        landCard.setVisible(true);
    }

    // visualizza servizio
    private void showService(Player p) {
        SocietyCard service = new SocietyCard();
        service.setAnniuties(((Property) model.getBoard().getSquare(p.getPosition())).getGain()[0]);
        service.setWithDoubleService(((Property) model.getBoard().getSquare(p.getPosition())).getGain()[0]);
        service.setMortgageValue(((Property) model.getBoard().getSquare(p.getPosition())).getPrice() / 2);
        service.setNameSociety(((Property) model.getBoard().getSquare(p.getPosition())).getName() +
                ". Si moltiplica per il valore ottenuto dai dadi");
        service.setLocation(getBoard().getPropertyPanel().getLocationOnScreen());
        service.setVisible(true);
    }

    // visualizza stazione
    private void showStation(Player p) {
        StationCard station = new StationCard();
        station.setOneStation(((Property) model.getBoard().getSquare(p.getPosition())).getGain()[0]);
        station.setTwoStation(((Property) model.getBoard().getSquare(p.getPosition())).getGain()[1]);
        station.setThreeStation(((Property) model.getBoard().getSquare(p.getPosition())).getGain()[2]);
        station.setFourStation(((Property) model.getBoard().getSquare(p.getPosition())).getGain()[3]);
        station.setPrice(((Property) model.getBoard().getSquare(p.getPosition())).getPrice());
        station.setMortgageValue(((Property) model.getBoard().getSquare(p.getPosition())).getPrice() / 2);
        station.setLocation(getBoard().getPropertyPanel().getLocationOnScreen());
        station.setVisible(true);
    }

    // metodi relativi all' aggiornamento dei valori del Player

    // aggiorna Bilancio
    public void updateViewBalance(Player p, int index) {
        getBoard().setBalance(p.getBalance(), index);
    }

    // aggiorna Posizioen
    public void updateViewPosition(Player p, int index, int old) {
        getBoard().getDie1().setText(Integer.toString(model.getDice().getDice()[0]));
        getBoard().getDie2().setText(Integer.toString(model.getDice().getDice()[1]));
        getBoard().getHighlight().append(p.getName() + " vola dalla posizione " + old +
                " alla posizione " + p.getPosition() + "\n");
        getBoard().setPos(p.getPosition(), index);
    }

    // mostra aggiornamenti a seconda delle azioni effettuate
    public void updateAction(Player p) {
        switch (model.getBoard().getSquare(p.getPosition()).getNature()) {

            case LAND -> showLand(p);
            case SERVICE -> showService(p);
            case STATION -> showStation(p);
            case PROBABILITY -> getBoard().getProbTextArea().append(
                    model.getBoard().getProb().getCurrentCard().getMessage());
            case UNEXPECTED -> getBoard().getUnexpTextArea().append(
                    model.getBoard().getUnex().getCurrentCard().getMessage());
            default -> {
            }
        }
    }

}
