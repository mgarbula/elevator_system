package elevator;

import org.javatuples.Triplet;

import java.util.ArrayList;

public class ElevatorSystem extends ArrayList<Elevator> {

    public ElevatorSystem(int elevatorsNumber) {
        for (int i = 1; i <= elevatorsNumber; i++) {
            this.add(new Elevator(i));
        }
    }

    public void pickup(int floor) {
        Elevator closestElevator = null;
        int minFloorDistance = Integer.MAX_VALUE;

        for (Elevator elevator : this) {
            int difference = elevator.getCurrentFloor() - floor;
            if (elevator.getDirection().equals(Direction.STOP) && Math.abs(difference) < minFloorDistance) {
                minFloorDistance = Math.abs(difference);
                closestElevator = elevator;
            } else if (elevator.getDirection().equals(Direction.UP) && difference < 0 && Math.abs(difference) < minFloorDistance) {
                minFloorDistance = Math.abs(difference);
                closestElevator = elevator;
            } else if (elevator.getDirection().equals(Direction.DOWN) && difference > 0 && difference < minFloorDistance) {
                minFloorDistance = difference;
                closestElevator = elevator;
            }
        }

        if (closestElevator != null) {
            closestElevator.addStop(floor);
        }
    }

    public void update(int elID, int destinationFloor) {
        this.get(elID - 1).addStop(destinationFloor);
    }

    public void step() {
        this.forEach(Elevator::step);
    }

    public ArrayList<Triplet<Integer, Integer, Integer>> status() {
        ArrayList<Triplet<Integer, Integer, Integer>> statusArray = new ArrayList<>();

        this.forEach((el) -> statusArray.add(el.status()));

        return statusArray;
    }

}
