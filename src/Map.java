public class Map {
    //map should contain all rooms and connections
    private Room[] all_rooms;
    private boolean [] visited_rooms;
    private Connection[] all_connections;

    public Map(Room[] all_rooms, boolean[] visited_rooms, Connection[] all_connections) {
        this.all_rooms = all_rooms;
        this.visited_rooms = visited_rooms;
        this.all_connections = all_connections;
    }

    public Room[] getAll_rooms() {
        return all_rooms;
    }

    public boolean[] getVisited_rooms() {
        return visited_rooms;
    }

    public Connection[] getAll_connections() {
        return all_connections;
    }
}
