import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Map {
    //map should contain all rooms and connections
    private Room[] all_rooms;
    private Connection[] all_connections;
    //HashMap<String, Connection> adjacency;
    private Connection[][] adjacency;

    public Map() {

        this.all_rooms = JsonParser.parseRoom();
        this.all_connections = JsonParser.parseConnection();
        this.adjacency = this.makeList();

        for (int i = 0; i <adjacency.length ; i++) {
            System.out.println("Room:");
            System.out.println(i);
            System.out.println("Connections:");
            for (int j = 0; j < adjacency[i].length; j++) {

                System.out.println(adjacency[i][j].getConnection_id());
                //System.out.println(adjacency[i].length);

            }

        }

        // Print keys and values
        //for (String i : adjacency.keySet()) {
            //System.out.println("key: " + i + " value: " + adjacency.get(i).getConnection_name());
        //}


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


    public Connection[][] makeList(){

        int counter;

        Connection[][] adjacent = new Connection[all_rooms.length][];

        HashMap<Integer, Connection> roomConnections= new HashMap<Integer, Connection>();

        //for each room
        for (int i = 0; i < all_rooms.length; i++) {
            //reset the size of the number of connections in that position
            counter=0;

            //for all connections
            for (int k = 0; k < all_connections.length ; k++) {
                //for all connected rooms
                for (int j = 0; j < all_connections[k].getConnected_rooms().length; j++) {
                    //if the first room has its id in the connected rooms matrix
                    if(all_rooms[i].getRoom_id() == all_connections[k].getConnected_rooms()[j]){
                        //store the connection in a hash map
                        roomConnections.put(counter,all_connections[k]);
                        //add one position in the matrix
                        counter++;
                        break;
                    }
                }
            }

            adjacent[i] = new Connection[counter];

            for (Integer b: roomConnections.keySet()) {
                adjacent[i][b] = roomConnections.get(b);
            }

            roomConnections.clear();

        }

        return adjacent;

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
