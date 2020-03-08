import jdk.swing.interop.SwingInterOpUtils;

import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Beans");
        System.out.println("beans");

        Map m = new Map();
        int[] soultion = m.dijkstra();

        System.out.println(Arrays.toString(soultion));

        //Connection[] connections = m.getAll_connections();

        //test t = new test();

    }
}
