import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Map {
    //map should contain all rooms and connections
    private Room[] all_rooms;
    private Connection[] all_connections;
    private HashMap<String, Connection>[] adjacency_list;
    //private int[] startEndRooms = new int[2];

    public Map() throws Exception {
        this.all_rooms = JsonParser.parseRoom();
        this.all_connections = JsonParser.parseConnection();
        this.adjacency_list = this.makeList();
    }

    /*
    private void askForRooms(){
        Scanner s = new Scanner(System.in);
        System.out.println("Please Enter the starting room: ");
        startEndRooms[0] = s.nextInt();

        System.out.println("Please Enter the ending room: ");
        startEndRooms[1] = s.nextInt();
    }

     */


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
                total_rooms += all_connections[i].getConnected_rooms().length - 1;
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
                if(connections_of_room[i].getConnected_rooms()[j]!=given.getRoom_id()){
                    rooms_connected[total_rooms] = all_rooms[connections_of_room[i].getConnected_rooms()[j]];
                    rooms_connected[total_rooms].setAttachedTo(connections_of_room[i]);
                    total_rooms++;
                }
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

    public int[] dijkstra(){

        Room start = all_rooms[0];
        Room end = all_rooms[10];

        int prob = 0;

        //routes -> List of the walks to each node from start
        //Route[] routes = new Route[0];
        int[] walk = new int[all_rooms.length];
        int[] probabilities = new int[all_connections.length];

        Arrays.fill(probabilities, Integer.MAX_VALUE);
        probabilities[start.getRoom_id()] = 0;

        Room current = new Room(start);

        int walks_counter =1;

        //while there are nodes left to visit and end is not visited do
        while(!allVisited() && !endVisited(end)){

            for (Room adj: getAdjacent(current)) {
                if(!adj.getVisited()){
                    prob += adj.getAttachedTo().getEnemy_probability();
                    //adj.getProbability + original.get
                    //get the new probability of going to that node
                    if(probabilities[adj.getRoom_id()] > prob){
                        probabilities[adj.getRoom_id()] = prob;
                        //update the route
                        //walk[adj.getRoom_id()] = walks_counter;

                    }
                }
            }

            all_rooms[current.getRoom_id()].setVisitedTrue();

            //update route rather than room

            int min = getAdjacent(current)[0].getAttachedTo().getEnemy_probability();
            int next_room_index =0;
            //current = getAdjacent(current)[0];

            for (int i = 0; i < getAdjacent(current).length; i++) {
                if(getAdjacent(current)[i].getAttachedTo().getEnemy_probability() < min && !getAdjacent(current)[i].getVisited()){
                    min = getAdjacent(current)[i].getAttachedTo().getEnemy_probability();
                    //current = getAdjacent(current)[i];
                    next_room_index = i;
                    System.out.println("Better current");
                }
            }

            current = getAdjacent(current)[next_room_index];

            walks_counter++;
        }

        return walk;
    }

    private boolean allVisited(){
        for (int i = 0; i < all_rooms.length; i++) {
            if(!all_rooms[i].getVisited()){
                return false;
            }
        }
        return true;
    }

    private boolean endVisited(Room end){

        for (int i = 0; i < all_rooms.length; i++) {
            if(all_rooms[i].getVisited() && all_rooms[i].getRoom_id() == end.getRoom_id()){
                return true;
            }
        }
        return false;
    }

    public Room[] getAll_rooms() {
        return all_rooms;
    }

    public Connection[] getAll_connections() {
        return all_connections;
    }
}
