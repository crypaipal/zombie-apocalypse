import java.util.ArrayList;
import java.util.List;

public class ProtectedPerson extends Agent {
    public static List<ProtectedPerson> protectedPeople = new ArrayList<>();
    public ProtectedPerson(int x, int y) {
        super(x, y);
    }
    //metoda jest wykorzystywana dla utworzenia agentow klasy protectedPerson
    public static void create() {
        int[] array;
        for(int i = 0; i < Simulation.protectedPersonCount; i++) {
            array = initCoordinates();
            ProtectedPerson protectedPerson = new ProtectedPerson(array[0], array[1]);
            Interaction.agents.add(protectedPerson);
            protectedPeople.add(protectedPerson);
        }
    }

    //ta metoda wyswietla agenta klasy protectedPerson na konsoli litera "P"
    @Override
    public void draw() {
        for(int i = 0; i < field.getHeight(); i++) {
            for(int j = 0; j < field.getWidth(); j++) {
                if(x == j && y == i)
                    field.field[i][j] = "P";
            }
        }
    }

    //to jest metoda dla wyswietlenia pozycji agenta klasy protectedPerson
    @Override
    public String toString() {
        return "ProtectedPerson{" + "x = " + x + ", y = " + y + '}';
    }
}
