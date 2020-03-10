import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Map {
    //map should contain all rooms and connections
    public Room[] all_rooms;
    public Connection[] all_connections;
    public Room start;
    public Room end;

    public Map(){
        this.all_rooms = JsonParser.parseRoom();
        this.all_connections = JsonParser.parseConnection();
        //this.adjacency_list = this.makeList();
        setAdjacents();
    }

    public void setStartEnd(){
        Scanner s = new Scanner(System.in);

        System.out.println("Please insert the starting room: ");
        this.start = new Room(all_rooms[s.nextInt()]);

        System.out.println("Please insert the ending room: ");
        this.end = new Room(all_rooms[s.nextInt()]);

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


    /*
    public Adjacent[] getAdjacents(Room given){
        StringBuilder sb = new StringBuilder();
        int connections = 0;
        for (int i = 0; i < all_connections.length; i++) {
            sb.append(given.getRoom_id());
            sb.append("_");
            sb.append(all_connections[i].getConnection_id());
            if (adjacency_list[given.getRoom_id()].get(sb.toString()) != null) {
                connections++;
            }
            sb.delete(0, sb.length());
        }
        Adjacent[] connections_of_room = new Adjacent[connections];
        connections = 0;
        for (int i = 0; i < all_connections.length; i++) {
            sb.append(given.getRoom_id());
            sb.append("_");
            sb.append(all_connections[i].getConnection_id());
            if (adjacency_list[given.getRoom_id()].get(sb.toString()) != null) {
                connections_of_room[connections] = new Adjacent(new Room[all_connections[i].getConnected_rooms().length], all_connections[i].getEnemy_probability());
                connections_of_room[connections].setRoomsBasedOnIDs(all_connections[i].getConnected_rooms(), all_rooms);
                connections++;
            }
            sb.delete(0, sb.length());
        }
        all_rooms[given.getRoom_id()].setAttachedTo(connections_of_room);
        return connections_of_room;
    }
     */


    public void setAdjacents(){

        int connections = 0;

        for (int i = 0; i < all_rooms.length; i++) {

            for (int j = 0; j < all_connections.length; j++) {
                for (int k = 0; k < all_connections[j].getConnected_rooms().length; k++) {
                    if (all_connections[j].getConnected_rooms()[k] == all_rooms[i].getRoom_id()) {
                        connections++;
                    }
                }
            }

            Adjacent[] connections_of_room = new Adjacent[connections];

            connections =0;

            for (int j = 0; j < all_connections.length; j++) {
                for (int k = 0; k < all_connections[j].getConnected_rooms().length; k++) {
                    if (all_connections[j].getConnected_rooms()[k] == all_rooms[i].getRoom_id()) {
                        connections_of_room[connections] = new Adjacent(new Room[all_connections[j].getConnected_rooms().length - 1], all_connections[j].getEnemy_probability());
                        connections_of_room[connections].setRoomsBasedOnIDs(all_connections[j].getConnected_rooms(), all_rooms, all_rooms[i].getRoom_id());
                        connections++;
                    }
                }
            }
            connections =0;

            all_rooms[i].setAttachedTo(connections_of_room);

        }
    }

    /*
    public HashMap<String, Connection>[] makeList(){
        HashMap[] adjacent = new HashMap[all_rooms.length];
        StringBuilder sb = new StringBuilder();
        //for each room
        for (int i = 0; i < all_rooms.length; i++) {
            adjacent[i] = new HashMap<>();
            for (int j = 0; j < all_connections.length; j++) {
                for (int k = 0; k < all_connections[j].getConnected_rooms().length; k++) {
                    if (all_connections[j].getConnected_rooms()[k] == all_rooms[i].getRoom_id()) {
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
     */

    private boolean checkAdjacents(Room given){
        
        int flag = 0;

        boolean[] adjAreVisited = new boolean[given.getAttachedTo().length];

        for (int i = 0; i < given.getAttachedTo().length; i++) {
            int counter=0;
            for (int j = 0; j < given.getAttachedTo()[i].getRooms().length; j++) {
                if(given.getAttachedTo()[i].getRooms()[j].getVisited()){
                    counter++;
                }

            }
            if(counter == given.getAttachedTo()[i].getRooms().length){
                adjAreVisited[i] = true;
            }
            else{
                adjAreVisited[i] = false;
            }
            
        }

        for (int i = 0; i < adjAreVisited.length; i++) {
            if(adjAreVisited[i] == true){
                flag++;
            }
        }
        if(flag == adjAreVisited.length){

            return true;
            
        }

        return false;
    }

    public int[] dijkstra(){

        int prob;
        int next_room_index;
        int best_room_index = 0;

        //routes -> List of the walks to each node from start
        //Route[] routes = new Route[0];
        int[] walk = new int[all_rooms.length];
        int[] probabilities = new int[all_connections.length];

        Arrays.fill(probabilities, Integer.MAX_VALUE);
        probabilities[start.getRoom_id()] = 0;

        Room current = start;

        int walks_counter =1;

        //fill rooms with connected to?

        //while there are nodes left to visit and end is not visited do
        while(!allVisited() && !endVisited(end)){

            for (int i = 0; i < current.getAttachedTo().length; i++) {
                for (int j = 0; j < current.getAttachedTo()[i].getRooms().length; j++) {

                    if(!all_rooms[current.getAttachedTo()[i].getRooms()[j].getRoom_id()].getVisited()){
                        prob = probabilities[current.getRoom_id()] + current.getAttachedTo()[i].getEnemy_probability();

                        //get the new probability of going to that node
                        if(probabilities[current.getAttachedTo()[i].getRooms()[j].getRoom_id()] > prob){
                            probabilities[current.getAttachedTo()[i].getRooms()[j].getRoom_id()] = prob;
                            //update the candidate for next room
                            best_room_index = current.getAttachedTo()[i].getRooms()[j].getRoom_id();

                            //update walk
                            walk[current.getAttachedTo()[i].getRooms()[j].getRoom_id()] = current.getRoom_id();
                        }
                    }
                }
            }

            all_rooms[current.getRoom_id()].setVisitedTrue();

            int min = probabilities[current.getRoom_id()] + current.getAttachedTo()[0].getEnemy_probability();
            next_room_index = current.getAttachedTo()[0].getRooms()[0].getRoom_id();

            for (int i = 0; i < current.getAttachedTo().length; i++) {
                for (int j = 0; j < current.getAttachedTo()[i].getRooms().length; j++) {
                    if(current.getAttachedTo()[i].getEnemy_probability() < min && !all_rooms[current.getAttachedTo()[i].getRooms()[j].getRoom_id()].getVisited()) {
                        min = current.getAttachedTo()[i].getEnemy_probability();
                        next_room_index = current.getAttachedTo()[i].getRooms()[j].getRoom_id();
                    }
                }
            }
            current = all_rooms[next_room_index];
        }

        printSolution(walk, end.getRoom_id());

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

    public void printSolution(int[] solution, int pos){
        if(solution[pos] == start.getRoom_id()){
            System.out.println("Room: " + start.getRoom_id());
        }else{
            printSolution(solution, solution[pos]);
            System.out.println("Room: " + solution[pos]);
        }
    }
}