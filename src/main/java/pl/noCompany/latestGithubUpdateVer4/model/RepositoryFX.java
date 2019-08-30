package pl.noCompany.latestGithubUpdateVer4.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RepositoryFX {

    private StringProperty name;
    private StringProperty time;

    public RepositoryFX(String name, String time){
        this.name = new SimpleStringProperty(name);
        this.time = new SimpleStringProperty(time);
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public StringProperty getTimeProperty() {
        return time;
    }

    @Override
    public String toString() {
        return "Repository{name=          " + name.get() +
                "\n   time=                  " + time.get() +
                "  }";
    }
}