import java.util.ArrayList;
import java.util.List;

public class Zombie extends Agent {
    public static List<Zombie> zombies = new ArrayList<>();
    public Zombie(int x, int y) {
        super(x, y);
    }

    //metoda jest wykorzystywana dla utworzenia agentow klasy Zombie
    public static void create() {
        int[] array;
        for(int i = 0; i < Simulation.zombieCount; i++) {
            array = initCoordinates();
            Zombie zombie = new Zombie(array[0], array[1]);
            Interaction.agents.add(zombie);
            zombies.add(zombie);
        }
    }

    //ta metoda wyswietla agenta klasy Zombie na konsoli litera "X"
    @Override
    public void draw() {
        for(int i = 0; i < field.getHeight(); i++) {
            for(int j = 0; j < field.getWidth(); j++) {
                if(x == j && y == i)
                    field.field[i][j] = "X";
            }
        }
    }

    //to jest metoda dla wyswietlenia pozycji agenta klasy Zombie
    @Override
    public String toString() {
        return "Zombie{" + "x = " + x + ", y = " + y + '}';
    }
}
