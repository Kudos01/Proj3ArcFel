public class Map {
    //map should contain all rooms and connections
    private Room[] all_rooms;
    private Connection[] all_connections;

    public Map(Room[] all_rooms, Connection[] all_connections) {
        this.all_rooms = all_rooms;
        this.all_connections = all_connections;
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
