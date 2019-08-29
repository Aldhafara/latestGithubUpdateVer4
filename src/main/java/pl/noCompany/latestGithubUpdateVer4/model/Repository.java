package pl.noCompany.latestGithubUpdateVer4.model;

import java.time.LocalDateTime;

public class Repository implements Comparable<Repository> {

    private String name;
    private LocalDateTime time;


    public Repository(){
        this(null,null);
    }

    public Repository(String name, LocalDateTime time){
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Repository{name=          " + name +
                "\n   time=                  " + time +
                "  }";
    }

    @Override
    public int compareTo(Repository rep) {
        return this.time.compareTo(rep.time);
    }
}
