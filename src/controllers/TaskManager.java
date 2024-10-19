package controllers;

import Model.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Task> tasks; // Хранит все задачи
    private HashMap<Integer, Epic> epics; // Хранит все эпики
    private HashMap<Integer, Subtask> subtasks; // Список подзадач
    private int generatorId = 0; // хранит уникальный ID

    public TaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
    }

    public int addTask(Task task) {
        final int id = ++generatorId;
        task.setId(id);
        tasks.put(id, task);
        return id;
    }

    public int addEpic(Epic epic) {
        final int epicId = ++generatorId;
        epic.setId(epicId);
        epics.put(epicId, epic);
        return epicId;
    }

    public int addSubtask(Epic epicId, Subtask subtask) {
        final int id = ++generatorId;
        Epic epic = epics.get(epicId);
        if (epic != null) {
            subtasks = new HashMap<>();
            subtasks.put(id, subtask); // Добавляем подзадачу в список подзадач эпика
        }
        return id;
    }

    public HashMap<Integer, Task> getAllTasks() {
        return (HashMap<Integer, Task>) tasks.values(); // Возвращаем все задачи
    }

    public HashMap<Integer, Epic> getAllEpics() {
        return (HashMap<Integer, Epic>) (epics.values()); // Возвращаем все эпики
    }

    public HashMap<Integer, Subtask> getAllSubtasks() {
        return (HashMap<Integer, Subtask>) (subtasks.values()); // Возвращаем список подзадач
    }

    public void getSubtasksOfEpic (){

    }



    public void removeTask(int id) { // удаление одной из задач
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        } else if (epics.containsKey(id)) {
            epics.remove(id);
        } else if (subtasks.containsKey(id)) {
            subtasks.remove(id);
        } else
            System.out.println("Задачи с таким ID не существует.");

    }

    public void removeAllTask() { //удаление всех задач определенного типа
        tasks.clear();
        epics.clear();
        subtasks.clear();
    }

    public void updateTask(Task task) {
        // Проверяем, существует ли задача с таким ID
        if (!tasks.containsKey(task.getId())) {
            System.out.println("Задача с таким ID не найдена");
        }

        // Обновляем задачу в HashMap
        tasks.put(task.getId(), task);

        // Если задача является эпиком, обновляем статус эпика
        if (task instanceof Epic) {
            Epic epic = (Epic) task;

            // Получаем все подзадачи, связанные с эпиком
            ArrayList<Subtask> subtasks = new ArrayList<>();
            subtasks = getSubTasksByEpicId(epic.getId());

            // Обновляем статус эпика в зависимости от статусов подзадач
            updateEpicStatus(epic, subtasks);
        }
    }

    // Метод для получения подзадач по ID эпика
    private ArrayList<Subtask> getSubTasksByEpicId(int epicId) {
        ArrayList<Subtask> subtasksList = new ArrayList<>(); //новый лист для хранения списка подзадач эпика
        for (Task task : subtasks.values()) {
            if (task instanceof Subtask && ((Subtask) task).getEpicId() == epicId) {
                subtasksList.add((Subtask) task);
            }
        }
        return subtasksList;
    }

    // Метод для обновления статуса эпика в зависимости от статусов подзадач
    private void updateEpicStatus(Epic epic, ArrayList<Subtask> subtasks) {
        boolean hasNew = false;
        boolean hasInProgress = false;
        boolean hasDone = true;

        for (Task subtask : subtasks) {
            if (subtask.getStatus() == Status.NEW) {
                hasNew = true;
            } else if (subtask.getStatus() == Status.IN_PROGRESS) {
                hasInProgress = true;
            } else if (subtask.getStatus() == Status.DONE) {
                // ничего не делаем, просто продолжаем
            }
        }

        if (hasInProgress) {
            epic.setStatus(Status.IN_PROGRESS);
        } else if (hasNew) {
            epic.setStatus(Status.NEW);
        } else if (hasDone) {
            epic.setStatus(Status.DONE);
        }
    }

    //

    @Override
    public String toString() {
        return "TaskManager{" +
                "tasks=" + tasks +
                ", epics=" + epics +
                ", subtasks=" + subtasks +
                '}';
    }
}