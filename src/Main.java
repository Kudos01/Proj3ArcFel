import jdk.swing.interop.SwingInterOpUtils;

import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Beans");

        Map m = new Map();

        /*
        for (int i = 0; i < m.all_rooms.length; i++) {
            System.out.println("CONNECTED TO ROOM WITH ID: " + i);
            for (int j = 0; j < m.all_rooms[i].getAttachedTo().length; j++) {
                //System.out.println(m.all_rooms[i].getAttachedTo()[j]);
                for (int k = 0; k < m.all_rooms[i].getAttachedTo()[j].getRooms().length; k++) {
                    System.out.println("ROOM ID: " + m.all_rooms[i].getAttachedTo()[j].getRooms()[k].getRoom_id());
                    //System.out.println(m.all_rooms[i].getAttachedTo()[j].getRooms().length);
                    //System.out.println("ENEMY PROBABILITY: " + m.all_rooms[i].getAttachedTo()[j].getEnemy_probability());
                }
            }
        }

        */

        /*
        for (int i = 0; i < m.all_rooms[3].getAttachedTo().length; i++) {
            System.out.println("Probability: " + m.all_rooms[3].getAttachedTo()[i].getEnemy_probability());
            for (int j = 0; j < m.all_rooms[3].getAttachedTo()[i].getRooms().length; j++) {
                System.out.println("Room: " + m.all_rooms[3].getAttachedTo()[i].getRooms()[j].getRoom_id());
            }
        }
        */

        m.setStartEnd();
        m.dijkstra();
        System.out.println("Room: " + m.end.getRoom_id());

        //System.out.println(Arrays.toString(soultion));

        /*
        for (int i = 0; i < soultion.length; i++) {
            if(soultion[i] != 0){
                System.out.println("Room "+ i);
            }
        }

        */

    }
}
