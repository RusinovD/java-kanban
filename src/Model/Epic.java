package Model;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Subtask> subtasks; // Список подзадач

    public Epic(String title, String description, int id) {
        super(title, description, id);
        this.subtasks = new ArrayList<>(); // Инициализация ArrayList
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