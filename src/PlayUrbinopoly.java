import java.util.Optional;

import Controller.ControllerUrbinopoly;
import Model.ModelGame.Urbinopoly;
import View.GUI.BoardGui;
import View.GUI.InsertPlayers;
import View.GUI.StartGui;

// Main class del gioco Urbinopoly
public class PlayUrbinopoly {
    public static void main(String[] args) {

        StartGui viewStart = new StartGui();
        InsertPlayers viewPlayers = new InsertPlayers();
        BoardGui viewBoard = new BoardGui();
        Urbinopoly model = new Urbinopoly(takeNamePlayer);
        ControllerUrbinopoly controller = new ControllerUrbinopoly(model, viewStart, viewPlayers, viewBoard);

        controller.game();
    }
}
