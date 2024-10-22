package tests.controllers;

import Model.Status;
import Model.Task;
import controllers.HistoryManager;
import controllers.Managers;
import controllers.TaskManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HistoryManagerTest {
    HistoryManager hm = Managers.getDefaultHistory();

    // убедитесь, что задачи, добавляемые в HistoryManager, сохраняют предыдущую версию задачи и её данных.
    @Test
    void tasksAddedToHistoryManagerRetainThePreviousVersionOfTaskAndItsData () {

        List<Task> taskHistory = new ArrayList<>(10);
        Task task1 = new Task(1,"", "", Status.NEW);
        Task task2 = new Task(2,"", "", Status.NEW);

        hm.add(task1);
        hm.add(task2);

        String task3 = String.valueOf(taskHistory.get(0));
        String task4 = String.valueOf(taskHistory.get(1));

        Assertions.assertEquals(task1, task3, "задача добавляется корректно");
        Assertions.assertEquals(task3, task2, "задачи добавляются некорректно!");
    }

    @Test
    void add() {
        Task task = new Task(1,"", "", Status.NEW);
        hm.add(task);
        final List<Task> history = hm.getHistory();
        Assertions.assertNotNull(history, "История не пустая.");
        Assertions.assertEquals(1, history.size(), "История не пустая.");
    }

    //•	проверьте, что задачи с заданным id и сгенерированным id не конфликтуют внутри менеджера;
    @Test
    void testTaskIdConflict() {
        TaskManager taskManager = Managers.getDefault();
        // Создаем задачу с заданным ID
        final int id = 1;
        Task task1 = new Task(id, "Задача 1", "Описание задачи 1", Status.NEW);
        taskManager.addTask(task1); // Добавляем задачу в менеджер

        // Генерируем новый ID (например, случайный или увеличиваемый)
        int generatedId = 0; // новый ID
        Task task2 = new Task(generatedId, "задача 2", "Описание 2", Status.NEW);

        // Добавляем сгенерированную задачу в менеджер
        taskManager.addTask(task2);

        // Проверяем, что задачи с заданным ID и сгенерированным ID не конфликтуют
        Task retrievedTask1 = taskManager.getTask(id);
        Task retrievedTask2 = taskManager.getTask(generatedId);

        Assertions.assertNotNull(taskManager.getTask(id));
        Assertions.assertNotNull(taskManager.getTask(generatedId));
        Assertions.assertNotEquals(retrievedTask1.getId(), retrievedTask2.getId(), "Задачи не равны");
    }
}