import jdk.swing.interop.SwingInterOpUtils;

import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {

        //System.out.println("Beans");
        //create an object of class Map.
        //This one contains is where the dijkstra method is coded and also where we find the adjacents of each room (the pre-processing of the data)
        Map m = new Map();

        //ask the user for the start and end room
        m.setStartEnd();

        System.out.println("Starting timer...");
        long startTime = System.nanoTime();
        m.dijkstra();
        long endTime = System.nanoTime();
        System.out.println("End Timer");
        System.out.println("Time taken to find path: " + (endTime - startTime)/10000000.0 + " seconds");

        //long duration = (endTime - startTime)/10000;
        //System.out.println("Time taken:"+ duration);

    }
}
