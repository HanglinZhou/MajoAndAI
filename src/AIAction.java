public class AIAction {

    private Vehicle vehicle;
    private Vehicle.Direction direction;

    public AIAction(Vehicle vehicle, Vehicle.Direction direction) {
        this.vehicle = vehicle;
        this.direction = direction;
    }

    public void printAIAction() {
        String result = "|\n|\n|\n" +
                "|  (" + vehicle.getId() + ", " + vehicle.getValidDirections() + ")" +
                "|\n|\n|\n|/";
        System.out.println(result);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Vehicle.Direction getDirection() {
        return direction;
    }
}
