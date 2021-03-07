package sample;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    public List<Point> snakeBody;
    public String direction;

    public Snake(Point startingPoint, String direction) {
        this.snakeBody = new ArrayList<>();
        this.snakeBody.add(startingPoint);
        this.direction = direction;
    }
}
