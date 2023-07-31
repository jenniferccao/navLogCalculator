import java.util.Scanner;

public class Trip { // singly linked list that contains legs
    private int totalDist;
    private double totalTime;
    private double totalFuel;
    public Node head;

    public Trip() {
        totalDist = 0;
        totalTime = 0;
        totalFuel = 0;
    }

    public void addTotals() {
        Node curr = head;
        while (curr != null) {
            totalDist = totalDist + curr.getData().getDist();
            totalTime = totalTime + curr.getData().getEte();
            totalFuel = totalFuel + curr.getData().getFuelReq();

            curr = curr.getNext();
        }

        System.out.println("Total Distance: " + totalDist);
        System.out.println("Total Time: " + totalTime);
        System.out.println("Total Fuel: " + totalFuel);
    }

    public void addLeg() {
        Scanner sc = new Scanner(System.in);

        // get trip information from user

        System.out.println("Indicated altitude: ");
        int iAlt = sc.nextInt();

        System.out.println("Temperature: ");
        int temp = sc.nextInt();

        System.out.println("True airspeed: ");
        int tas = sc.nextInt();

        System.out.println("Track: ");
        int track = sc.nextInt();

        System.out.println("Distance: ");
        int dist = sc.nextInt();

        System.out.println("Wind direction: ");
        int windDir = sc.nextInt();

        System.out.println("Wind speed: ");
        int windVel = sc.nextInt();

        System.out.println("Altimeter setting: ");
        double altSet = sc.nextDouble();

        System.out.println("Magnetic variation: ");
        int var = sc.nextInt();

        System.out.println("Fuel per hour: ");
        double fuelPH = sc.nextDouble();

        Node newLeg = new Node(new Leg(windDir, windVel, temp, iAlt, altSet, track, tas, var, fuelPH, dist)); // make new node containing data from new leg

        System.out.println(newLeg.getData());

        if (head == null) {
            head = newLeg;
        } else {
            // traverse list until end
            Node curr = head;
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            // append new leg to end of list
            curr.setNext(newLeg);
        }
    }


    public static void main(String[] args) {
        boolean done = false;
        Trip newTrip = new Trip();

        String ans;
        Scanner sc = new Scanner(System.in);


        while (!done) {
            System.out.println("Type A to add leg, X to finish");
            ans = sc.nextLine();

            if (ans.equalsIgnoreCase("A")) {
                newTrip.addLeg();
            } else if (ans.equalsIgnoreCase("X")) {
                newTrip.addTotals();
                done = true;
            }

        }
    }


}
