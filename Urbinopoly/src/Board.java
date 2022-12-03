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
        board[1] = new Land(null, 0, null, null, 0);
        board[2] = new Cards.Card(null, null, null);
        board[3] = new Land(null, 0, null, null, 0);
        board[4];
        board[5] = new Station(null, 0, null);
        board[6] = new Land(null, 0, null, null, 0);
        board[7] = new Cards.Card(null, null, null);
        board[8] = new Land(null, 0, null, null, 0);
        board[9] = new Land(null, 0, null, null, 0);
        board[PRISON] = new Square.Prison();
        board[11] = new Land(null, 0, null, null, 0);
        board[12] = new Service(null, 0, null);
        board[13] = new Land(null, 0, null, null, 0);
        board[14] = new Land(null, 0, null, null, 0);
        board[15] = new Station(null, 0, null);
        board[16] = new Land(null, 0, null, null, 0);
        board[17] = new Cards.Card(null, null, null);
        board[18] = new Land(null, 0, null, null, 0);
        board[19] = new Land(null, 0, null, null, 0);
        board[PARKING] = new Square.Parking();
        board[21] = new Land(null, 0, null, null, 0);
        board[22] = new Cards.Card(null, null, null);
        board[23] = new Land(null, 0, null, null, 0);
        board[24] = new Land(null, 0, null, null, 0);
        board[25] = new Station(null, 0, null);
        board[26] = new Land(null, 0, null, null, 0);
        board[27] = new Land(null, 0, null, null, 0);
        board[28] = new Service(null, 0, null);
        board[29] = new Land(null, 0, null, null, 0);
        board[GO_TO_PRISON] = new Square.GoToPrison();
        board[31] = new Land(null, 0, null, null, 0);
        board[32] = new Land(null, 0, null, null, 0);
        board[33] = new Cards.Card(null, null, null);
        board[34] = new Land(null, 0, null, null, 0);
        board[35] = new Station(null, 0, null);
        board[36] = new Cards.Card(null, null, null);
        board[37] = new Land(null, 0, null, null, 0);
        board[38];
        board[39] = new Land(null, 0, null, null, 0);
    }
}
