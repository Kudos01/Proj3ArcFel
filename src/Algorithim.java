public class Algorithim {

    private Route dman(Map original, Room start, Room end){
        int tmp = 0;

        //routes -> List of the walks to each node from start
        Route[] routes = new Route[0];
        float[] probabilities = new float[tmp];

        Room current = new Room(start);

        return null;
    }

    /*
    private void uh(Map original, Room start, Room end){
        boolean f = true;
        //while there are nodes left to visit and end is not visited do
        while(allVisited(original) && !endVisited(original, end)){
            //TODO: function that gets adjacents to room by id
            while(f=true){
                //for each adjacent node
                for (:
                 ) {

                }
                f=false;
            }
        }
    }

     */

    private boolean allVisited(Map original){
        for (int i = 0; i < original.getVisited_rooms().length; i++) {
            if(!original.getVisited_rooms()[i]){
                return false;
            }
        }
        return true;
    }
    
    private boolean endVisited(Map original, Room end){

        for (int i = 0; i < original.getVisited_rooms().length ; i++) {
            if(original.getVisited_rooms()[i] && original.getAll_rooms()[i].getRoom_id() == end.getRoom_id()){
                return true;
            }
        }
        return false;
    }

    private Room getRoomById(Map original, int id){

        for (int i = 0; i < original.getAll_rooms().length ; i++) {
            if(id == original.getAll_rooms()[i].getRoom_id() ){
                return original.getAll_rooms()[i];
            }
        }
        return null;
    }
}

/*
function dijkstra (g: Graph, start: Node, end: Node) returns Walk
        walks: List of the walks to each node from start
        d: Array to store the distances from the start node to each node
// We initialize d to 0 for the start node and infinity for the rest
        current := start
        while there are nodes left to visit and end is not visited do
            for each adj in g.adjacents(current) do
            if ¬adj.visited then
        new := d[current] + g.edge(current, adj).weight()
        if d[adj] > new then
        d[adj] := new
                walks.update(adj, current)
        end
                end
        end
        current.visited := TRUE
        current := minimum not visited value from d
        end
        return walks.get(end)
        end

 */