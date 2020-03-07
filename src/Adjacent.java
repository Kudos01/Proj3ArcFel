public class Adjacent {

    private Room room;
    private Connection connection;

    public Adjacent(Room room, Connection connection) {
        this.room = room;
        this.connection = connection;
    }

    public Adjacent(int i, Room[] all_rooms, Connection connection) {
        this.room = getRoomById(i, all_rooms);
        this.connection = connection;
    }

    private Room getRoomById(int id, Room[] all_rooms){

        for (int i = 0; i < all_rooms.length; i++) {
            if(id ==  all_rooms[i].getRoom_id() ){
                return  all_rooms[i];
            }
        }
        return null;
    }

    public Room getRoom() {
        return room;
    }

    public Connection getConnection() {
        return connection;
    }

}
