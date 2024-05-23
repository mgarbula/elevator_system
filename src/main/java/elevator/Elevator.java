package elevator;

import org.javatuples.Triplet;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Elevator {

    private int elID;
    private int currentFloor;
    private Queue<Integer> floorsUp;
    private Queue<Integer> floorsDown;
    private String direction;

    public Elevator(int elId) {
        this.elID = elId;
        this.currentFloor = 0;
        this.floorsUp = new PriorityQueue<>();
        this.floorsDown = new PriorityQueue<>(Comparator.reverseOrder());
        this.direction = Direction.STOP;
    }

    public int getElID() {
        return elID;
    }

    public void setElID(int elID) {
        this.elID = elID;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Queue<Integer> getFloorsUp() {
        return floorsUp;
    }

    public void setFloorsUp(Queue<Integer> floorsUp) {
        this.floorsUp = floorsUp;
    }

    public Queue<Integer> getFloorsDown() {
        return floorsDown;
    }

    public void setFloorsDown(Queue<Integer> floorsDown) {
        this.floorsDown = floorsDown;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    private void addFloor(int floor, Queue<Integer> queue, String dir) {
        queue.add(floor);
        if (this.direction.equals(Direction.STOP)) {
            this.direction = dir;
        }
    }

    public void addStop(int floor) {
        if (floor > this.currentFloor) {
            addFloor(floor, this.floorsUp, Direction.UP);
        } else {
            addFloor(floor, this.floorsDown, Direction.DOWN);
        }
    }

    private void makeStep(Queue<Integer> first, Queue<Integer> second, int floor, String direction) {
        this.currentFloor += floor;
        if (this.currentFloor == first.element()) {
            first.poll();
        }
        if (first.isEmpty()) {
            if (!second.isEmpty()) {
                this.direction = direction;
            } else {
                this.direction = Direction.STOP;
            }
        }
    }

    public void step() {
        if (this.direction.equals(Direction.UP)) {
            makeStep(this.floorsUp, this.floorsDown, 1, Direction.DOWN);
        } else if (this.direction.equals(Direction.DOWN)) {
            makeStep(this.floorsDown, this.floorsUp, -1, Direction.UP);
        }
    }

    // returns ID, currentFloor and next floor
    public Triplet<Integer, Integer, Integer> status() {
        int nextFloor;
        if (this.direction.equals(Direction.STOP)) {
            nextFloor = this.currentFloor;
        } else {
            nextFloor = this.direction.equals(Direction.UP) ? this.floorsUp.element() : this.floorsDown.element();
        }
        return new Triplet<>(this.elID, this.currentFloor, nextFloor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Elevator)) return false;

        Elevator elevator = (Elevator) o;

        if (elID != elevator.elID) return false;
        if (currentFloor != elevator.currentFloor) return false;
        if (floorsUp != null && !CompareQueues.compareQueues(floorsUp, elevator.floorsUp)) return false;
        if (floorsDown != null && !CompareQueues.compareQueues(floorsDown, elevator.floorsDown)) return false;
        return direction != null ? direction.equals(elevator.direction) : elevator.direction == null;
    }

    @Override
    public int hashCode() {
        int result = elID;
        result = 31 * result + currentFloor;
        result = 31 * result + (floorsUp != null ? floorsUp.hashCode() : 0);
        result = 31 * result + (floorsDown != null ? floorsDown.hashCode() : 0);
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "elID=" + elID +
                ", currentFloor=" + currentFloor +
                ", floorsUp=" + floorsUp +
                ", floorsDown=" + floorsDown +
                ", direction='" + direction + '\'' +
                '}';
    }
}
