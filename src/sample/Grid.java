package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static sample.Play.COLUMNNUMBER;

public class Grid {
    public Point[][] mapGrid;
    public Snake snake;
    public Food food;

    public Grid() {
        this.mapGrid = new Point[COLUMNNUMBER][COLUMNNUMBER];
        for(int i = 0; i < COLUMNNUMBER; i++){
            for (int j = 0; j < COLUMNNUMBER; j++){
                mapGrid[i][j] = new Point(i, j);
            }
        }
        reset();
    }

    public void render(GraphicsContext context){
        for(int i = 0; i < COLUMNNUMBER; i++){
            for (int j = 0; j < COLUMNNUMBER; j++){
                mapGrid[i][j].render(context, Color.BLACK);
            }
        }
        food.render(context, Color.GOLDENROD);
        snake.render(context, Color.FORESTGREEN);
    }

    public int update(){
        int status = snake.update(food);
        if(status == 1){
            if(Play.FRAMERATE < COLUMNNUMBER){
                Play.FRAMERATE++;
            }
            List<Point> unoccupiedPoints = new ArrayList<>();
            for(int i = 0; i < COLUMNNUMBER; i++){
                for (int j = 0; j < COLUMNNUMBER; j++){
                    boolean intersects = false;
                    for(int k = 0; k < snake.snakeBody.size(); k++){
                        if((mapGrid[i][j].getX() == snake.snakeBody.get(k).getX() &&
                        mapGrid[i][j].getY() == snake.snakeBody.get(k).getY())){
                            intersects = true;
                        }
                    }
                    if(!intersects){
                        unoccupiedPoints.add(mapGrid[i][j]);
                    }
                }
            }
            if(unoccupiedPoints.size() == 0){ //conditia de victorie
                return 1;
            }
            Random random = new Random();
            int randomPointIndex = random.nextInt(unoccupiedPoints.size());
            food.changeFoodLocation(unoccupiedPoints.get(randomPointIndex).getX(),
                    unoccupiedPoints.get(randomPointIndex).getY());
        }else if(status == -1){
            return -1;
        }
        return 0;
    }

    public void reset(){
        Play.FRAMERATE = 5;
        Random random = new Random();
        int snakeX = random.nextInt(COLUMNNUMBER);
        int snakeY = random.nextInt(COLUMNNUMBER);
        int foodX = snakeX;
        int foodY = snakeY;

        while(snakeX == foodX && snakeY == foodY){
            foodX = random.nextInt(COLUMNNUMBER);
            foodY = random.nextInt(COLUMNNUMBER);
        }
        snake = new Snake(new Point(snakeX, snakeY), "UP");
        food = new Food(new Point(foodX, foodY));
    }
}
