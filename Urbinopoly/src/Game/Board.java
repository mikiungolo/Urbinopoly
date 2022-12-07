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
        squares[1] = new Land("Vicolo Ducale", 60, new int[] { 2, 10, 30, 90, 160, 250 },
                Land.ColorUrbinopoly.MARRONE, 50);
        squares[2] = new Cards.Card.Probability();
        squares[3] = new Land("Vicolo Nuova Luce", 60, new int[] { 4, 20, 60, 180, 320, 450 },
                Land.ColorUrbinopoly.MARRONE, 50);
        squares[4] = new Taxes<Double>("Income Tax", 1.1);
        squares[5] = new Station("Adriabus 30", 200, new int[] { 25, 50, 100, 200 });
        squares[6] = new Land("Corso Reactive", 100, new int[] { 6, 30, 90, 270, 400, 550 },
                Land.ColorUrbinopoly.CELESTE, 50);
        squares[7] = new Cards.Card.Unexpected();
        squares[8] = new Land("Corso Gimnasim", 100, new int[] { 6, 30, 90, 270, 400, 550 },
                Land.ColorUrbinopoly.CELESTE, 50);
        squares[9] = new Land("Corso MadPlanet", 120, new int[] { 8, 40, 100, 300, 450, 600 },
                Land.ColorUrbinopoly.CELESTE, 50);
        squares[PRISON] = new Square.Prison();
        squares[11] = new Land("Via del Salame di Montefeltro", 140, new int[] { 10, 50, 150, 450, 625, 750 },
                Land.ColorUrbinopoly.ROSA, 100);
        squares[12] = new Service("Societa' Elettrica", 150, new int[] { 4, 10 });
        squares[13] = new Land("Via della Casciotta", 140, new int[] { 10, 50, 150, 450, 625, 750 },
                Land.ColorUrbinopoly.ROSA, 100);
        squares[14] = new Land("Via dell Crescia", 160, new int[] { 12, 60, 180, 500, 700, 900 },
                Land.ColorUrbinopoly.ROSA, 100);
        squares[15] = new Station("Adriabus 3m", 200, new int[] { 25, 50, 100, 200 });
        squares[16] = new Land("Parco della Rimembranza", 180, new int[] { 14, 70, 200, 550, 750, 950 },
                Land.ColorUrbinopoly.ARANCIONE, 100);
        squares[17] = new Cards.Card.Probability();
        squares[18] = new Land("Parco Belvedere", 180, new int[] { 14, 70, 200, 550, 750, 950 },
                Land.ColorUrbinopoly.ARANCIONE, 100);
        squares[19] = new Land("Parco della Resistenza", 200, new int[] { 16, 80, 220, 600, 800, 1000 },
                Land.ColorUrbinopoly.ARANCIONE, 100);
        squares[PARKING] = new Square.Parking();
        squares[21] = new Land("Via del Duca", 220, new int[] { 18, 90, 250, 700, 875, 1050 },
                Land.ColorUrbinopoly.ROSSO, 150);
        squares[22] = new Cards.Card.Unexpected();
        squares[23] = new Land("Via Carlo Bo", 220, new int[] { 18, 90, 250, 700, 875, 1050 },
                Land.ColorUrbinopoly.ROSSO, 150);
        squares[24] = new Land("Via Raffaello", 240, new int[] { 20, 100, 300, 750, 925, 1100 },
                Land.ColorUrbinopoly.ROSSO, 150);
        squares[25] = new Station("Adriabus 1", 200, new int[] { 25, 50, 100, 200 });
        squares[26] = new Land("Via Olivetti", 260, new int[] { 22, 110, 330, 800, 975, 1150 },
                Land.ColorUrbinopoly.GIALLO, 150);
        squares[27] = new Land("Via Von Neumann", 260, new int[] { 22, 110, 330, 800, 975, 1150 },
                Land.ColorUrbinopoly.GIALLO, 150);
        squares[28] = new Service("Fontane", 150, new int[] { 4, 10 });
        squares[29] = new Land("Via Turing", 280, new int[] { 22, 120, 360, 850, 1025, 1200 },
                Land.ColorUrbinopoly.GIALLO, 150);
        squares[GO_TO_PRISON] = new Square.GoToPrison();
        squares[31] = new Land("Via dell'Aquilone", 300, new int[] { 26, 130, 390, 900, 1100, 1275 },
                Land.ColorUrbinopoly.VERDE, 200);
        squares[32] = new Land("Via Accipizza", 300, new int[] { 26, 130, 390, 900, 1100, 1275 },
                Land.ColorUrbinopoly.VERDE, 200);
        squares[33] = new Cards.Card.Probability();
        squares[34] = new Land("Via del Ghiottone", 320, new int[] { 28, 150, 450, 1000, 1200, 1400 },
                Land.ColorUrbinopoly.VERDE, 200);
        squares[35] = new Station("Adriabus 3d", 200, new int[] { 25, 50, 100, 200 });
        squares[36] = new Cards.Card.Unexpected();
        squares[37] = new Land("Fortezza Albornoz", 350, new int[] { 35, 175, 500, 1100, 1300, 1500 },
                Land.ColorUrbinopoly.BLU, 200);
        squares[38] = new Taxes<Integer>("Luxury Tax", 200);
        squares[39] = new Land("Piazza della Repubblica", 400, new int[] { 50, 200, 600, 1400, 1700, 2000 },
                Land.ColorUrbinopoly.BLU, 200);

        // creazione dei mazzi Probabilità e Imprevisti come parte del tabellone
        this.prob = new ProbabilityDeck();
        this.unex = new UnexpectedDeck();
    }

    // getter
    public ProbabilityDeck getProb() {
        return prob;
    }

    public UnexpectedDeck getUnex() {
        return unex;
    }

    // quadrato di partenza
    public Square go() {
        return squares[GO];
    }

    // manipolazione del quadrato corrente
    public Square getSquare(int position) {
        return squares[position];
    }

    /*
     * Il tabellone richiede una modellazione accurata di tutte
     * quelle fasi in cui l'azione verificata è automatica ed
     * unidirezionale, pertanto tale classe tutela tutte le possibilità
     */
}
