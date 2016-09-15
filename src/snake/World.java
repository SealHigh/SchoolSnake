package snake;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * The world contains a snake, apples (and later on obstacles). The method move
 * updates the world, i.e. moves the snake one step, checks for apples to eat,
 * whether the snake is out of bounds et c.
 *
 * Created by anderslm@kth.se on 2015-03-10. Based on the Android Open Source
 * Project Snake.
 */
public class World {

    public static final int POINTS_PER_APPLE = 10,
            POINTS_PER_POWERED_APPLE = 20;

    private final int boardWidth, boardHeight;

    private Snake snake;
    private final LinkedList<Apple> apples;

    private final LinkedList<Obstacle> obstacles;
    private State state = State.READY;
    private long score = 0;

    private static final Random RANDOM = new Random();

    /**
     * Creates a new World with the stated dimensions measured in snake
     * segments.
     *
     * @param boardWidth The board width, measured in segments (integer)
     * @param boardHeight The board height, measured in segments (integer)
     */
    public World(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        obstacles = new LinkedList<>();
        apples = new LinkedList<>();
        initNewGame();
    }

    /**
     * Initializes a new game. A new snake trail of five segments and two apples
     * (randomized) are created. Game state is set to READY.
     */
    public final void initNewGame() {
        Point head = new Point(boardWidth / 2, boardHeight / 2);
        snake = new Snake(head, 5);

        apples.clear();
        addApple();
        addApple();

        List<Point> points = new ArrayList<>();
        points.add(new Point(3,3));
        points.add(new Point(3,4));
        points.add(new Point(3,5));
        points.add(new Point(4,4));

        points.add(new Point(10,3));
        points.add(new Point(10,4));
        points.add(new Point(9,4));

        points.add(new Point(13,12));
        points.add(new Point(13,13));
        points.add(new Point(14,12));
        points.add(new Point(14,13));
        obstacles.add(new Obstacle(points));

        score = 0;
        state = State.READY;
    }

    /**
     * Returns a <em>copy</em> of the list representing the snake trail
     * (positions head to tail).
     */
    public List<Point> getSnakeTrail() {
        return (List<Point>) snake.getBody();
    }

    /**
     * Returns a <em>copy</em> of the list of apples.
     */

    public List<Obstacle> getObstacles() {
        return (List<Obstacle>) obstacles.clone();
      }

    public List<Apple> getApples() {
        return (List<Apple>) apples.clone();
    }

    public Direction getDirection() {
        return snake.getDirection();
    }

    public State getState() {
        return state;
    }

    public long getScore() {
        return score;
    }

    public void addScore(int i) {
        score = score+i;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    /**
     * Set the new direction for the snake. A direction anti parallel with the
     * current direction is ignored, to prevent the snake from turning back on
     * itself.
     *
     * @param direction The new direction
     */
    public void setDirection(Direction direction) {
        snake.setDirection(direction);
    }

    /**
     * Set the new state for this snake game: READY, RUNNING, PAUSED or LOSE
     *
     * @param newState The new state.
     */
    public void setState(State newState) {
        if (newState == state) {
            return;
        }

        if (state == State.PAUSED && newState == State.RUNNING) {
            state = State.RUNNING;
        } else if (state == State.READY && newState == State.RUNNING) {
            state = State.RUNNING;

        } else if (state == State.RUNNING && newState == State.GAME_OVER
                || newState == State.PAUSED) {
            state = newState;
        } else if (state != State.RUNNING && newState == State.READY) {
            initNewGame();
            state = State.READY;
        }
    }

    /**
     * Move this snake one step in the current direction. If an apple is found,
     * remove it and update the score, the add a new apple.
     */
    public void move(World world) {
        if (state == State.RUNNING) {
            snake.move();
            Point newHead = snake.getHead();

            if(snake.collisionWithSelf())
                setState(state.GAME_OVER);
            if(snakeIsOutsideBoard(snake.getHead()))
                setState(state.GAME_OVER);
            // TODO: Check whether the snake head is outside
            // the board, by calling snakeIsOutsideBoard and, 
            // if so, call setState to set state GAME_OVER.
            // ...
            // TODO: Check for collision with self by calling 
            // collisionWithSelf and, if so, call setState to 
            // set state GAME_OVER.
            // ...

            for (Obstacle o : obstacles) {

                if (o.intersects(snake.getHead()))
                    setState(state.GAME_OVER);
                }

            for (Apple apple : apples) {
                if (apple.getPosition().equals(newHead)) {
                    apples.remove(apple);
                    snake.eat(apple, world);
                    addApple(); // add a new apple to the board
                    break;
                }
            }
        }
    }

    /**
     * Add a new apple in a randomized position.
     */
    private void addApple() {
        Point pos = null;
        List<Point> tempPoints = new LinkedList<>();
        for (Obstacle o:obstacles) {
            tempPoints.addAll(o.getPoints());
        }
        // Randomize a new position, avoid the snake body
        do {
            pos = new Point(RANDOM.nextInt(boardWidth),
                    RANDOM.nextInt(boardHeight));
            // Make sure we do not select a point under the snake trail or any obstacle
        } while (snake.getBody().contains(pos) || tempPoints.contains(pos));



        Apple a = new Apple(pos, RANDOM.nextBoolean());
        apples.add(a);
    }

    /**
     * Return true if the head of the snake is outside the board, otherwise
     * return false.
     */
    private boolean snakeIsOutsideBoard(Point head) {
       if(head.getX() < 0 || head.getY() < 0 || head.getX() >= boardHeight || head.getY() >= boardWidth  )
           return true;
        return false; // unimplemented
    }
}
