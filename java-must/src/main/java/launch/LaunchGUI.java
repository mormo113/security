package launch;

import gui.GUIController;
import jobs.sonar.JobLogger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tools.LoggerScren;

public class LaunchGUI extends Application {

    private static LoggerScren LOGGER = new LoggerScren(JobLogger.class.getName());

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("guilaunch.fxml"));
        Parent root = loader.load();
        GUIController controller= (GUIController) loader.getController();
        controller.init(primaryStage);
        System.out.println(primaryStage.getWidth() + " ///  " + primaryStage.getHeight());
        primaryStage.setTitle("JAVA-MUST");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
