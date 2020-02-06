import java.util.List;

public class Vehicle {
    enum Direction {
        LEFT, RIGHT, UP, DOWN;
    }

    int id;
    /*  (row, column)
        the coord for a horizontal car (has moving direction of left and right) is the (r,c) of its leftmost grid;
        the coord of a vertical car (has moving direction of up and down) is the (r,c) of its top grid.
     */
    int[] coord;
    int length;
    List<Direction> validDirections;

    public Vehicle(int id, int[] coord, int length, List<Direction> validDirections) {
        this.id = id;
        this.coord = coord;
        this.length = length;
        this.validDirections = validDirections;
    }

    public int getId() {
        return id;
    }

    public int[] getCoord() {
        return coord;
    }

    public int getLength() {
        return length;
    }

    public List<Direction> getValidDirections() {
        return validDirections;
    }

    public void setCoord(int[] coord) {
        this.coord = coord;
    }

    // returns true if the current vehicle moves vertically
    public boolean movesVertically() {
        return (this.getValidDirections().get(0) == Direction.UP ||
                this.getValidDirections().get(0) == Direction.DOWN);

    }

    public void move(Direction dir) {
        switch (dir) {
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            default:
                break;
        }
    }
    private void moveLeft() {
        coord[1]--;
    }

    private void moveRight() {
        coord[1]++;
    }

    private void moveUp() {
        coord[0]--;
    }

    private void moveDown() {
        coord[0]++;
    }


}
