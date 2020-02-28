import com.google.gson.JsonObject;

public class Room {

    private int id;
    private String room_name;

    public Room(int room_id, String room_name) {
        this.id = room_id;
        this.room_name = room_name;
    }

    public Room(JsonObject json){
        this.id = json.get("id").getAsInt();
        this.room_name = json.get("room_name").getAsString();

    }

    public Room(Room room) {
        this.id = room.getRoom_id();
        this.room_name = room.getRoom_name();
    }

    public Room() {

    }

    public int getRoom_id() {
        return id;
    }

    public String getRoom_name() {
        return room_name;
    }
}
