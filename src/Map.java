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
        int comp_prob;
        int next_room_index;
        float no_enemy;
        float enemy;

        int[] walk = new int[all_rooms.length];
        float[] probabilities = new float[all_connections.length];
        float[] comp_probs = new float[all_rooms.length];

        Arrays.fill(probabilities, Integer.MAX_VALUE);
        Arrays.fill(comp_probs, 1);
        probabilities[start.getRoom_id()] = 0;
        comp_probs[start.getRoom_id()] = 0;

        Room current = start;

        int min =0;

        //fill rooms with connected to?
        //while there are nodes left to visit and end is not visited do
        while(!allVisited() && !endVisited()){
            //for all of the adjacents
            for (int i = 0; i < current.getAttachedTo().length; i++) {
                for (int j = 0; j < current.getAttachedTo()[i].getRooms().length; j++) {

                    //if this adjacent hasn't been visited
                    if(!all_rooms[current.getAttachedTo()[i].getRooms()[j].getRoom_id()].getVisited() && haveSomewhereToGo(current.getAttachedTo()[i].getRooms()[j].getRoom_id())){
                        //prob = probabilities[current.getRoom_id()] + current.getAttachedTo()[i].getEnemy_probability();
                        if(current.getRoom_id() == start.getRoom_id()){
                            //get the probability of finding an enemy
                            enemy = (float) current.getAttachedTo()[i].getEnemy_probability()/100;
                            //get the probability of not finding an enemy
                            no_enemy = 1-enemy;
                            //store both
                            comp_probs[current.getAttachedTo()[i].getRooms()[j].getRoom_id()] = no_enemy;
                            probabilities[current.getAttachedTo()[i].getRooms()[j].getRoom_id()]= enemy;
                            //comp_prob = 1 - ((1-probabilities[current.getRoom_id()])*(1-current.getAttachedTo()[i].getEnemy_probability()));

                        }
                        else{

                        }


                        //get the new probability of going to that node
                        if(comp_probs[current.getAttachedTo()[i].getRooms()[j].getRoom_id()] < comp_prob){
                            comp_probs[current.getAttachedTo()[i].getRooms()[j].getRoom_id()] = comp_prob;
                            //comp_probs[current.getAttachedTo()[i].getRooms()[j].getRoom_id()] = comp_prob;
                            //update the candidate for next room
                            //best_room_index = current.getAttachedTo()[i].getRooms()[j].getRoom_id();

                            //update walk
                            walk[current.getAttachedTo()[i].getRooms()[j].getRoom_id()] = current.getRoom_id();

                            //System.out.println("WALK UPDATED");
                        }
                    }
                }
            }

            all_rooms[current.getRoom_id()].setVisitedTrue();

            min = 0;
            next_room_index = current.getAttachedTo()[0].getRooms()[0].getRoom_id();

            for (int i = 0; i < current.getAttachedTo().length; i++) {
                for (int j = 0; j < current.getAttachedTo()[i].getRooms().length; j++) {
                    //if the value is better than the minimum prob so far
                    //and that node has not been visited
                    if((comp_probs[current.getAttachedTo()[i].getRooms()[j].getRoom_id()] + current.getAttachedTo()[i].getEnemy_probability()) > min
                            && !all_rooms[current.getAttachedTo()[i].getRooms()[j].getRoom_id()].getVisited()){

                        min = comp_probs[current.getAttachedTo()[i].getRooms()[j].getRoom_id()] + current.getAttachedTo()[i].getEnemy_probability();
                        next_room_index = current.getAttachedTo()[i].getRooms()[j].getRoom_id();
                    }
                }
            }
            current = all_rooms[next_room_index];
            //System.out.println("Next room is: " + next_room_index);
            //System.out.println("Current updated");
        }

        System.out.println("ending");

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

    private boolean endVisited(){
        return end.getVisited();
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