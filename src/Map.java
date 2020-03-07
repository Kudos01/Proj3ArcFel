import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Map {
    //map should contain all rooms and connections
    private Room[] all_rooms;
    private Connection[] all_connections;
    private LinkedList[] adjacency_list;

    public Map() {

        this.all_rooms = JsonParser.parseRoom();
        this.all_connections = JsonParser.parseConnection();
        this.adjacency_list = this.makeList();

        for (int i = 0; i <adjacency_list.length ; i++) {
            System.out.println("Room "+adjacency_list[i]);
            adjacency_list[i].toString();


        }

    }

    private LinkedList[] makeList(){

        System.out.println("We in dis bitch");

        LinkedList[] adjacency_list = new LinkedList[all_rooms.length];

        for (int i = 0; i < adjacency_list.length; i++) {

            adjacency_list[i] = new LinkedList();

        }

        System.out.println("we initialized LL");

            for (int j = 0; j < all_connections.length; j++) {
                for (int k = 0; k < all_connections[j].getConnected_rooms().length ; k++) {

                    System.out.println("we addin those connections");
                    //adjacency_list[all_connections[j].getConnected_rooms()[k]].add(adjacency_list[all_connections[j].getConnected_rooms()[k]],all_connections[j]);
                    adjacency_list[all_connections[j].getConnected_rooms()[k]].add(all_connections[j]);
                    System.out.println( adjacency_list[all_connections[j].getConnected_rooms()[k]].get(k));
                }

                System.out.println("we goin to the next connection");

            }
        System.out.println(adjacency_list);

        return adjacency_list;
    }

    /*

    public Connection[][] makeList(){

        int counter;
        int num_rooms_connected = 0;

        for (int i = 0; i < all_connections.length; i++) {
            for (int j = 0; j < all_connections[i].getConnected_rooms().length; j++) {
                num_rooms_connected =+ all_connections[i].getConnected_rooms()[j];
            }

        }


        Adjacent[] all_adjacents = new Adjacent[num_rooms_connected];
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

            //Connection[] current_room_connections = new Connection[counter];

            int adj_counter =0;

            for (Integer b: roomConnections.keySet()) {
                adjacent[i][b] = roomConnections.get(b);

                all_adjacents[adj_counter] = new Adjacent(i,roomConnections.get(b));

                //current_room_connections[b] = roomConnections.get(b);
                adj_counter++;
            }

           // all_rooms[i].setAll_room_connections(current_room_connections);
            //Room room = new Room(i,current_room_connections);



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

        int counter =0;

        for (int i = 0; i < given.getAll_room_connections().length; i++) {
            counter =+ given.getAll_room_connections()[i].getConnected_rooms().length;

        }

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

     */


}
