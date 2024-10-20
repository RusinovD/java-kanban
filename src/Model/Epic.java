package Model;
import java.util.ArrayList;

public class Epic extends Task {
    public ArrayList<Integer> subtasksId = new ArrayList<>(); // Список ID подзадач


    public Epic(int id, String title, String description, Status status) {
        super(id, title, description, status);
        this.subtasksId = subtasksId;
    }

    public ArrayList<Integer> getSubtasksId() {
        return subtasksId;
    }

    public void setSubtasksId(ArrayList<Integer> subtasksId) {
        this.subtasksId = subtasksId;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int newId) {
        super.setId(newId);
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
