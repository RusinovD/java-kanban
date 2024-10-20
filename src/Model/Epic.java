package Model;
import java.util.ArrayList;

public class Epic extends Task {
    public ArrayList<Task> subtasksId = new ArrayList<>(); // Список ID подзадач

    public Epic(int id, String title, String description, Status status) {
        super(id, title, description, status);
        this.subtasksId = subtasksId;
    }

    public ArrayList<Task> getSubtasksId() {
        return subtasksId;
    }

    public void setSubtasksId(ArrayList<Task> subtasksId) {
        this.subtasksId = subtasksId;
    }

        @Override
    public String toString() {
        return "Epic{" +
                "title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", subtasksId=" + subtasksId +
                '}';
    }

    }
