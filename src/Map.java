import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Map {
    //map should contain all rooms and connections
    private Room[] all_rooms;
    private Connection[] all_connections;
    private Connection[][] adjacency;

    public Map() {

        this.all_rooms = JsonParser.parseRoom();
        this.all_connections = JsonParser.parseConnection();
        this.adjacency = this.makeList();

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

        int counter = 0;

        for (int i = 0; i < adjacency[given.getRoom_id()].length; i++) {
            //get the total number of adjacent rooms
            counter =+ adjacency[given.getRoom_id()][i].getConnected_rooms().length-1;
        }
        int[] adjacents_int = new int[counter];

        //for each room that's adjacent
        for (int i = 0; i < counter ; i++) {
            //check which connected rooms are
            for (int j = 0; j <adjacency[given.getRoom_id()][i].getConnected_rooms().length ; j++) {
                if(given.getRoom_id() != adjacency[given.getRoom_id()][i].getConnected_rooms()[j]){
                    adjacents_int[i] = adjacency[given.getRoom_id()][i].getConnected_rooms()[j];
                }
            }
        }

        Room[] adjacent_rooms = new Room[counter];

        for (int i = 0; i < adjacents_int.length; i++) {

            adjacent_rooms[i] = getRoomById(adjacents_int[i]);

        }



       return adjacent_rooms;

    }

    private Room getRoomById(int id){

        for (int i = 0; i < all_rooms.length; i++) {
            if(id ==  all_rooms[i].getRoom_id() ){
                return  all_rooms[i];
            }
        }
        return null;
    }

    private void checkList(){

        for (int i = 0; i <adjacency.length ; i++) {
            System.out.println("Length of pos "+i+":");
            System.out.println(adjacency[i].length);
            System.out.println("Room:");
            System.out.println(i);
            System.out.println("Connections:");
            for (int j = 0; j < adjacency[i].length; j++) {

                System.out.println(adjacency[i][j].getConnection_id());

            }

        }
    }

}
