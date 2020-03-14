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
        System.out.println("Parsing the Rooms Json..");
        this.all_rooms = JsonParser.parseRoom();
        System.out.println("Done");
        System.out.println("");
        System.out.println("Parsing the Connections Json..");
        this.all_connections = JsonParser.parseConnection();
        System.out.println("Done");
        //this.adjacency_list = this.makeList();
        System.out.println("Finding the adjacents to each room...");
        setAdjacents();
        System.out.println("Done");
    }

    public void setStartEnd(){
        Scanner s = new Scanner(System.in);

        System.out.println("Please insert the starting room: ");
        this.start = new Room(all_rooms[s.nextInt()]);

        System.out.println("Please insert the ending room: ");
        this.end = new Room(all_rooms[s.nextInt()]);

    }


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

    private boolean haveSomewhereToGo(int room_id){

        Room given = all_rooms[room_id];

        int num_visited=0;
        int counter_adjacent;

        //for this adjacent
        for (int i = 0; i < given.getAttachedTo().length; i++) {
            //get each of its adjacents
            counter_adjacent=0;
            for (int j = 0; j < given.getAttachedTo()[i].getRooms().length; j++) {
                if(all_rooms[given.getAttachedTo()[i].getRooms()[j].getRoom_id()].getVisited()){
                    counter_adjacent++;
                }
            }
            if(counter_adjacent == given.getAttachedTo()[i].getRooms().length){
                num_visited++;
            }
        }
        return num_visited != given.getAttachedTo().length;
    }

    public int[] dijkstra(){

        int prob;
        int next_room_index;

        //routes -> List of the walks to each node from start
        //Route[] routes = new Route[0];
        int[] walk = new int[all_rooms.length];
        int[] probabilities = new int[all_connections.length];

        Arrays.fill(probabilities, Integer.MAX_VALUE);
        probabilities[start.getRoom_id()] = 0;

        Room current = start;

        int min =0;

        //fill rooms with connected to?

        //while there are nodes left to visit and end is not visited do
        while(!allVisited() && !endVisited(end)){

            for (int i = 0; i < current.getAttachedTo().length; i++) {
                for (int j = 0; j < current.getAttachedTo()[i].getRooms().length; j++) {

                    if(!all_rooms[current.getAttachedTo()[i].getRooms()[j].getRoom_id()].getVisited()/*&& haveSomewhereToGo(current.getAttachedTo()[i].getRooms()[j].getRoom_id())*/ ){
                        prob = probabilities[current.getRoom_id()] + current.getAttachedTo()[i].getEnemy_probability();

                        //get the new probability of going to that node
                        if(probabilities[current.getAttachedTo()[i].getRooms()[j].getRoom_id()] > prob){
                            probabilities[current.getAttachedTo()[i].getRooms()[j].getRoom_id()] = prob;
                            //update the candidate for next room
                            //best_room_index = current.getAttachedTo()[i].getRooms()[j].getRoom_id();

                            //update walk
                            walk[current.getAttachedTo()[i].getRooms()[j].getRoom_id()] = current.getRoom_id();
                        }
                    }
                }
            }

            all_rooms[current.getRoom_id()].setVisitedTrue();

            min = Integer.MAX_VALUE;
            next_room_index = current.getAttachedTo()[0].getRooms()[0].getRoom_id();

            for (int i = 0; i < current.getAttachedTo().length; i++) {
                for (int j = 0; j < current.getAttachedTo()[i].getRooms().length; j++) {
                    //if the value is better than the minimum prob so far
                    //and that node has not been visited
                    //and that node has somewhere to go
                    if(current.getAttachedTo()[i].getEnemy_probability() < min
                            && !all_rooms[current.getAttachedTo()[i].getRooms()[j].getRoom_id()].getVisited()
                            && haveSomewhereToGo(current.getAttachedTo()[i].getRooms()[j].getRoom_id())){

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