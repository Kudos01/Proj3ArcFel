import jdk.swing.interop.SwingInterOpUtils;

import java.util.Arrays;
import java.util.HashMap;

public class Main {
    //

    public static void main(String[] args) throws Exception {


        //609 onwards does not work if not directly connected to 0
        //some random ones don't work with the S dataset -- TESTED NUMBER - 42 AND 127
        //simplified the isEndVisited


        //I was thinking maybe that the problem was end wasn't being marked properly, but it doesn't really make sense
        //because 42 to 127 doesn't work, but 127 to 42 works so...
        //have somewhere to go still doesn't fix things

        Map m = new Map();

        m.setStartEnd();

        System.out.println("All parsing finished");
        //System.out.println("Starting timer...");
        //long startTime = System.nanoTime();
        m.dijkstra();
        //long endTime = System.nanoTime();
        //System.out.println("End Timer");

        //long duration = (endTime - startTime)/10000;
        //System.out.println("Time taken:"+ duration);

    }
}
