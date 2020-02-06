import java.util.List;

/**
 * The snapshot of the current parking lot/ the puzzle board, which is also the state of our search space.
 */
public class Board {


    int[][] board;
    List<Vehicle> vehicles; //vehicles on this board, index of the vehicle corresponds to its id
    int cost; //f(n) = g(n)+h(n)
    Board parent; //the state/board from which this board is reached
    AIAction actionTaken; //how I was born

    public Board(int[][] boardData) {
        //todo
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Board getParent() {
        return parent;
    }

    public void setParent(Board parent) {
        this.parent = parent;
    }

    public AIAction getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(AIAction actionTaken) {
        this.actionTaken = actionTaken;
    }

    /**
     * In the order of indices of "vehicles", compute all valid actions for one vehicle.
     * @return All valid actions for a given board.
     */
    public List<AIAction> computeValidActions() {
        //todo
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
