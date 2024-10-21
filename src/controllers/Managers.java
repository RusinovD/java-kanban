package controllers;

import Model.Epic;
import Model.Subtask;
import Model.Task;

import java.util.List;

public class Managers {

    public static TaskManager getDefault() { //возвращать объект-менеджер
        return new InMemoryTaskManager() {
            @Override
            public List<Task> getHistory() {
                return List.of();
            }

            @Override
            public Task getTask(int id) {
                return null;
            }

            @Override
            public Epic getEpic(int id) {
                return null;
            }

            @Override
            public Subtask getSubtask(int id) {
                return null;
            }
        };
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
