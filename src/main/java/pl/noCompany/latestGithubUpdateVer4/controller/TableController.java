package pl.noCompany.latestGithubUpdateVer4.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.noCompany.latestGithubUpdateVer4.App;
import pl.noCompany.latestGithubUpdateVer4.model.RepositoryFX;


public class TableController{

    private ObservableList<RepositoryFX> repositoryFXList = SearchingTask.getLogic().createFXList();

    @FXML
    private TableView<RepositoryFX> repositoryTable;

    @FXML
    private TableColumn<RepositoryFX, String> nameCol;

    @FXML
    private TableColumn<RepositoryFX, String> timeCol;

    @FXML
    private Button button1;
    @FXML
    private Button button2;



    @FXML
    public void initialize() {

        button1.setVisible(false);
        button2.setVisible(false);


        nameCol.setCellValueFactory(cell -> cell.getValue().getNameProperty());
        timeCol.setCellValueFactory(cell -> cell.getValue().getTimeProperty());
        timeCol.setStyle("-fx-alignment: CENTER; ");

    }

    public void setStage(Stage stage) {
    }

    public void setMain(App main) {
        repositoryTable.setItems(repositoryFXList);
    }

    public void setRepositoryFXList(ObservableList<RepositoryFX> FXList) {
        this.repositoryFXList= FXList;
    }

}
