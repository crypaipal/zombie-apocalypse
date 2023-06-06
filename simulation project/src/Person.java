import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Person extends Agent {
    public static List<Person> people = new ArrayList<>();
    public Person(int x, int y) {
        super(x, y); // wywolanie konstruktora klasy potomnej
    }

    //metoda jest wykorzystywana dla utworzenia agentow klasy Person
    public static void create() {
        Random random = new Random();
        int x, y;
        for(int i = 0; i < Simulation.personCount; i++) {
            x = random.nextInt(field.getWidth());
            y = random.nextInt(field.getHeight());
            Person person = new Person(x, y);
            Interaction.agents.add(person);
            people.add(person);
        }
    }

    //ta metoda wyswietla agenta klasy Person na konsoli litera "A"
    @Override
    public void draw() {
        for(int i = 0; i < field.getHeight(); i++) {
            for(int j = 0; j < field.getWidth(); j++) {
                if(x == j && y == i)
                    field.field[i][j] = "A";
            }
        }
    }

    //to jest metoda dla wyswietlenia pozycji agenta klasy Person
    @Override
    public String toString() {
        return "Person{" + "x = " + x + ", y = " + y + '}';
    }
}
