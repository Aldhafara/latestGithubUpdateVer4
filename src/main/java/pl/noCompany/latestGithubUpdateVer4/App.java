package pl.noCompany.latestGithubUpdateVer4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.noCompany.latestGithubUpdateVer4.controller.TableController;
import pl.noCompany.latestGithubUpdateVer4.controller.ViewController;

import java.io.IOException;

public class App extends Application {
    private Stage stage;


    public static void main(String[] args) {

        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.stage.setTitle("Latest Github Update ver4.0");
        loadView();
    }

    private void loadView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/Box.fxml"));

            VBox layout = (VBox) loader.load();

            Scene scene = new Scene(layout);
            stage.getIcons().add(new Image(App.class.getResourceAsStream("/icon.png")));
            stage.setScene(scene);
            stage.show();

            ViewController controller = loader.getController();
            controller.setMain(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTableView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/Table.fxml"));
            VBox window;

            window = loader.load();

            TableController tableController = loader.getController();
            tableController.setMain(this);

            Stage newTableStage = new Stage();
            tableController.setStage(newTableStage);
            newTableStage.getIcons().add(new Image(App.class.getResourceAsStream("/icon.png")));

            newTableStage.setTitle("Pozostałe repozytoria");
            Scene scene = new Scene(window);
            newTableStage.setScene(scene);
            newTableStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
