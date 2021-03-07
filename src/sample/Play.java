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
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(SCREENWIDTH, SCREENHEIGHT);
        GraphicsContext context = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        Random random = new Random();
        loop = new GameLoop(new Grid(
                new Snake(
                        new Point(random.nextInt(COLUMNNUMBER),random.nextInt(COLUMNNUMBER)), "UP"),
                new Food(new Point(random.nextInt(COLUMNNUMBER),random.nextInt(COLUMNNUMBER)))), context);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root, SCREENWIDTH, SCREENHEIGHT));
        primaryStage.show();

        (new Thread(loop)).start();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
