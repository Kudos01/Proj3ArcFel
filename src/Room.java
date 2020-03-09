import com.google.gson.JsonObject;

public class Room {

    private int id;
    private String room_name;
    private boolean visited;
    private Adjacent[] attachedTo;


    public void setAttachedTo(Adjacent[] attachedTo) {
        this.attachedTo = attachedTo;
    }

    public Room(JsonObject json){
        this.id = json.get("id").getAsInt();
        this.room_name = json.get("room_name").getAsString();
        this.visited = false;
        this.attachedTo = null;
    }

    public Room(Room room) {
        this.id = room.getRoom_id();
        this.room_name = room.getRoom_name();
        this.visited = room.getVisited();
        this.attachedTo = room.getAttachedTo();
    }

    public void setVisitedTrue() {
        this.visited = true;
    }

    public void setVisitedFalse() {
        this.visited = false;
    }

    public boolean getVisited() { return visited; }

    public int getRoom_id() {
        return id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public Adjacent[] getAttachedTo() {
        return attachedTo;
    }
}
