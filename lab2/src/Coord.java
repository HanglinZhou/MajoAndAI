public class Coord {
    int row;
    int col;

    public Coord(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    /* returns whether the coord is out of range of the board)*/
    public boolean isOutOfRange() {
        final int range = 8;
        return (this.row < 0 || this.row > range || this.col < 0 || this.col > range);
    }

    @Override
    public String toString() {
        return "Coord{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        // check if o is an instance of Coord or not
        if (!(o instanceof Coord)) {
            return false;
        }

        Coord c = (Coord) o;

        return (this.row == c.getRow()) && (this.col == c.getCol());
    }
}
