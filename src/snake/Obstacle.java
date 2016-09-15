package snake;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Martin on 2016-09-11.
 */
public class Obstacle {
    private final List<Point> obstacle;

    public Obstacle(List<Point> tempList){
        obstacle = tempList;
    }

    public List<Point> getPoints(){
        return obstacle;
    }

    public boolean intersects(Point p){
        for (Point point: obstacle
             ) {
            if(point.equals(p))
                return true;
        }
        return false;
    }


}
