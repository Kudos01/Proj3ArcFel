import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.jar.JarOutputStream;

public class Map {
    //map should contain all rooms and connections
    private Room[] all_rooms;
    private Connection[] all_connections;
    private Connection[][] adjacency_list;

    public Map() throws Exception {

        this.all_rooms = JsonParser.parseRoom();
        this.all_connections = JsonParser.parseConnection();
        this.adjacency_list = this.makeList();
    }


    public Connection[][] makeList(){

        int counter;
        int connection_rooms = 0;

        Connection[][] adjacent = new Connection[all_rooms.length][];

        //for each room
        for (int i = 0; i < all_rooms.length; i++) {
            //reset the size of the number of connections in that position
            counter=0;

            System.out.println("WE IN ROOM: " + i);

            //for all connections
            for (Connection allConnection : all_connections) {
                //for all connected rooms
                for (int j = 0; j < allConnection.getConnected_rooms().length; j++) {
                    //if the first room has its id in the connected rooms matrix
                    if (all_rooms[i].getRoom_id() == allConnection.getConnected_rooms()[j]) {
                        //store the connection in a hash map
                        //add one position in the matrix
                        counter++;
                    }
                }
            }

            adjacent[i] = new Connection[counter];

            for (Connection all_connection : all_connections) {
                for (int j = 0; j < all_connection.getConnected_rooms().length; j++) {
                    //if the first room has its id in the connected rooms matrix
                    if (all_rooms[i].getRoom_id() == all_connection.getConnected_rooms()[j]) {
                        //add one position in the matrix
                        adjacent[i][connection_rooms] = new Connection();
                        adjacent[i][connection_rooms] = all_connection;
                        connection_rooms++;
                    }
                }
            }
            //reset the counter for knowing how many connections a room had
            connection_rooms =0;
        }
        return adjacent;
    }

    public Room[] getAll_rooms() {
        return all_rooms;
    }

    public Connection[] getAll_connections() {
        return all_connections;
    }
}
