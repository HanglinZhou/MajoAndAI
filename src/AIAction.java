public class AIAction {

    private Vehicle vehicle;
    private Vehicle.Direction direction;

    public AIAction(Vehicle vehicle, Vehicle.Direction direction) {
        this.vehicle = vehicle;
        this.direction = direction;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Vehicle.Direction getDirection() {
        return direction;
    }
}
