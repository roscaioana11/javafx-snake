package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    public List<Point> snakeBody;
    public String direction;
    public boolean moved;

    public Snake(Point startingPoint, String direction) {
        this.snakeBody = new ArrayList<>();
        this.snakeBody.add(startingPoint);
        this.direction = direction;
        this.moved = false;
    }

    public void changeDirection(String newDirection){
        if(!moved && (newDirection == "UP" && direction != "DOWN") ||
                (newDirection == "DOWN" && direction != "UP") ||
                (newDirection == "LEFT" && direction != "RIGHT") ||
                (newDirection == "RIGHT" && direction != "LEFT")){
            direction = newDirection;
            moved = true;
        }
    }

    public void render(GraphicsContext context,Color color){
        for(int i = 0; i < snakeBody.size(); i++){
            snakeBody.get(i).render(context, color);
        }
    }

    public int update(Food food){
        moved = false;
        Point lastPosition = new Point(snakeBody.get(0).getX(), snakeBody.get(0).getY());
        if(direction == "UP"){
            snakeBody.get(0).setY(snakeBody.get(0).getY() - 1);
            if (snakeBody.get(0).getY() < 0){
                snakeBody.get(0).setY(Play.COLUMNNUMBER - 1);
            }
        }else if(direction == "DOWN"){
            snakeBody.get(0).setY(snakeBody.get(0).getY() + 1);
            if (snakeBody.get(0).getY() > Play.COLUMNNUMBER - 1){
                snakeBody.get(0).setY(0);
            }
        }else if(direction == "RIGHT"){
            snakeBody.get(0).setX(snakeBody.get(0).getX() + 1);
            if(snakeBody.get(0).getX() > Play.COLUMNNUMBER -1){
                snakeBody.get(0).setX(0);
            }
        }else if(direction == "LEFT"){
            snakeBody.get(0).setX(snakeBody.get(0).getX() - 1);
            if(snakeBody.get(0).getX() < 0){
                snakeBody.get(0).setX(Play.COLUMNNUMBER - 1);
            }
        }
        for(int i = 1; i < snakeBody.size(); i++){
            if(snakeBody.get(i).getX() == snakeBody.get(0).getX() &&
                    snakeBody.get(i).getY() == snakeBody.get(0).getY()){
                return -1;
            }
            Point auxPoint = new Point(snakeBody.get(i).getX(), snakeBody.get(i).getY());
            snakeBody.get(i).setX(lastPosition.getX());
            snakeBody.get(i).setY(lastPosition.getY());
            lastPosition.setX(auxPoint.getX());
            lastPosition.setY(auxPoint.getY());
        }
        if(food.foodPosition.getX() == snakeBody.get(0).getX() &&
                food.foodPosition.getY() == snakeBody.get(0).getY()){
            snakeBody.add(lastPosition);
            return 1;
        }
        return 0;
    }
}
