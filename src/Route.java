public class Route {

    //idea 1 -> two matrixes that show the final result
    //filled in the end by a two hash maps
    //extra memory
    //very work-around-y
    //idea 2-> hash map with a new class that's Room+Connection
    //makes routes easier
    //extra class
    //public Room[] taken_rooms;
    //public Connection[] connections_to_rooms;
    private Room current_node;
    private Connection connection_next_best_path;
    private double probability_so_far;
    // or probability -> then use marking to determine probability or w/e
    //or total probability so far?


    public Room getCurrent_node() {
        return current_node;
    }

    public Connection getConnection_next_best_path() {
        return connection_next_best_path;
    }

    public Route(Room current_node, Connection connection_next_best_path) {
        this.current_node = current_node;
        this.connection_next_best_path = connection_next_best_path;
        //function to get probability so far
        //this.probability_so_far = probability_so_far;
    }
}
