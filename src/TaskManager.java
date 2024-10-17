import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Task> tasks; // Хранит все задачи
    private HashMap<Integer, Epic> epics; // Хранит все эпики
    private ArrayList<Subtask> subtasks; // Список подзадач

    public TaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void addEpic(Epic epic) {
        epics.put(epic.getId(), epic);
    }

    public void addSubtask(int epicId, Subtask subtask) {
        Epic epic = epics.get(epicId);
        if (epic != null) {
            epic.addSubtask(subtask);
            subtasks.add(subtask); // Добавляем подзадачу в общий список подзадач
            addTask(subtask); // Добавляем подзадачу в общий список задач
        }
    }

    public Task getTask(int id) {
        System.out.println(tasks.get(id));
        return tasks.get(id);
    }

    public Epic getEpic(int id) {
        System.out.println(epics.get(id));
        return epics.get(id);
    }

    public ArrayList<Task> getAllTasks() {
        System.out.println(tasks.values());
        return new ArrayList<>(tasks.values()); // Возвращаем все задачи
    }

    public ArrayList<Epic> getAllEpics() {
        System.out.println(epics.values());
        return new ArrayList<>(epics.values()); // Возвращаем все эпики
    }

    public ArrayList<Subtask> getAllSubtasks() {
        return subtasks; // Возвращаем список подзадач
    }

    @Override
    public String toString() {
        return "TaskManager{" +
                "tasks=" + tasks +
                ", epics=" + epics +
                ", subtasks=" + subtasks +
                '}';
    }
}