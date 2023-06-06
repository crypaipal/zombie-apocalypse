//clasa dla utworzenia mapy, na ktorej bedzie sie dzialala simulacja
public class Field {
    public String[][] field;
    private final int width;
    private final int height;
    private int xBunker;
    private int yBunker;

    //konstruktor dla utworzenia mapy
    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        field = new String[height][width];
        createBunker();
    }

    //getter dlugosci mapy
    public int getWidth() {
        return width;
    }

    //getter wysokosci mapy
    public int getHeight() {
        return height;
    }

    //metoda dla rysowania i wyswietlenia mapy na ekranie
    public void draw() {
        for(int i = 0; i < height; i++) {
            System.out.print("\t");
            for(int j = 0; j < width; j++) {
                if(field[i][j] == null) {
                    if(i < yBunker && j >= width - xBunker) {
                        field[i][j] = "+";
                    } else {
                        field[i][j] = ".";
                    }
                }
                System.out.print(field[i][j] + " ");
                if(i < yBunker && j >= width - xBunker) {
                    field[i][j] = "+";
                } else {
                    field[i][j] = ".";
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    //getter koordynaty X Bunkera
    public int getXBunker() {
        return xBunker;
    }

    //getter koordynaty Y Bunkera
    public int getYBunker() {
        return yBunker;
    }

    //metoda dla tworzenia bunkera
    public void createBunker() {
        if((width < 5 && height < 3) || (width < 3 && height < 5)) {
            xBunker = 0;
            yBunker = 0;
        } else {
            xBunker = (int) Math.floor(width / 2.0);
            yBunker = (int) Math.ceil(height / 2.0);
            if(xBunker == 0)
                yBunker = 0;
        }
    }
}
