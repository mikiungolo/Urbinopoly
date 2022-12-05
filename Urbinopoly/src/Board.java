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
    private final Square[] squares = new Square[N_SQAURES];
    private final ProbabilityDeck prob;
    private final UnexpectedDeck unex;

    // costruttore tabellone
    public Board() {
        squares[GO] = new Square.Go();
        squares[1] = new Land("Vicolo Ducale", 60, null, Land.ColorUrbinopoly.MARRONE, 0);
        squares[2] = new Cards.Card.Probability();
        squares[3] = new Land("Vicolo Nuova Luce", 60, null, Land.ColorUrbinopoly.MARRONE, 0);
        squares[4] = new Taxes<Double>("Income Tax", 1.1);
        squares[5] = new Station("Adriabus 30", 200, null);
        squares[6] = new Land("Corso Reactive", 100, null, Land.ColorUrbinopoly.CELESTE, 0);
        squares[7] = new Cards.Card.Unexpected();
        squares[8] = new Land("Corso Gimnasim", 100, null, Land.ColorUrbinopoly.CELESTE, 0);
        squares[9] = new Land("Corso MadPlanet", 100, null, Land.ColorUrbinopoly.CELESTE, 0);
        squares[PRISON] = new Square.Prison();
        squares[11] = new Land("Via del Salame di Montefeltro", 140, null, Land.ColorUrbinopoly.ROSA, 0);
        squares[12] = new Service("Societa' Elettrica", 150, null);
        squares[13] = new Land("Via della Casciotta", 140, null, Land.ColorUrbinopoly.ROSA, 0);
        squares[14] = new Land("Via dell Crescia", 160, null, Land.ColorUrbinopoly.ROSA, 0);
        squares[15] = new Station("Adriabus 3m", 200, null);
        squares[16] = new Land("Parco della Rimembranza", 180, null, Land.ColorUrbinopoly.ARANCIONE, 0);
        squares[17] = new Cards.Card.Probability();
        squares[18] = new Land("Parco Belvedere", 180, null, Land.ColorUrbinopoly.ARANCIONE, 0);
        squares[19] = new Land("Parco della Resistenza", 200, null, Land.ColorUrbinopoly.ARANCIONE, 0);
        squares[PARKING] = new Square.Parking();
        squares[21] = new Land("Via del Duca", 220, null, Land.ColorUrbinopoly.ROSSO, 0);
        squares[22] = new Cards.Card.Unexpected();
        squares[23] = new Land("Via Carlo Bo", 220, null, Land.ColorUrbinopoly.ROSSO, 0);
        squares[24] = new Land("Via Raffaello", 240, null, Land.ColorUrbinopoly.ROSSO, 0);
        squares[25] = new Station("Adriabus 1", 200, null);
        squares[26] = new Land("Via Olivetti", 260, null, Land.ColorUrbinopoly.GIALLO, 0);
        squares[27] = new Land("Via Von Neumann", 260, null, Land.ColorUrbinopoly.GIALLO, 0);
        squares[28] = new Service("Fontane", 150, null);
        squares[29] = new Land("Via Turing", 280, null, Land.ColorUrbinopoly.GIALLO, 0);
        squares[GO_TO_PRISON] = new Square.GoToPrison();
        squares[31] = new Land("Via dell'Aquilone", 300, null, Land.ColorUrbinopoly.VERDE, 0);
        squares[32] = new Land("Via Accipizza", 300, null, Land.ColorUrbinopoly.VERDE, 0);
        squares[33] = new Cards.Card.Probability();
        squares[34] = new Land("Via del Ghiottone", 320, null, Land.ColorUrbinopoly.VERDE, 0);
        squares[35] = new Station("Adriabus 3d", 200, null);
        squares[36] = new Cards.Card.Unexpected();
        squares[37] = new Land("Fortezza Albornoz", 350, null, Land.ColorUrbinopoly.BLU, 0);
        squares[38] = new Taxes<Integer>("Luxury Tax", 200);
        squares[39] = new Land("Piazza della Repubblica", 400, null, Land.ColorUrbinopoly.BLU, 0);

        // creazione dei mazzi Probabilità e Imprevisti come parte del tabellone
        this.prob = new ProbabilityDeck();
        this.unex = new UnexpectedDeck();
    }

    // manipolazione del quadrato corrente
    public Square getSquare(int index) {
        return squares[index];
    }

    /*
     * comunicazione automatizzata tra quadrato e mazzo
     * se il giocatore si trova in determinate caselle
     * sarà pescata una carta del relativo mazzo
     */
    public Cards.CardApi takeCard(int position) {
        if (getSquare(position) instanceof Cards.Card.Probability) {
            return prob.getEvento();
        } else if (getSquare(position) instanceof Cards.Card.Unexpected) {
            return unex.getEvento();
        }
        return null;
    }
}
