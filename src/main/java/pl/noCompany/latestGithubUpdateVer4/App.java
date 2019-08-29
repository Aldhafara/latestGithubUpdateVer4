package pl.noCompany.latestGithubUpdateVer4;


import javafx.application.Application;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.noCompany.latestGithubUpdateVer4.logic.Logic;
import pl.noCompany.latestGithubUpdateVer4.model.Repository;


import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    private Stage stage;
    private VBox layout;

    public static void main(String[] args) {
        System.out.println("Hello Word!");

        List<Repository> list = new ArrayList<>();
        Repository zeroElement = new Repository();
        zeroElement.setName("Brak danych");
        zeroElement.setTime(null);
        list.add(zeroElement);

        String name = "Aldhafara";

        Logic.connection(name, list);


    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.stage.setTitle("Latest Github Update ver4.0");

    }

}
