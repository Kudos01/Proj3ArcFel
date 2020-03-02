import com.google.gson.JsonObject;

public class Room {

    private int id;
    private String room_name;
    private Boolean visited;

    public Room(JsonObject json){
        this.id = json.get("id").getAsInt();
        this.room_name = json.get("room_name").getAsString();
        this.visited = null;

    }

    public Room(Room room) {
        this.id = room.getRoom_id();
        this.room_name = room.getRoom_name();
        this.visited = room.getVisited();
    }

    public void setVisitedTrue() {
        this.visited = true;
    }

    public void setVisitedFalse() {
        this.visited = true;
    }

    public Boolean getVisited() { return visited; }

    public int getRoom_id() {
        return id;
    }

    public String getRoom_name() {
        return room_name;
    }
}
