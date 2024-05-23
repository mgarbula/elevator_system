import elevator.CompareQueues;
import elevator.Direction;
import elevator.Elevator;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class TestElevator {

    @Test
    public void testConstructor() {
        Elevator elevator = new Elevator(3);
        Assert.assertEquals(3, elevator.getElID());
        Assert.assertEquals(0, elevator.getCurrentFloor());
        Assert.assertEquals(Direction.STOP, elevator.getDirection());
    }

    @Test
    public void testAddFloorsUp() {
        Elevator elevator = new Elevator(3);
        elevator.addStop(2);
        elevator.addStop(5);
        elevator.addStop(6);

        Queue<Integer> expectedFloors = new PriorityQueue<>();
        expectedFloors.add(2);
        expectedFloors.add(5);
        expectedFloors.add(6);
        Assert.assertTrue(CompareQueues.compareQueues(expectedFloors, elevator.getFloorsUp()));
        Assert.assertEquals(Direction.UP, elevator.getDirection());
    }

    @Test
    public void testAddFloorsDown() {
        Elevator elevator = new Elevator(3);
        elevator.setCurrentFloor(6);
        elevator.addStop(2);
        elevator.addStop(5);
        elevator.addStop(0);

        Queue<Integer> expectedFloors = new PriorityQueue<>(Comparator.reverseOrder());
        expectedFloors.add(2);
        expectedFloors.add(5);
        expectedFloors.add(0);
        Assert.assertTrue(CompareQueues.compareQueues(expectedFloors, elevator.getFloorsDown()));
        Assert.assertEquals(Direction.DOWN, elevator.getDirection());
    }

    @Test
    public void testAddFloorsDifferentDirections() {
        Elevator elevator = new Elevator(3);
        elevator.setCurrentFloor(4);
        elevator.addStop(3);
        elevator.addStop(7);
        elevator.addStop(1);

        Queue<Integer> expectedDownFloors = new PriorityQueue<>(Comparator.reverseOrder());
        expectedDownFloors.add(3);
        expectedDownFloors.add(1);

        Queue<Integer> expectedUpFloors = new PriorityQueue<>();
        expectedUpFloors.add(7);

        Assert.assertTrue(CompareQueues.compareQueues(expectedDownFloors, elevator.getFloorsDown()));
        Assert.assertTrue(CompareQueues.compareQueues(expectedUpFloors, elevator.getFloorsUp()));
        Assert.assertEquals(Direction.DOWN, elevator.getDirection());
    }

    @Test
    public void testStepFloorUp() {
        Elevator elevator = new Elevator(3);
        elevator.setCurrentFloor(4);
        elevator.addStop(7);

        Queue<Integer> expectedFloors = new PriorityQueue<>();
        expectedFloors.add(7);

        elevator.step();
        Assert.assertTrue(CompareQueues.compareQueues(expectedFloors, elevator.getFloorsUp()));
        Assert.assertEquals(5, elevator.getCurrentFloor());
        Assert.assertEquals(Direction.UP, elevator.getDirection());
    }

    @Test
    public void testStepFloorDown() {
        Elevator elevator = new Elevator(3);
        elevator.setCurrentFloor(4);
        elevator.addStop(3);

        Queue<Integer> expectedFloors = new PriorityQueue<>(Comparator.reverseOrder());

        elevator.step();
        Assert.assertTrue(CompareQueues.compareQueues(expectedFloors, elevator.getFloorsDown()));
        Assert.assertEquals(3, elevator.getCurrentFloor());
        Assert.assertEquals(Direction.STOP, elevator.getDirection());
    }

    @Test
    public void testStepFloorDifferentDirections() {
        Elevator elevator = new Elevator(3);
        elevator.setCurrentFloor(4);
        elevator.addStop(3);
        elevator.addStop(8);
        elevator.addStop(2);
        elevator.addStop(6);
        elevator.addStop(9);

        Queue<Integer> expectedUp = new PriorityQueue<>();
        expectedUp.add(8);
        expectedUp.add(6);
        expectedUp.add(9);

        Queue<Integer> expectedDown = new PriorityQueue<>(Comparator.reverseOrder());
        expectedDown.add(3);
        expectedDown.add(2);

        ArrayList<Integer> floorsWhenPoll = new ArrayList<>();
        floorsWhenPoll.add(3);
        floorsWhenPoll.add(8);
        floorsWhenPoll.add(2);
        floorsWhenPoll.add(6);
        floorsWhenPoll.add(9);

        int floor = 4;
        for (int i = floor; i > 2; i--) {
            Assert.assertTrue(CompareQueues.compareQueues(expectedUp, elevator.getFloorsUp()));
            Assert.assertTrue(CompareQueues.compareQueues(expectedDown, elevator.getFloorsDown()));
            Assert.assertEquals(Direction.DOWN, elevator.getDirection());
            Assert.assertEquals(i, elevator.getCurrentFloor());

            elevator.step();

            if (floorsWhenPoll.contains(i - 1)) {
                expectedDown.poll();
                floorsWhenPoll.remove((Integer) (i - 1));
            }
        }

        floor = 2;
        for (int i = floor; i < 9; i++) {
            Assert.assertTrue(CompareQueues.compareQueues(expectedUp, elevator.getFloorsUp()));
            Assert.assertTrue(CompareQueues.compareQueues(expectedDown, elevator.getFloorsDown()));
            Assert.assertEquals(Direction.UP, elevator.getDirection());
            Assert.assertEquals(i, elevator.getCurrentFloor());

            elevator.step();

            if (floorsWhenPoll.contains(i + 1)) {
                expectedUp.poll();
            }
        }

        Assert.assertTrue(CompareQueues.compareQueues(expectedUp, elevator.getFloorsUp()));
        Assert.assertTrue(CompareQueues.compareQueues(expectedDown, elevator.getFloorsDown()));
        Assert.assertEquals(Direction.STOP, elevator.getDirection());
        Assert.assertEquals(9, elevator.getCurrentFloor());
    }

}
