import javafx.application.Application;
import javafx.stage.Stage;

public class Client extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        Window mainWindow = new Window(primaryStage);
        mainWindow.start();
    }

    public static void main(String[] args) { launch(args); }
}