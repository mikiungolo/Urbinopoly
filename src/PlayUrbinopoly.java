import Controller.ControllerInitialize;
import Model.ModelGame.Urbinopoly;
import View.GUI.StartGui;

// Main class del gioco Urbinopoly
public class PlayUrbinopoly {

    public static void main(String[] args) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                StartGui viewStart = new StartGui();
                Urbinopoly model = new Urbinopoly();

                ControllerInitialize controllerInit = new ControllerInitialize(model, viewStart);
                controllerInit.init();
            }
        });
    }
}
