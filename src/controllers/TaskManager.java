package controllers;

import Model.Epic;
import Model.Subtask;
import Model.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    void addTask(Task task);

    void addEpic(Epic epic);

    Integer addSubtask(Subtask subtask);

    ArrayList<Task> getTasks();

    ArrayList<Subtask> getSubtasks();

    ArrayList<Epic> getEpics();

    ArrayList<Subtask> getSubtasksOfEpic(Integer epicId);

    // удаление одной из задач
    void removeTask(int id);

    // удаление всех задач определенного типа
    void deleteTasks();

    void deleteSubtasks();

    void deleteEpics();

    void updateTask(Task task);

    List<Task> getHistory();

    Task getTask(int id);

    Epic getEpic(int id);

    Subtask getSubtask(int id);

    @Override
    String toString();
}
