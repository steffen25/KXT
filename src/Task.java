import java.util.ArrayList;
import java.util.List;

/**
 * Created by danniwu on 11/05/15.
 */
public class Task {
    public String title;

    public String description;

    public String phase;

    public ArrayList responsible;

    public ArrayList  comments;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList getResponsible() {
        return responsible;
    }

    public void setResponsible(ArrayList responsible) {
        this.responsible = responsible;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public ArrayList getComments() {
        return comments;
    }

    public void setComments(ArrayList comments) {
        this.comments = comments;
    }
}
