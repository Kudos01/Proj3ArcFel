import java.util.HashMap;

public class Dijkstra {

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
        new:= probability current +
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


    private Route dman(Map original, Room start, Room end){
        int prob = 0;

        //routes -> List of the walks to each node from start
        //Route[] routes = new Route[0];
        HashMap<String, Route[]> walk = new HashMap<String, Route[]>();
        int[] probabilities = new int[original.getAll_rooms().length];

        Room current = new Room(start);

        //while there are nodes left to visit and end is not visited do
        while(!allVisited(original) && !endVisited(original, end)){
            for (Room adj: original.getAdjacent(current)) {
                if(!adj.getVisited()){
                    prob += adj.getAttachedTo().getEnemy_probability();
                    //adj.getProbability + original.get
                    //get the new probability of going to that node
                    if(probabilities[adj.getRoom_id()] > prob){
                        probabilities[adj.getRoom_id()] = prob;
                        //update the route
                    }
                }
            }

            current.setVisitedTrue();
            //update route rather than room

            int min = original.getAdjacent(current)[0].getAttachedTo().getEnemy_probability();

            for (int i = 0; i < original.getAdjacent(current).length; i++) {
                if(original.getAdjacent(current)[i].getAttachedTo().getEnemy_probability() < min){
                    min = original.getAdjacent(current)[i].getAttachedTo().getEnemy_probability();
                    current = original.getAdjacent(current)[i];
                }
            }
        }

        return null;
    }


    private boolean allVisited(Map original){
        for (int i = 0; i < original.getAll_rooms().length; i++) {
            if(!original.getAll_rooms()[i].getVisited()){
                return false;
            }
        }
        return true;
    }

    private boolean endVisited(Map original, Room end){

        for (int i = 0; i < original.getAll_rooms().length; i++) {
            if(original.getAll_rooms()[i].getVisited() && original.getAll_rooms()[i].getRoom_id() == end.getRoom_id()){
                return true;
            }
        }
        return false;
    }

}
