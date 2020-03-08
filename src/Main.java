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

        for (int i = 0; i < soultion.length; i++) {

            if(soultion[i] != 0){
                System.out.println("Room "+ i);
            }

        }

    }
}
