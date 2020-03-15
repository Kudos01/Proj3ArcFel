import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;


public class JsonParser {

    private static String FILEPATH_CONNECTION = "resources/ConnectionM.json";
    private static String FILEPATH_ROOM = "resources/RoomM.json";

    public static Room[] parseRoom(){

        Gson gson = new Gson();

        Room[] room = null;

        try (Reader reader = new FileReader(FILEPATH_ROOM)) {

            JsonArray j = gson.fromJson(reader, JsonArray.class);

            room = new Room[j.size()];

            for (int i = 0; i < j.size(); i++) {
                room[i] = new Room(j.get(i).getAsJsonObject());
            }

        }catch (IOException e){
            e.printStackTrace();
        }
            return room;
    }

    public static Connection[] parseConnection(){

        Gson gson = new Gson();

        Connection[] connections = null;

        try (Reader reader = new FileReader(FILEPATH_CONNECTION)) {

            JsonArray j = gson.fromJson(reader, JsonArray.class);

            connections = new Connection[j.size()];

            for (int i = 0; i < j.size(); i++) {
                connections[i] = new Connection(j.get(i).getAsJsonObject());
            }

        }catch (IOException e){
            e.printStackTrace();
        }
            return connections;

    }
}
