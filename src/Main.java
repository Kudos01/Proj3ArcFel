import java.util.Arrays;

public class Main {

    public static Room[] all_rooms;
    public static Connection[] all_connections;

    public static void main(String[] args) {

        all_rooms = JsonParser.parseRoom();
        all_connections = JsonParser.parseConnection();

        //JsonParser.testParsing(all_rooms, all_connections);


        
    }


}
