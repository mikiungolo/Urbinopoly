// mdoellazione di una Proprietà Terreno
public class Land extends Property {

    // definizione dei diversi Urbinopoly
    public enum ColorUrbinopoly {
        MARRONE(2),
        CELESTE(3),
        ROSA(3),
        ARANCIONE(3),
        ROSSO(3),
        GIALLO(3),
        VERDE(3),
        BLU(2);

        // numero terreni dello stesso colore
        private final int nLandsColor;

        // costruttore
        private ColorUrbinopoly(int nLandsColor) {
            this.nLandsColor = nLandsColor;
        }

        public int getnLandsColor() {
            return nLandsColor;
        }
    };

    // costante bonus dovuta al monopolio
    private static final int BONUS_URBINOPOLY = 0;

    // stato di un Terreno
    private final ColorUrbinopoly color;

    private final int priceHouse;
    private int nHouse;

    private boolean urbinopoly;

    // costruttore
    public Land(String name, int price, int[] gain, Land.ColorUrbinopoly color,
            int priceHouse) {
        super(name, price, gain, TypeProperty.LAND);
        this.color = color;
        this.priceHouse = priceHouse;
        this.nHouse = 0;
        this.urbinopoly = false;
    }

    // getter and setter
    public ColorUrbinopoly getColor() {
        return color;
    }

    public int getPriceHouse() {
        return priceHouse;
    }

    public int getnHouse() {
        return nHouse;
    }

    public void setnHouse(int nHouse) {
        this.nHouse = nHouse;
    }

    public boolean isUrbinopoly() {
        return this.urbinopoly;
    }

    public static int getBonusUrbinopoly() {
        return BONUS_URBINOPOLY;
    }

    public void setUrbinopoly(boolean urbinopoly) {
        this.urbinopoly = urbinopoly;
    }

    // costruzione di una casa
    public int buildHouse() {
        if (urbinopoly) {
            this.nHouse++;
            return this.priceHouse;
        } else
            return 0;
    }

    // rimozione di una casa
    public int removeHouse() {
        if (this.nHouse > 0) {
            this.nHouse--;
            return (this.priceHouse / 2);
        } else
            return 0;
    }

    // ritorna affitto
    @Override
    public int getRent(int qnt) {
        if (nHouse == 0 && (!urbinopoly)) {
            return super.getGain()[nHouse];
        } else if (nHouse == 0 && urbinopoly) {
            return super.getGain()[nHouse] * getBonusUrbinopoly();
        } else {
            return super.getGain()[nHouse];
        }
    }
}