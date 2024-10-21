package controllers;

import Model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

abstract class InMemoryTaskManager implements TaskManager {
    private HashMap<Integer, Task> tasks; // Хранит все задачи
    private HashMap<Integer, Epic> epics; // Хранит все эпики
    public HashMap<Integer, Subtask> subtasks; // Список подзадач
    private int nextId = 1; // хранит уникальный ID

    private HistoryManager historyManager = new HistoryManager() {
        @Override
        public void add(Task task) {

        }

        @Override
        public List<Task> getHistory() {
            return List.of();
        }
    };

    public InMemoryTaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
        this.historyManager = Managers.getDefaultHistory();
    }

    @Override
    public int addTask(Task task) {
        task.setId(nextId++);
        tasks.put(nextId, task);
        return 0;
    }

    @Override
    public void addEpic(Epic epic) {
        epic.setId(nextId++);
        epics.put(nextId, epic);
    }

    @Override
    public Integer addSubtask(Subtask subtask) {
        final int epicId = subtask.getEpicId(); //берем id от эпика
        Epic epic = epics.get(epicId); // по id эпика получаем сам эпик
        if (epic == null) {// проверяем не пустой ли эпик
            return null;
        }
        final int id = nextId++; //генерируем новый id для подзадачи
        subtask.setId(id); // передаем тот id в класс подзадачи
        subtasks.put(id, subtask); // добавляем подзадачу в хэшмэп
        epic.getSubtasksId().add(subtask); //в лист эпика подзадачу
        updateEpicStatus(epic);// обновляем статус эпика
        return id;
    }

    @Override
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }
    @Override
    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }
    @Override
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public ArrayList<Subtask> getSubtasksOfEpic (Integer epicId){
        ArrayList<Subtask> subtasksOfEpic = new ArrayList<>(); //создаем лист для хранения и передачи списка на печать
        Epic epic = epics.get(epicId); //получили нужный эпик по его id, теперь надо взять его подзадачи
        if (epic == null) {
            return null;
        }
        for (Subtask subtask : subtasks.values()) {
            subtasksOfEpic.add(subtask);
        }
        return subtasksOfEpic;
    }

    // удаление одной из задач
    @Override
    public void removeTask(int id) {
        Epic epic = epics.get(id);
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        } else if (epics.containsKey(id)) {//удаление эпика
            epics.remove(id); //удаляем сам эпик
            epic.getSubtasksId().remove(id);//удаляем его подзадачи
        } else if (subtasks.containsKey(id)) {
            subtasks.remove(id); //удаляем задачу
            epic.subtasksId.remove(id);
            updateEpicStatus(epic); //обновляем статус эпика
        } else
            System.out.println("Задачи с таким ID не существует.");
    }

    // удаление всех задач определенного типа
    @Override
    public void deleteTasks() {
        tasks.clear();
    }
    @Override
    public void deleteSubtasks() {
        for (Epic epic : epics.values()) {
            epic.subtasksId.clear();
            updateEpicStatus(epic);
        }
        subtasks.clear();
    }
    @Override
    public void deleteEpics() {
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void updateTask(Task task) {
        // Проверяем, существует ли задача с таким ID
        if (!tasks.containsKey(task.getId())) {
            System.out.println("Задача с таким ID не найдена");
        }
        // Обновляем задачу в HashMap
        tasks.put(task.getId(), task);
        if (task instanceof Epic) { // Если задача является эпиком, обновляем статус эпика
            Epic epic = (Epic) task;
            ArrayList<Subtask> subtasks = new ArrayList<>();
            subtasks = getSubTasksByEpicId(epic.getId());// Получаем все подзадачи, связанные с эпиком
            updateEpicStatus(epic); // Обновляем статус эпика в зависимости от статусов подзадач
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
    private void updateEpicStatus(Epic epic) {
        boolean hasNew = false;
        boolean hasInProgress = false;
        boolean hasDone = true;

        for (Task task : epic.getSubtasksId()) {
            if (task.getStatus() == Status.NEW) {
                hasNew = true;
            } else if (task.getStatus() == Status.IN_PROGRESS) {
                hasInProgress = true;
            } else if (task.getStatus() == Status.DONE) {
                hasDone = true;
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

    @Override
    public String toString() {
        return "TaskManager{" +
                "tasks=" + tasks +
                ", epics=" + epics +
                ", subtasks=" + subtasks +
                '}';
    }
}
