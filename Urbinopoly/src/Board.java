import Cards.Card;

// modellazione del tabellone di gioco
public class Board {
    // definizione di costanti per la costruzione del tabellone
    public static final int N_SQAURES = 40;
    public static final int GO = 0;
    public static final int PRISON = 10;
    public static final int PARKING = 20;
    public static final int GO_TO_PRISON = 30;

    // il tabellone è un insieme di quadrati
    private Square[] board = new Square[N_SQAURES];

    // costruttore tabellone
    public Board() {
        board[GO] = new Square.Go();
        board[1] = new Land("Vicolo Ducale", 60, null, Land.ColorUrbinopoly.MARRONE, 0);
        board[2] = new Cards.Card("Probability", Cards.Card.TypeCard.PROBABILITY, null);
        board[3] = new Land("Vicolo Nuova Luce", 60, null, Land.ColorUrbinopoly.MARRONE, 0);
        board[4] = new Taxes<Double>("Income Tax", 1.1);
        board[5] = new Station("Adriabus 30", 200, null);
        board[6] = new Land("Corso Reactive", 100, null, Land.ColorUrbinopoly.CELESTE, 0);
        board[7] = new Cards.Card("Unexpected", Cards.Card.TypeCard.UNEXPECTED, null);
        board[8] = new Land("Corso Gimnasim", 100, null, Land.ColorUrbinopoly.CELESTE, 0);
        board[9] = new Land("Corso MadPlanet", 100, null, Land.ColorUrbinopoly.CELESTE, 0);
        board[PRISON] = new Square.Prison();
        board[11] = new Land("Via del Salame di Montefeltro", 140, null, Land.ColorUrbinopoly.ROSA, 0);
        board[12] = new Service("Societa' Elettrica", 150, null);
        board[13] = new Land("Via della Casciotta", 140, null, Land.ColorUrbinopoly.ROSA, 0);
        board[14] = new Land("Via dell Crescia", 160, null, Land.ColorUrbinopoly.ROSA, 0);
        board[15] = new Station("Adriabus 3m", 200, null);
        board[16] = new Land("Parco della Rimembranza", 180, null, Land.ColorUrbinopoly.ARANCIONE, 0);
        board[17] = new Cards.Card("Probabilities", Cards.Card.TypeCard.PROBABILITY, null);
        board[18] = new Land("Parco Belvedere", 180, null, Land.ColorUrbinopoly.ARANCIONE, 0);
        board[19] = new Land("Parco della Resistenza", 200, null, Land.ColorUrbinopoly.ARANCIONE, 0);
        board[PARKING] = new Square.Parking();
        board[21] = new Land("Via del Duca", 220, null, Land.ColorUrbinopoly.ROSSO, 0);
        board[22] = new Cards.Card("Unexpected", Cards.Card.TypeCard.UNEXPECTED, null);
        board[23] = new Land("Via Carlo Bo", 220, null, Land.ColorUrbinopoly.ROSSO, 0);
        board[24] = new Land("Via Raffaello", 240, null, Land.ColorUrbinopoly.ROSSO, 0);
        board[25] = new Station("Adriabus 1", 200, null);
        board[26] = new Land("Via Olivetti", 260, null, Land.ColorUrbinopoly.GIALLO, 0);
        board[27] = new Land("Via Von Neumann", 260, null, Land.ColorUrbinopoly.GIALLO, 0);
        board[28] = new Service("Fontane", 150, null);
        board[29] = new Land("Via Turing", 280, null, Land.ColorUrbinopoly.GIALLO, 0);
        board[GO_TO_PRISON] = new Square.GoToPrison();
        board[31] = new Land("Via dell'Aquilone", 300, null, Land.ColorUrbinopoly.VERDE, 0);
        board[32] = new Land("Via Accipizza", 300, null, Land.ColorUrbinopoly.VERDE, 0);
        board[33] = new Cards.Card("Probability", Cards.Card.TypeCard.PROBABILITY, null);
        board[34] = new Land("Via del Ghiottone", 320, null, Land.ColorUrbinopoly.VERDE, 0);
        board[35] = new Station("Adriabus 3d", 200, null);
        board[36] = new Cards.Card("Unexpected", Cards.Card.TypeCard.UNEXPECTED, null);
        board[37] = new Land("Fortezza Albornoz", 350, null, Land.ColorUrbinopoly.BLU, 0);
        board[38] = new Taxes<Integer>("Luxury Tax", 200);
        board[39] = new Land("Piazza della Repubblica", 400, null, Land.ColorUrbinopoly.BLU, 0);
    }
}
