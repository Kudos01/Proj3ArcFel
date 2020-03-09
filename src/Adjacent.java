public class Adjacent {

    private Room[] rooms;
    private int enemy_probability;

    public Adjacent(Room[] rooms, int enemy_probability) {
        this.rooms = rooms;
        this.enemy_probability = enemy_probability;
    }

    public void setRoomsBasedOnIDs(int[] ids, Room[] rooms, int exclude_id) {

        int counter = 0;

        for (int i = 0; i < ids.length; i++) {
            if(ids[i] != exclude_id){
                this.rooms[counter] = rooms[ids[i]];
                counter++;
            }
        }

    }

    public void setEnemy_probability(int enemy_probability) {
        this.enemy_probability = enemy_probability;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public int getEnemy_probability() {
        return enemy_probability;
    }
}
