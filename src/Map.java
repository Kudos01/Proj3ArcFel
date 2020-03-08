import java.util.Arrays;
import java.util.Scanner;

public class Map {
    //map should contain all rooms and connections
    private Room[] all_rooms;
    private Connection[] all_connections;
    private Connection[][] adjacency_list;
    private int[] startEndRooms = new int[2];

    public Map() throws Exception {

        this.all_rooms = JsonParser.parseRoom();
        this.all_connections = JsonParser.parseConnection();
        this.adjacency_list = this.makeList();
    }


    private void askForRooms(){
        Scanner s = new Scanner(System.in);
        System.out.println("Please Enter the starting room: ");
        startEndRooms[0] = s.nextInt();

        System.out.println("Please Enter the ending room: ");
        startEndRooms[1] = s.nextInt();



    }


    public Connection[][] makeList(){

        int counter;
        int connected_rooms = 0;

        Connection[][] adjacent = new Connection[all_rooms.length][];

        //for each room
        for (int i = 0; i < all_rooms.length; i++) {
            //reset the size of the number of connections in that position (room)
            counter=0;

            //for all connections
            for (int k = 0; k < all_connections.length ; k++) {
                //for all connected rooms
                for (int j = 0; j < all_connections[k].getConnected_rooms().length; j++) {
                    //if the first room has its id in the connected rooms matrix
                    if (all_rooms[i].getRoom_id() == all_connections[k].getConnected_rooms()[j]) {
                        //count up to know how many connections particular room has
                        counter++;
                    }
                }
            }

            //make an array of that size of connections
            adjacent[i] = new Connection[counter];

            for (int k = 0; k < all_connections.length ; k++) {
                for (int j = 0; j < all_connections[k].getConnected_rooms().length; j++) {
                    //if the first room has its id in the connected rooms matrix
                    if (all_rooms[i].getRoom_id() == all_connections[k].getConnected_rooms()[j]) {
                        //add the connection we found to the corresponding room, creating a new connection object
                        adjacent[i][connected_rooms] = new Connection();
                        adjacent[i][connected_rooms] = all_connections[k];
                        //ad to the counter that tells us how many connections a room has, to keep adding them in different positions
                        connected_rooms++;
                    }
                }
            }
            //reset the counter
            connected_rooms =0;
        }
        //return the constructed map (graph)
        return adjacent;
    }

    public Room[] getAll_rooms() {
        return all_rooms;
    }

    public Connection[] getAll_connections() {
        return all_connections;
    }
}
