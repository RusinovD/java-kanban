package Model;

public class Subtask extends Task {
    private int epicId; // Идентификатор эпика, к которому относится подзадача

    public Subtask(int id, String title, String description, Status status, int epicId) {
        super(id, title, description, status);
        this.epicId = epicId;
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

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int newId) {
        super.setId(newId);
    }
}
