package sample;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

//Runnable merge in paralel cu Canvas-ul
public class GameLoop implements Runnable{ //precum controllerul de la API, aici vin toate finalizate
    public final Grid grid;
    private final GraphicsContext context;
    public boolean pause;

    public GameLoop(Grid grid,GraphicsContext context) {
        this.grid = grid;
        this.context = context;
        this.pause = false;
    }

    @Override
    public void run() {

        while (true){
            if(!pause){
                int status = update(); //ofera un status, iar daca da un status de exit, atunci jocul este intrerupt
                if(status == -1){
                    grid.reset();
                }else if(status == 1){
                    context.setFill(Color.BLACK);
                    context.fillRect(0, 0, Play.SCREENWIDTH, Play.SCREENHEIGHT);
                    context.setFill(Color.BLUE);
                    context.setFont(new Font("Impact", 48));
                    context.fillText("You Won", Play.SCREENWIDTH/2 -85, Play.SCREENHEIGHT/2);
                    break;
                }
                render();
            }
            try {
                Thread.sleep((long) (1000 / Play.FRAMERATE)); //asigurarea ca jocul functioneaza cu un framerate constant
            } catch (InterruptedException ignore) {
            }
        }
    }

    public void render(){
        grid.render(context);
    }

    public int update(){
        return grid.update();
    }
}
