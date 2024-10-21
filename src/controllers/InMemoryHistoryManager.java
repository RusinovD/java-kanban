package controllers;

import Model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    public final List<Task> taskHistory = new ArrayList<>(10);//хранит историю просмотра задач

    @Override
    public void add(Task task) {
        taskHistory.add(task);// Добавляем задачу в историю просмотров
        if (taskHistory.size() > 10) {
            taskHistory.remove(0); // Удаляем самый старый элемент
        }
    }

    @Override
    public List<Task> getHistory() {
        return taskHistory;
    }




}
