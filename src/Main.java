import jdk.swing.interop.SwingInterOpUtils;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {

        //JsonParser.testParsing(all_rooms, all_connections);

        System.out.println("Beans");
        System.out.println("beans");

        Map m = new Map();

        Connection[][] a = m.makeList();

        for (int i = 0; i < a.length; i++) {
            System.out.println("Room number " + i + " has connections: ");
            for (int j = 0; j < a[i].length; j++) {
                //System.out.println(a[i].length);
                System.out.println(a[i][j].getConnection_id());
            }
        }

    }
}
