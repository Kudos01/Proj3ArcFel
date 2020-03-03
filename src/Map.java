import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Map {
    //map should contain all rooms and connections
    private Room[] all_rooms;
    private Connection[] all_connections;
    HashMap<String, Connection> adjacency;

    public Map() {

        this.all_rooms = JsonParser.parseRoom();
        this.all_connections = JsonParser.parseConnection();
        this.hashMap();

        // Print keys and values
        for (String i : adjacency.keySet()) {
            System.out.println("key: " + i + " value: " + adjacency.get(i).getConnection_name());
        }


        //JsonParser.testParsing(all_rooms,all_connections);


        /*
        this.adjacency_matrix = new int [all_rooms.length][all_rooms.length];

        for (int i = 0; i < all_rooms.length; i++) {
            for (int j = 0; j < all_rooms.length; j++) {
                adjacency_matrix[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < all_connections.length ; i++) {

            if (all_connections[i].getConnected_rooms().length == 2) {

                adjacency_matrix[all_connections[i].getConnected_rooms()[0]][all_connections[i].getConnected_rooms()[1]] = all_connections[i].getEnemy_probability();
            } else{

                for (int j = 0; j < all_connections[i].getConnected_rooms().length -1; j++) {
                    for (int k = 0; k < all_connections[i].getConnected_rooms().length -1 -j; k++) {
                        adjacency_matrix[all_connections[i].getConnected_rooms()[j]][all_connections[i].getConnected_rooms()[k]] = all_connections[i].getEnemy_probability();
                    }
                }
            }
        }

        for (int i = 0; i < all_rooms.length; i++) {
            for (int j = 0; j < all_rooms.length; j++) {
                if(adjacency_matrix[i][j] != Integer.MAX_VALUE){
                    System.out.println(adjacency_matrix[i][j]);
                }
            }
        }
        */

    }

    public void hashMap(){

        this.adjacency = new HashMap<String, Connection>();
        for (int i = 0; i < all_connections.length ; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < all_connections[i].getConnected_rooms().length; j++) {

                sb.append(all_connections[i].getConnected_rooms()[j]);

                if(j!=all_connections[i].getConnected_rooms().length-1){
                    sb.append("_");
                }

            }
            String connected_room_key = sb.toString();
            adjacency.put(connected_room_key,all_connections[i]);
        }

    }

    public Room[] getAll_rooms() {
        return all_rooms;
    }

    public Connection[] getAll_connections() {
        return all_connections;
    }

    public Room[] getAdjacent(Room given){

       // return adjacents;
        return null;

    }

}
