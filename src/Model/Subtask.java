package Model;

public class Subtask extends Task {
    private int epicId; // Идентификатор эпика, к которому относится подзадача

    public Subtask(String title, String description, int id) {
        super(title, description, id);
        this.setStatus(Status.NEW);

    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", status=" + getStatus() +
                ", epicId=" + epicId +
                '}';
    }
}