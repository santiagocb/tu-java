package co.com.randomexec.linearcalc;

public class LinearCalculus {

    /**
     * Sum the total distance driven by both cars and the initial distance.
     *
     * Example:
     * <pre>
     * V1 = 90 km/h; V2 = 110 km/h; S = 65 km; T = 3 h  =>  Distance = 665 km
     * V1 = 65.5 km/h; V2 = 90.4 km/h; S = 20.9 km; T = 1.5 h  =>  Distance = 254.75 km
     * V1 = 70 km/h; V2 = 85.6 km/h; S = 32.6 km; T = 2 h  =>  Distance = 343.8 km
     * </pre>
     *
     * @return the distance
     */
    public static double findCarsDistance(double car1Speed, double car2Speed, double initialDistance, double time) {
        double car1FinalDistance = car1Speed * time;
        double car2FinalDistance = car2Speed * time;
        double totalDistance = initialDistance + car1FinalDistance + car2FinalDistance;
        return totalDistance;
    }

    public static void main(String[] args) {
        System.out.println(findCarsDistance(90., 110., 65., 3.));
    }
}