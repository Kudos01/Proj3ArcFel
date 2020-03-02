import java.util.Arrays;

public class Map {
    //map should contain all rooms and connections
    private Room[] all_rooms;
    private Connection[] all_connections;
    int adjacency_matrix [][];

    public Map() {
        this.all_rooms = JsonParser.parseRoom();
        this.all_connections = JsonParser.parseConnection();
        //JsonParser.testParsing(all_rooms,all_connections);
        this.adjacency_matrix = new int [all_rooms.length][all_rooms.length];

        for (int i = 0; i < all_rooms.length; i++) {
            for (int j = 0; j < all_rooms.length; j++) {
                adjacency_matrix[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < all_connections.length ; i++) {
            for (int j = 0; j < all_connections[i].getConnected_rooms().length;j++) {
                if( all_connections[i].getConnected_rooms().length > 2){
                    adjacency_matrix[all_connections[i].getConnected_rooms()[0]][all_connections[i].getConnected_rooms()[1]] =
                }

            }
        }
    }

    public Room[] getAll_rooms() {
        return all_rooms;
    }


    public Connection[] getAll_connections() {
        return all_connections;
    }

    public Room[] getAdjacent(Room given){

       // return adjacents;
        return null;

    }

}
