import java.util.ArrayList;

/**
 * Created by danniwu on 11/05/15.
 */
public class Project {
    public String name;
    public int status;
    public ArrayList phaes;
    public ArrayList tasks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList getPhaes() {
        return phaes;
    }

    public void setPhaes(ArrayList phaes) {
        this.phaes = phaes;
    }

    public ArrayList getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList tasks) {
        this.tasks = tasks;
    }
}
