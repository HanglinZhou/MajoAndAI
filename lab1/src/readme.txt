compile: javac TrafficJamPuzzleSolver.java
run:
java TrafficJamPuzzleSolver
[path/input.txt] (we what used is input/input1.txt

file.txt format: "x"being a blank grid, # represent a number, which is the unique id assigned to each car.
     * an individual car is separated from other cars or blanks by a space
     * |n for  a n*n board/parking lot              |
     * |which row the door is on (index starts at 0)|
     * |   x x x x # # #                            |
     * |   ...                                      |
     * |   ...                                      |
     * |   x x x x # # #                            |
