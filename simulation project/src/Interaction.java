import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

//klasa, w ktorej trwa symulacja
public class Interaction {
    public static List<Agent> agents = new ArrayList<>();
    //metoda dla dzialania symulacji
    public void simulate() {
        boolean isNotFirst = false;
        Person.create();
        ProtectedPerson.create();
        Zombie.create();
        while(!isGameStopped()) {
            Simulation.field.draw();
            if(isNotFirst) {
                checkZombieAndPersonInteraction();
                checkPersonAndProtectedPersonInteraction();
                checkZombieAndProtectedPersonInteraction();
            }
            for(Agent agent : agents) {
                if(isNotFirst) {
                    agent.move();
                }
                System.out.println(agent);
                agent.draw();
            }
            isNotFirst = true;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Simulation.field.draw();
    }

    //metoda sprawdzenia czy gra sie konczy
    public boolean isGameStopped() {
        return (Person.people.isEmpty() && ProtectedPerson.protectedPeople.isEmpty()) || Zombie.zombies.isEmpty();
    }

    //metoda dla interakcji agentow z klasy Zombie oraz Person
    public void checkZombieAndPersonInteraction() {
        ListIterator<Zombie> zombieIterator = Zombie.zombies.listIterator();
        ListIterator<Person> personIterator = Person.people.listIterator();
        while (zombieIterator.hasNext()) {
            Zombie zombie = zombieIterator.next();
            while(personIterator.hasPrevious()) {
                personIterator.previous();
            }
            while (personIterator.hasNext()) {
                Person person = personIterator.next();
                if (zombie.x == person.x && zombie.y == person.y) {
                    Zombie current = new Zombie(person.x, person.y);
                    zombieIterator.add(current);
                    agents.add(current);
                    personIterator.remove();
                    agents.remove(person);
                }
            }
        }
    }

    //metoda dla interakcji agentow z klasy Zombie oraz protectedPerson
    public void checkZombieAndProtectedPersonInteraction() {
        ListIterator<Zombie> zombieIterator = Zombie.zombies.listIterator();
        ListIterator<ProtectedPerson> protectedPersonIterator = ProtectedPerson.protectedPeople.listIterator();
        while (zombieIterator.hasNext()) {
            Zombie zombie = zombieIterator.next();
            while(protectedPersonIterator.hasPrevious()) {
                protectedPersonIterator.previous();
            }
            while (protectedPersonIterator.hasNext()) {
                ProtectedPerson protectedPerson = protectedPersonIterator.next();
                if (zombie.x == protectedPerson.x && zombie.y == protectedPerson.y) {
                    Random random = new Random();
                    int value = random.nextInt(2);
                    if (value == 0) {
                        Zombie current = new Zombie(protectedPerson.x, protectedPerson.y);
                        zombieIterator.add(current);
                        zombieIterator.previous();
                        zombieIterator.next();
                        agents.add(current);
                        protectedPersonIterator.remove();
                        agents.remove(protectedPerson);
                    } else {
                        zombieIterator.remove();
                        agents.remove(zombie);
                        break;
                    }
                }
            }
        }
    }

    //metoda dla interakcji agentow z klasy Person oraz protectedPerson
    public void checkPersonAndProtectedPersonInteraction() {
        ListIterator<Person> personIterator = Person.people.listIterator();
        ListIterator<ProtectedPerson> protectedPersonIterator = ProtectedPerson.protectedPeople.listIterator();
        while(personIterator.hasNext()) {
            Person person = personIterator.next();
            while(protectedPersonIterator.hasPrevious()) {
                protectedPersonIterator.previous();
            }
            while(protectedPersonIterator.hasNext()) {
                ProtectedPerson protectedPerson = protectedPersonIterator.next();
                if(protectedPerson.x == person.x && protectedPerson.y == person.y) {
                    ProtectedPerson current = new ProtectedPerson(person.x, person.y);
                    protectedPersonIterator.add(current);
                    agents.add(current);
                    personIterator.remove();
                    agents.remove(person);
                    break;
                }
            }
        }
    }
}
