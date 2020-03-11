import jdk.swing.interop.SwingInterOpUtils;

import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Beans");
        Map m = new Map();

        m.setStartEnd();

        System.out.println("All parsing finished");
        //System.out.println("Starting timer...");
        long startTime = System.nanoTime();
        m.dijkstra();
        long endTime = System.nanoTime();
        //System.out.println("End Timer");

        System.out.println("Room: " + m.end.getRoom_id());

        //long duration = (endTime - startTime)/10000;
        //System.out.println("Time taken:"+ duration);



    }
}
