import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Subtask> subtasks; // Список подзадач

    public Epic(String title, String description, int id) {
        super(title, description, id);
        this.subtasks = new ArrayList<>(); // Инициализация ArrayList
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks; // Возвращаем ArrayList
    }

    @Override
    public String toString() {
        return "Epic{" +
                "title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", subtasks=" + subtasks +
                '}';
    }
}