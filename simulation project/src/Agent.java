import java.util.*;

//klasa abstrakcyjna dla agentow
public abstract class Agent {
    protected static Field field = Simulation.field;
    protected int x;
    protected int y;

    public Agent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //to jest metoda dla przemieszczenia naszych agentow
    public void move() {
        if(this instanceof Person)
            movePerson();
        else
            moveNotPerson();
    }

    //metoda dla przemieszczenia agentow klasy Person
    private void movePerson() {
        int x0, y0;
        Random random = new Random();
        x0 = random.nextInt(3) - 1; // zakres od -1 do 1
        y0 = random.nextInt(3) - 1;

        if(x == 0 && x0 < 0) // kolo lewej sciany mapy
            x -= x0;
        else if(x == field.getWidth() - 1 && x0 > 0) // kolo prawej sciany mapy
            x -= x0;
        else
            x += x0;

        if(y == 0 && y0 < 0) // kolo gornej sciany mapy
            y -= y0;
        else if(y == field.getHeight() - 1 && y0 > 0)// kolo dolnej sciany mapy
            y -= y0;
        else
            y += y0;
    }

    //metoda dla przemieszczenia protectedPerson oraz Zombie
    private void moveNotPerson() {
        int x0, y0;
        Random random = new Random();
        x0 = random.nextInt(3) - 1; // zakres od -1 do 1
        y0 = random.nextInt(3) - 1;
        boolean isXTrigger = false;
        boolean isYTrigger = false;
        if(x == field.getWidth() - 1 - field.getXBunker() &&
                (y >= 0 && y < field.getYBunker()) && x == 0) {
            isXTrigger = true;

        } else if(x == field.getWidth() - 1 - field.getXBunker() &&
                (y >= 0 && y < field.getYBunker()) && x0 > 0) {
            x -= x0;
            isXTrigger = true;

        } else if(y == field.getYBunker() &&
                (x >= field.getWidth() - field.getXBunker() && x < field.getWidth()) &&
                y == field.getHeight() - 1) {
            isYTrigger = true;

        } else if(y == field.getYBunker() &&
                (x >= field.getWidth() - field.getXBunker() && x < field.getWidth()) && y0 < 0) {
            y -= y0;
            isYTrigger = true;

        } else if(x == field.getWidth() - 1 - field.getXBunker() &&
                y == field.getYBunker() && x == 0 && y0 < 0) {
            isXTrigger = true;

        } else if(x == field.getWidth() - 1 - field.getXBunker() &&
                y == field.getYBunker() && y == field.getHeight() - 1 && x0 > 0) {
            isYTrigger = true;

        } else if(x == field.getWidth() - 1 - field.getXBunker() &&
                y == field.getYBunker() && x0 > 0 && y0 < 0) {
            x -= x0;
            y -= y0;
            isXTrigger = true;
            isYTrigger = true;
        }

        if(!isXTrigger) {
            if (x == 0 && x0 < 0) // kolo prawej sciany
                x -= x0;
            else if (x == field.getWidth() - 1 && x0 > 0) // kolo lewej sciany
                x -= x0;
            else
                x += x0;
        }
        
        if(!isYTrigger) {
            if (y == 0 && y0 < 0) // kolo gornej sciany
                y -= y0;
            else if (y == field.getHeight() - 1 && y0 > 0)// kolo dolnej sciany
                y -= y0;
            else
                y += y0;
        }
    }

    //to jest metoda dla wyswietlenia agentow na konsoli
    public abstract void draw();

    //metoda dla inicjalizacji koordynat dla protectedPerson oraz Zombie
    protected static int[] initCoordinates() {
        int[] arr = new int[2];
        Random random = new Random();
        int x = random.nextInt(field.getWidth());
        int y = random.nextInt(field.getHeight());
        if(x >= field.getWidth() - field.getXBunker() && y < field.getYBunker()) {
            arr = Arrays.copyOf(initCoordinates(), 2);
        } else {
            arr[0] = x;
            arr[1] = y;
        }
        return arr;
    }
}
