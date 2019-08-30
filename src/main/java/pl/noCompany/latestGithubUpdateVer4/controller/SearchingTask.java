package pl.noCompany.latestGithubUpdateVer4.controller;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import pl.noCompany.latestGithubUpdateVer4.logic.Logic;
import pl.noCompany.latestGithubUpdateVer4.model.Repository;

import java.util.List;

public class SearchingTask extends Service<List<Repository>> {

    private static Logic logic;

    private String getLogin() {
        return login;
    }


    void setLogin(String login) {
        this.login = login;
    }

    List<Repository> getValues() {
        return values;
    }

    private void setValues(List<Repository> values) {
        this.values = values;
    }

    private String login;
    private List<Repository> values;

    @Override
    protected Task<List<Repository>> createTask() {
        return new Task<List<Repository>>() {
            @Override
            protected List<Repository> call() throws Exception {
                logic = new Logic();
                setValues(logic.getNewList(getLogin()));
                return getValues();
            }
        };
    }

    public static Logic getLogic() {
        return logic;
    }
}
