package controllers;

public class Managers {

    public static TaskManager getDefault() { //возвращать объект-менеджер
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
