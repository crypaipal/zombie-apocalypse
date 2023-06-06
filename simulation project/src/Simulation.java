import java.util.Scanner;

public class Simulation {
    public static Field field = null;
    public static int personCount;
    public static int protectedPersonCount;
    public static int zombieCount;
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int width = console.nextInt();
        int height = console.nextInt();
        field = new Field(width, height);   //wprowadzenie rozmiarow mapy
        personCount = console.nextInt();    //wprowadzenie liczby agentow klasy Person
        protectedPersonCount = console.nextInt();   //wprowadzenie liczby agentow klasy protectedPerson
        zombieCount = console.nextInt();    //wprowadzenie liczby agentow klasy Zombie
        Interaction interaction = new Interaction();
        interaction.simulate();
    }
}
