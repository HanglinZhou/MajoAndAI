public class TrafficJamPuzzleHeuristicOverlap implements TrafficJamPuzzleHeuristic {

    /*
    f(currBoard) = number of overlaps after moving all the vehicles blocking the red car
    from reaching the door for a minimum number of steps
    @Override
     */
    public int computeEstCost(Board board, int doorCoord, Vehicle redCar) {
        // if there exists a vehicle moving vertically in the same column with the red car,
        // the red car is never able to reach the door

        return 0;
    }

    private int num
}
