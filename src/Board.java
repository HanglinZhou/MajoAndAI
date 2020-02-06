import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The snapshot of the current parking lot/ the puzzle board, which is also the state of our search space.
 */
public class Board {
    static int doorColumn; //door row = 0
    static List<Vehicle> vehicles; //vehicles on this board, index of the vehicle corresponds to its id
    final int blank = -1;

    int[][] board;
    int cost; //f(n) = g(n)+h(n)
    Board parent; //the state/board from which this board is reached
    AIAction actionTaken; //how I was born


    public Board(int[][] boardData) {
        board = new int[boardData.length - 1][boardData.length - 1];
        //initialize board
        for (int i = 0; i < boardData.length - 1; i++) {
            for (int j = 0; j < boardData[0].length - 1; j++)
                board[i][j] = boardData[i][j];
        }


        doorColumn = boardData[boardData.length-1][boardData.length-1];

        boolean sawRedCar = false;
        //iterate through the board, construct vehicles and store all of them
        /*
        procedure: if blank, continue, other cases:
        1) if I haven't seen redCar and i find a grid with index 0, add redCar
        2) else: a) before I see redCar, my list size would be either smaller than currId i am looking at or equal to
                    - curId > size, we haven't added this v: go down and see if I am a vertical v,
                        increment length and specify dir, vice versa
                    - curId == size, we have added this v, do nothing
                 b) after I saw redCar, my list size is increased by one, so I decrease it and repeat the above
         */
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (!isGridOccupied(row,col))
                    continue;
                if (sawRedCar || board[row][col] != 0) {
                    int curId = board[row][col];
                    int curNumVehicles = vehicles.size();
                    if (sawRedCar)  //my size  updated (red car has been added to the front)
                        curNumVehicles--;
                    //todo:test
                    if (curId > curNumVehicles) {
                        //want to construct a new v and add it to the list as this is the first time we see this vehicle
                        //don't need to look up or check my left, as we have added those
                        List<Vehicle.Direction> actions = new ArrayList<>();
                        int length = 1;
                        if (row < board.length - 1 && curId == board[row+1][col]) {
                            length++;
                            if (row < board.length - 2 && curId == board[row+2][col]) {
                                length++;
                            }
                            actions.add(Vehicle.Direction.DOWN);
                            actions.add((Vehicle.Direction.UP));
                        }

                        if (col < board[0].length - 1 && curId == board[row][col+1]) {
                            length++;
                            if (col < board[0].length - 2 && curId == board[row][col+2]) {
                                length++;
                            }
                            actions.add(Vehicle.Direction.LEFT);
                            actions.add((Vehicle.Direction.RIGHT));
                        }

                        Vehicle v = new Vehicle(curId, new int[]{row, col}, length, actions);
                        vehicles.add(v);
                    }


                } else {
                    //TODO: assume red car length always = 2
                    Vehicle redCar = new Vehicle(0, new int[]{row, col}, 2,
                            Arrays.asList(Vehicle.Direction.DOWN, Vehicle.Direction.UP));
                    vehicles.add(0, redCar);
                    sawRedCar = true;
                }

            }

        }

        cost = 0;
        parent = null;
        actionTaken = null;

    }

    public boolean isGridOccupied(int row, int col) {
        return board[row][col] != blank;
    }

    public Board(Board parent, AIAction actionTaken) {
        this.parent = parent;
        this.actionTaken = actionTaken;
        this.board = parent.getBoard().clone();

        //update board and move the vehicle
        Vehicle v = actionTaken.getVehicle();
        Vehicle.Direction dir = actionTaken.getDirection();
        v.move(dir);

        int[] coordV = v.getCoord();

        switch (dir) {
            case LEFT:
                board[coordV[0]][coordV[1]] = v.getId();
                board[coordV[0]][coordV[1]+v.getLength()] = blank;
                break;
            case RIGHT:
                board[coordV[0]][coordV[1]] = v.getId();
                board[coordV[0]][coordV[1]-v.getLength()] = blank;
                break;
            case UP:
                board[coordV[0]][coordV[1]] = v.getId();
                board[coordV[0]+v.getLength()][coordV[1]] = blank;
                break;
            case DOWN:
                board[coordV[0]][coordV[1]] = v.getId();
                board[coordV[0]-v.getLength()][coordV[1]] = blank;
                break;
            default:
                break;
        }


    }

    public static int getDoorColumn() { return doorColumn; }

    // returns the vehicle with id
    public static Vehicle getVehicle(int id) { return vehicles.get(id); }

    public int[][] getBoard() {
        return board;
    }

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


    public Vehicle getRedCar() {
        return vehicles.get(0);
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
