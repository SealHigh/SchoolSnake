package snake;

/**
 * An instance of this class represents an apple. An apple might be 
 * powered.
 * 
 * @author Anders Lindström <anderslm@kth.se>
 */
public class Apple {

    private final Point position;
    private boolean Powered;

    public Apple(Point position) {
        this.position = position;
    }

    public Apple(Point position, boolean powered) {
        this.position = position;
        Powered = powered;
    }

    public Point getPosition() {
        return position;
    }

    /**
     * Return whether this apple is powered or not.
     */
    public boolean isPowered() {
        return Powered; // change this implementation
    }
    
    public boolean intersects(Point point) {
        return this.position.equals(point);
    }
}
