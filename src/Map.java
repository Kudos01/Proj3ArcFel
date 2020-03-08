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
        /*

        for (int i = 0; i <adjacency_list.length ; i++) {
            System.out.println("Room "+i);

            for (int j = 0; j < adjacency_list[i].getSize(); j++) {

                System.out.println(adjacency_list[i].get(j));

                System.out.println("Connection: "+ j);
                Connection c = (Connection) adjacency_list[i].get(j);
                System.out.println(c.getConnection_id());

            }

        }

         */

    }


    public Connection[][] makeList(){

        int counter;
        int connected_rooms = 0;

        Connection[][] adjacent = new Connection[all_rooms.length][];

        //for each room
        for (int i = 0; i < all_rooms.length; i++) {
            //reset the size of the number of connections in that position
            counter=0;

            //for all connections
            for (int k = 0; k < all_connections.length ; k++) {
                //for all connected rooms
                for (int j = 0; j < all_connections[k].getConnected_rooms().length; j++) {
                    //if the first room has its id in the connected rooms matrix
                    if (all_rooms[i].getRoom_id() == all_connections[k].getConnected_rooms()[j]) {
                        //store the connection in a hash map
                        //add one position in the matrix
                        counter++;
                    }
                }
            }

            adjacent[i] = new Connection[counter];

            for (int k = 0; k < all_connections.length ; k++) {
                for (int j = 0; j < all_connections[k].getConnected_rooms().length; j++) {
                    //if the first room has its id in the connected rooms matrix
                    if (all_rooms[i].getRoom_id() == all_connections[k].getConnected_rooms()[j]) {
                        //add one position in the matrix
                        adjacent[i][connected_rooms] = new Connection();
                        adjacent[i][connected_rooms] = all_connections[k];
                        connected_rooms++;
                    }
                }
            }
            connected_rooms =0;
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
