import elevator.Direction;
import elevator.Elevator;
import elevator.ElevatorSystem;
import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Queue;

public class TestElevatorSystem {

    @Test
    public void testPickupOneElevatorSimple() {
        ElevatorSystem system = new ElevatorSystem(1);
        //system.pickup(3, Direction.UP);
        system.pickup(3);
        system.step();
        system.step();

        Queue<Integer> floorsUp = new PriorityQueue<>();
        floorsUp.add(3);
        Elevator expectedElevator = new Elevator(1);
        expectedElevator.setCurrentFloor(2);
        expectedElevator.setFloorsUp(floorsUp);
        expectedElevator.setDirection(Direction.UP);
        expectedElevator.setFloorsDown(new PriorityQueue<>());

        Assert.assertEquals(expectedElevator, system.get(0));
    }

    @Test
    public void testOneElevatorComplex() {
        ElevatorSystem system = new ElevatorSystem(1);
        //system.pickup(3, Direction.UP);
        system.pickup(3);
        system.step();
        system.step();

        Queue<Integer> floorsUp = new PriorityQueue<>();
        floorsUp.add(3);
        Elevator expectedElevator = new Elevator(1);
        expectedElevator.setCurrentFloor(2);
        expectedElevator.setFloorsUp(floorsUp);
        expectedElevator.setDirection(Direction.UP);
        expectedElevator.setFloorsDown(new PriorityQueue<>());

        Assert.assertEquals(expectedElevator, system.get(0));

        /*system.pickup(7, Direction.UP);
        system.pickup(5, Direction.DOWN);*/
        system.pickup(7);
        system.pickup(5);

        system.step();

        floorsUp.clear();
        floorsUp.add(7);
        floorsUp.add(5);

        expectedElevator.setCurrentFloor(3);
        expectedElevator.setFloorsUp(floorsUp);
        expectedElevator.setDirection(Direction.UP);
        expectedElevator.setFloorsDown(new PriorityQueue<>());
    }

    @Test
    public void testBiggerSystem() {
        ElevatorSystem system = new ElevatorSystem(5);

        system.pickup(3);
        system.pickup(2);
        system.step();

        Elevator elevator1 = new Elevator(1);
        elevator1.setCurrentFloor(1);
        elevator1.setDirection(Direction.UP);
        Queue<Integer> floorsUp1 = new PriorityQueue<>();
        floorsUp1.add(2);
        floorsUp1.add(3);
        elevator1.setFloorsUp(floorsUp1);
        elevator1.setFloorsDown(new PriorityQueue<>());

        Assert. assertEquals(elevator1, system.get(0));

        system.step();

        system.pickup(7);
        system.pickup(6);

        for (int i = 3; i < 5; i++) system.step();

        floorsUp1.clear();
        floorsUp1.add(6);
        floorsUp1.add(7);
        elevator1.setCurrentFloor(4);
        elevator1.setFloorsUp(floorsUp1);

        Assert.assertEquals(elevator1, system.get(0));

        system.pickup(2);

        Elevator elevator2 = new Elevator(2);
        elevator2.setCurrentFloor(0);
        elevator2.setDirection(Direction.UP);
        Queue<Integer> floorsUp2 = new PriorityQueue<>();
        floorsUp2.add(2);
        elevator2.setFloorsUp(floorsUp2);
        elevator2.setFloorsDown(new PriorityQueue<>());

        Assert.assertEquals(elevator2, system.get(1));

        system.step();

        system.update(2, 4);
        elevator2.getFloorsUp().add(4);
        elevator2.setCurrentFloor(1);
        Assert.assertEquals(elevator2, system.get(1));
    }

}
