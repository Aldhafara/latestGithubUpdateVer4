package pl.noCompany.latestGithubUpdateVer4.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;

public class RepositoryFX {

    private StringProperty name;
    private ObjectProperty<LocalDateTime> time;

    public RepositoryFX(){
        this(null,null);
    }

    public RepositoryFX(String name, String time){
        this.name = new SimpleStringProperty(name);
        this.time = new SimpleObjectProperty(time);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public LocalDateTime getTime() {
        return time.get();
    }

    public ObjectProperty<LocalDateTime> getTimeProperty() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time.set(time);
    }

    @Override
    public String toString() {
        return "Repository{name=          " + name.get() +
                "\n   time=                  " + time.get() +
                "  }";
    }
}
