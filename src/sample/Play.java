package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Random;

public class Play extends Application {
    public static final int SCREENWIDTH = 800;
    public static final int SCREENHEIGHT = 800;
    public static final int COLUMNNUMBER = 25;

    private GameLoop loop;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("SNAKE");
        StackPane root = new StackPane(); //creaza un screen pe care apoi poti atasa un canvas
        Canvas canvas = new Canvas(SCREENWIDTH, SCREENHEIGHT); //creaza o zona unde am control asupra pixelilor
        GraphicsContext context = canvas.getGraphicsContext2D(); //metoda folosita pt manipulare a pixelilor
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(e -> {
            Snake snake = loop.grid.snake;

            //TODO snake shouldn't be able to move the complete opposite of its current direction
            //TODO pause
            switch (e.getCode()) {
                case UP:
                    snake.direction = "UP";
                    break;
                case DOWN:
                    snake.direction = "DOWN";
                    break;
                case LEFT:
                    snake.direction = "LEFT";
                    break;
                case RIGHT:
                    snake.direction = "RIGHT";
                    break;
//                case ENTER:
//                    if (loop.isPaused()) {
//                        reset();
//                        (new Thread(loop)).start();
//                    }
            }
        });
        Random random = new Random();
        loop = new GameLoop(new Grid(), context);
//        loop.grid.snake.snakeBody.add(new Point(
//                loop.grid.snake.snakeBody.get(0).getX(),
//                loop.grid.snake.snakeBody.get(0).getY() - 1
//        ));
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root, SCREENWIDTH, SCREENHEIGHT));
        primaryStage.show();

        (new Thread(loop)).start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
