import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The snapshot of the current parking lot/ the puzzle board, which is also the state of our search space.
 */
public class Board {
    static int doorColumn; //door row = 0
    static List<Vehicle> vehicles; //vehicles on this board, index of the vehicle corresponds to its id

    int[][] board;
    int cost; //f(n) = g(n)+h(n)
    Board parent; //the state/board from which this board is reached
    AIAction actionTaken; //how I was born



    public Board(int[][] boardData) {
        //todo
    }

    public static int getDoorColumn() { return doorColumn; }

    // returns the vehicle with id
    public static Vehicle getVehicle(int id) { return vehicles.get(id); }

    public int[][] getBoard() {
        return board;
    }

    public boolean isGridOccupied(int r, int c) { return !(this.board[r][c] == -1); }

    public void printBoard() {
        final int GRIDLENGTH = 4;
        int n = this.board.length;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < (n + 1) * GRIDLENGTH + 2; c++) {
                System.out.print('-');
            }
            System.out.println();
            for (int i = 0; i < n; i++) {
                String gridFill = "";
                if (this.board[r][i] != -1) {
                    gridFill = " " + this.board[r][i];
                }
                while (gridFill.length() < GRIDLENGTH) {
                    gridFill += ' ';
                }
                System.out.print('|' + gridFill);
            }
            System.out.println('|');
        }
        for (int c = 0; c < (n + 1) * GRIDLENGTH + 2; c++) {
            System.out.print('-');
        }
        System.out.println();
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
        return new ArrayList<>();
    }

    // override the hashCode by the deepHash for board, such that
    // two Board objects w/ same board field has the same hashCode
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(this.getBoard());
    }

    // override the equals method such that two Board objects w/ same board
    // field are treated as the same object
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Board) {
          Board bd = (Board)obj;
          return (Arrays.deepEquals(bd.getBoard(), this.getBoard()));
        } 
        return false;
    }

}
