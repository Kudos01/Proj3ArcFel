import java.util.Arrays;
import java.util.Scanner;

public class Map {
    //map should contain all rooms and connections
    public Room[] all_rooms;
    public Connection[] all_connections;
    public Room start;
    public Room end;
    private static final Scanner s = new Scanner(System.in);

    public Map(){
        System.out.println("Parsing the Rooms Json..");
        this.all_rooms = JsonParser.parseRoom();
        System.out.println("Done\n");
        System.out.println("Parsing the Connections Json..");
        this.all_connections = JsonParser.parseConnection();
        System.out.println("Done");
        System.out.println("Finding the adjacents to each room...");
        long startTime = System.nanoTime();
        setAdjacents();
        long endTime = System.nanoTime();

        double duration = (endTime - startTime)/1000000.0;
        System.out.println("Time taken to find adjacents: " + duration + " milliseconds\n");

        System.out.println("Done");
    }

    //method to ask the user for the start and end rooms
    public void setStartEnd(){
        System.out.println("Please insert the starting room: ");
        this.start = new Room(all_rooms[s.nextInt()]);

        System.out.println("Please insert the ending room: ");
        this.end = new Room(all_rooms[s.nextInt()]);

    }


    public void setAdjacents(){

        //counter to know how many connections a room is in
        int connections = 0;

        //for all the rooms
        for (int i = 0; i < all_rooms.length; i++) {
            //for all the connections
            for (int j = 0; j < all_connections.length; j++) {
                //for al the rooms in a connection
                for (int k = 0; k < all_connections[j].getConnected_rooms().length; k++) {
                    //check if the current room exists in the connection, if so add to the counter
                    if (all_connections[j].getConnected_rooms()[k] == all_rooms[i].getRoom_id()) {
                        connections++;
                    }
                }
            }

            //create an adjacent object with the size of the connections the room is in
            Adjacent[] connections_of_room = new Adjacent[connections];

            //reset the counter
            connections =0;

            //do the same as before but now filling in the information if the enemy probabilities
            for (int j = 0; j < all_connections.length; j++) {
                for (int k = 0; k < all_connections[j].getConnected_rooms().length; k++) {
                    if (all_connections[j].getConnected_rooms()[k] == all_rooms[i].getRoom_id()) {
                        //fill the adjacent array with the info of the enemy probabilities and the connected rooms (ignoring that a room is connected to itself)
                        connections_of_room[connections] = new Adjacent(new Room[all_connections[j].getConnected_rooms().length - 1], all_connections[j].getEnemy_probability());
                        connections_of_room[connections].setRoomsBasedOnIDs(all_connections[j].getConnected_rooms(), all_rooms, all_rooms[i].getRoom_id());

                        //increment the counter to fill in the next position
                        connections++;
                    }
                }
            }
            connections =0;

            //set the attached rooms of the room we iterated through currently
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

    public void dijkstra(){

        int prob;
        int next_room_index;

        //array for storing the walks
        int[] walk = new int[all_rooms.length];

        //array to add up the probabilities and find the shortest path
        int[] distances = new int[all_rooms.length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start.getRoom_id()] = 0;

        //array for storing the probabilities of not finding an enemy up to a particular room
        double[] probabilities = new double[all_rooms.length];
        Arrays.fill(probabilities, 1);

        Room current = start;

        int min;

        //while there are nodes left to visit and end is not visited do
        while(!allVisited() && !endVisited(end)){

            for (int i = 0; i < current.getAttachedTo().length; i++) {
                for (int j = 0; j < current.getAttachedTo()[i].getRooms().length; j++) {

                    if(!all_rooms[current.getAttachedTo()[i].getRooms()[j].getRoom_id()].getVisited()){
                        prob = distances[current.getRoom_id()] + current.getAttachedTo()[i].getEnemy_probability();

                        //get the new probability of going to that node
                        if(distances[current.getAttachedTo()[i].getRooms()[j].getRoom_id()] > prob){
                            distances[current.getAttachedTo()[i].getRooms()[j].getRoom_id()] = prob;
                            //update the candidate for next room
                            //best_room_index = current.getAttachedTo()[i].getRooms()[j].getRoom_id();

                            //update walk
                            walk[current.getAttachedTo()[i].getRooms()[j].getRoom_id()] = current.getRoom_id();

                            //update probability
                            probabilities[current.getAttachedTo()[i].getRooms()[j].getRoom_id()] = ((1 - current.getAttachedTo()[i].getEnemy_probability()/100.0) * probabilities[current.getRoom_id()]);
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
                    if((distances[current.getAttachedTo()[i].getRooms()[j].getRoom_id()] + current.getAttachedTo()[i].getEnemy_probability()) < min
                            && !all_rooms[current.getAttachedTo()[i].getRooms()[j].getRoom_id()].getVisited()){

                        min = distances[current.getAttachedTo()[i].getRooms()[j].getRoom_id()] + current.getAttachedTo()[i].getEnemy_probability();
                        next_room_index = current.getAttachedTo()[i].getRooms()[j].getRoom_id();
                    }
                }
            }
            //update the current with the one found
            current = all_rooms[next_room_index];
        }

        //Print the path to the end room
        printSolution(walk, end.getRoom_id());
        System.out.println("Room: " + end.getRoom_id());

        //Print out the probability of not finding an enemy of the whole path
        System.out.println("Probability of not finding an enemy: " + probabilities[end.getRoom_id()]*100 + "%");
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