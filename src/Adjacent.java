public class Adjacent {

    private Room room;
    private Connection connection;

    public Adjacent(Room room, Connection connection) {
        this.room = room;
        this.connection = connection;
    }

    public Room getRoom() {
        return room;
    }

    public Connection getConnection() {
        return connection;
    }

}
