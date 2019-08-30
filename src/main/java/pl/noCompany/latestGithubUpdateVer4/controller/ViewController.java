package pl.noCompany.latestGithubUpdateVer4.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pl.noCompany.latestGithubUpdateVer4.App;
import pl.noCompany.latestGithubUpdateVer4.model.DateAndTimeFormatter;
import pl.noCompany.latestGithubUpdateVer4.model.Repository;

import java.util.List;


public class ViewController {

    private App main;
    private List<Repository> values;

    @FXML
    private Label timeArea;
    @FXML
    public TextField userField;
    @FXML
    private Label repositoryArea;
    @FXML
    private Pane SearchingPane;
    @FXML
    private Button moreButton;


    @FXML
    public void handleOk() throws Exception {

        repositoryArea.setText(" ");
        timeArea.setText(" ");
        moreButton.setVisible(false);

        final SearchingTask searchingTask = new SearchingTask();
        searchingTask.setLogin(userField.getText());

        //Here you tell your progress indicator is visible only when the service is runing
        SearchingPane.visibleProperty().bind(searchingTask.runningProperty());
        searchingTask.setOnSucceeded(workerStateEvent -> {
            values = searchingTask.getValues();   //here you get the return value of your service
            try {
                search();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        searchingTask.setOnFailed(workerStateEvent -> {
            //DO stuff on failed
            System.out.println("FAILED");
        });
        searchingTask.restart(); //here you start your service

    }

    @FXML
    public void handleMoreButton() {
        this.main.loadTableView();
    }

    @FXML
    public void initialize(){
        SearchingPane.setVisible(true);
        repositoryArea.setText(" ");
        timeArea.setText(" ");

        userField.setText("podaj login");
        moreButton.setVisible(false);
        SearchingPane.setVisible(false);
    }

    private void search() {

        if (values.get(0).getName() == null)
        {
            repositoryArea.setText(values.get(1).getName());
            timeArea.setText(values.get(1).getTime().format(DateAndTimeFormatter.getFormatter()));
            if(values.size()>2){moreButton.setVisible(true);}
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            SearchingPane.visibleProperty().unbind();
            SearchingPane.setVisible(false);
            alert.setTitle("Nie znaleziono repozytorium");
            alert.setHeaderText(null);
            alert.setContentText(values.get(0).getName());
            alert.showAndWait();
        }
    }

    public ViewController() {
    }


    public void setMain(App main) {
        this.main=main;
    }

}