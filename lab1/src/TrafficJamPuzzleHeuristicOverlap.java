public class TrafficJamPuzzleHeuristicOverlap implements TrafficJamPuzzleHeuristic {
    Board board;

    /**
     *
     * @param board
     * @param redCar
     * @return number of overlaps after moving all the vehicles blocking the red car
     *        from reaching the door for a minimum number of steps
     * @Override
     */
    public int computeEstCost(Board board, Vehicle redCar) {
        int totalEstCost = 0;
        this.board = board;

        int rowOfRedCar = redCar.getCoord()[0]; // the row the red car is in

        for (int row = 0; row < rowOfRedCar; row++) {
            int id = this.board.getBoard()[row][Board.getDoorColumn()];
            if (id != -1) {  // if that grid is occupied
                Vehicle vehicle = this.board.getVehicle(id);
                // if there exists a vehicle moving vertically in the same column with the red car,
                // the red car is never able to reach the door
                if (vehicle.movesVertically())
                    return Integer.MAX_VALUE;

                // if obstacle vehicle moves horizontally
                int numGridsToMoveLeft = numOfGridsToMove(vehicle, Vehicle.Direction.LEFT);
                int numGridsToMoveRight = numOfGridsToMove(vehicle, Vehicle.Direction.RIGHT);

                int numCostCurrRow = Math.min(
                        estCostOfCurrMove(vehicle, Vehicle.Direction.LEFT, numGridsToMoveLeft),
                        estCostOfCurrMove(vehicle, Vehicle.Direction.RIGHT, numGridsToMoveRight));

                if (numCostCurrRow == Integer.MAX_VALUE)
                    return numCostCurrRow;

                totalEstCost += numCostCurrRow;
            }
        }

        return totalEstCost;
    }

    /**
     *
     * @param vehicle: vehicle to be moved
     * @param direction: direction to be moved towards
     * @param numGridsToMove: number of grids to move towards direction
     * @return the sum of the number of overlapping grids after moving the specified vehicle to the
     * specified direction and the number of moves to take; returns infinity if move is invalid
     */
    private int estCostOfCurrMove(Vehicle vehicle, Vehicle.Direction direction, int numGridsToMove) {
        if (numGridsToMove == Integer.MAX_VALUE)  // if invalid move
            return numGridsToMove;

        int result = numGridsToMove;
        int currCol = Board.getDoorColumn();
        int currRow = vehicle.getCoord()[0];

        int oneMove = 1;
        if (direction == Vehicle.Direction.LEFT) {
            oneMove = -1;
        }

        // go through the grids towards the direction to move one by one,
        // if there is a overlap at that grid, increment one to the result
        for (int i = 0; i < numGridsToMove; i++) {
            currCol += oneMove;
            if (board.isGridOccupied(currRow, currCol)) {
                result++;
            }
        }
        return result;
    }

    /**
     *
     * @param vehicle: vehicle to be moved
     * @param direction: direction to be moved towards
     * @return the min number of grids to be moved for the current vehicle to get
     *        out of the way in order for the red car to reach the door, to the specified direction
     */
    private int numOfGridsToMove(Vehicle vehicle, Vehicle.Direction direction) {
        int numGridsLeftOfDoorCol = Board.getDoorColumn() - vehicle.getCoord()[1];
        int numMoves;
        if (direction == Vehicle.Direction.LEFT) {
            numMoves = vehicle.getLength() - numGridsLeftOfDoorCol;
            if (vehicle.getCoord()[1] - numMoves < 0)
                return Integer.MAX_VALUE;
        } else {
            numMoves = numGridsLeftOfDoorCol + 1;
            if (vehicle.getCoord()[1] + vehicle.getLength() - 1 + numMoves >= board.getBoard().length)
                return Integer.MAX_VALUE;
        }
        return numMoves;
    }
}
