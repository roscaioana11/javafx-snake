package sample;
import javafx.scene.canvas.GraphicsContext;

//Runnable merge in paralel cu Canvas-ul
public class GameLoop implements Runnable{ //precum controllerul de la API, aici vin toate finalizate
    public final Grid grid;
    private final GraphicsContext context;
    private static final int FRAMERATE = 10;

    public GameLoop(Grid grid,GraphicsContext context) {
        this.grid = grid;
        this.context = context;
    }

    @Override
    public void run() {

        while (true){
            int status = update(); //ofera un status, iar daca da un status de exit, atunci jocul este intrerupt
            if(status == -1){
                grid.reset();
            }
            render();
            try {
                Thread.sleep((long) (1000 / FRAMERATE)); //asigurarea ca jocul functioneaza cu un framerate constant
            } catch (InterruptedException ignore) {

            }
            System.out.println("loop ended");
        }
    }

    public void render(){
        grid.render(context);
    }

    public int update(){
        return grid.update();
    }
}
