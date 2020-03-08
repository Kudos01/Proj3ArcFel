import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Map {
    //map should contain all rooms and connections
    private Room[] all_rooms;
    private Connection[] all_connections;
    private HashMap<String, Connection>[] adjacency_list;
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


    public Room[] getAdjacent(Room given){

        StringBuilder sb = new StringBuilder();

        int connections = 0;
        int total_rooms = 0;

        for (int i = 0; i < all_connections.length; i++) {
            sb.append(given.getRoom_id());
            sb.append("_");
            sb.append(all_connections[i].getConnection_id());

            if(adjacency_list[given.getRoom_id()].get(sb.toString()) != null){
                connections++;
                total_rooms += all_connections[i].getConnected_rooms().length;
            }
            sb.delete(0, sb.length());
        }

        Connection[] connections_of_room = new Connection[connections];
        Room[] rooms_connected = new Room[total_rooms];

        connections =0;
        total_rooms = 0;

        for (int i = 0; i < all_connections.length; i++) {
            sb.append(given.getRoom_id());
            sb.append("_");
            sb.append(all_connections[i].getConnection_id());

            if(adjacency_list[given.getRoom_id()].get(sb.toString()) != null){
                connections_of_room[connections] = all_connections[i];
                connections++;
            }
            sb.delete(0, sb.length());
        }


        for (int i = 0; i < connections_of_room.length; i++) {
            for (int j = 0; j < connections_of_room[i].getConnected_rooms().length; j++) {
                rooms_connected[total_rooms] = all_rooms[connections_of_room[i].getConnected_rooms()[j]];
                rooms_connected[total_rooms].setAttachedTo(connections_of_room[i]);
                total_rooms++;
            }
        }
        return rooms_connected;
    }


    public HashMap<String, Connection>[] makeList(){

        HashMap[] adjacent = new HashMap[all_rooms.length];

        StringBuilder sb = new StringBuilder();

        //for each room
        for (int i = 0; i < all_rooms.length; i++) {
            adjacent[i] = new HashMap<>();
            for (int j = 0; j < all_connections.length; j++) {
                for (int k = 0; k < all_connections[j].getConnected_rooms().length; k++) {
                    if (all_connections[j].getConnected_rooms()[k] == all_rooms[i].getRoom_id()) {
                        //adjacent[i].put(all_connections[j].getConnected_rooms()[k], all_connections[k]);
                        sb.append(all_rooms[i].getRoom_id());
                        sb.append("_");
                        sb.append(all_connections[j].getConnection_id());

                        adjacent[i].put(sb.toString(), all_connections[j]);
                    }
                    sb.delete(0, sb.length());
                }
            }
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
