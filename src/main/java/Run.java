
import Controller.AppController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Run extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Web Crawler");
        new AppController(primaryStage).initRootLayout();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
