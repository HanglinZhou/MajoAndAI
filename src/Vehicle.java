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


}
