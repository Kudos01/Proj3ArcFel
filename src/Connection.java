import com.google.gson.JsonObject;

public class Connection {

    private int id;
    private String connection_name;
    private int[] room_connected;
    private int enemy_probability;

    public Connection(int connection_id, String connection_name, int[] connected_rooms, int enemy_probability) {
        this.id = connection_id;
        this.connection_name = connection_name;
        this.room_connected = connected_rooms;
        this.enemy_probability = enemy_probability;
    }

    public Connection(JsonObject json){
        this.id = json.get("id").getAsInt();
        this.connection_name = json.get("connection_name").getAsString();
        this.room_connected = new int[json.get("room_connected").getAsJsonArray().size()];
        for (int i = 0; i < json.get("room_connected").getAsJsonArray().size(); i++) {
            this.room_connected[i] = json.get("room_connected").getAsJsonArray().get(i).getAsInt();
        }
        this.enemy_probability = json.get("enemy_probability").getAsInt();

    }

    public Connection() {

    }

    public int getConnection_id() {
        return id;
    }

    public String getConnection_name() {
        return connection_name;
    }

    public int[] getConnected_rooms() {
        return room_connected;
    }

    public int getEnemy_probability() {
        return enemy_probability;
    }
}
