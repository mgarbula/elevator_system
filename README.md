#Elevator System Simulation

This project aim was to create a simulation for elevator system.

There are two important classes in this project - `Elevator` and `ElevatorSystem`.

`Elevator` class has couple of fields, but the most important are two `PriorityQueue`s: `floorsUp` and `floorsDown`.
They hold floors numbers which have to be visited. Field `direction` says in which direction elevator travels.
`Elevator` can be in three states `UP`, `DOWN` and `STOP`. When `direction` is `UP` elements from `floorsUp` queue are taken. When `direction` is `DOWN` `floorsDown` is used.
When `direction` is `STOP` elevator stops and `currentFloor` and `nextFloor` are equal.

When elevator travels it can't change direction until one of the queues is empty, e.g. 
when `direction` is `UP` and `floorsUp` are `5,6,8` and `floorsDown` are `3, 1` elevator will go to the 5th, 6th and 8th floor
and then to the 3rd floor.

`ElevatorSystem` has following methods:

- `pickup(int currentFloor)` - it calls nearest elevator that travels to your direction or has state `STOP` (e.g. elevator is on floor 3 and has status `UP` and you are call is from floor 4)

- `update(int elID, int destinationFloor)` - it adds floor to one of the queue (depending on state). `update` can be 
  executed on any `Elevator`, it doesn't matter if there is someone inside
  
- `step()` - makes step of the simulation. If elevator is moving it adds or substracts one floor, depending on state

- `ArrayList<Triplet<Integer, Integer, Integer>> status()` - returns status of each `Elevator` in the system. status is 
  (elevator ID, current floor, next destination floor (first floor from the queue in direction its moving))
  
# Run

To run program execute

`mvn compile exec:java -Dexec.mainClass="Main" -Dexec.args="$NUMBER"` where NUMBER is number of `Elevator`s in the system.

# Test

To run all tests execute

`mvn clean test`