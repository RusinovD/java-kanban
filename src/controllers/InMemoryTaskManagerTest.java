package controllers;

import Model.Task;
import Model.Status;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    HistoryManager hm = Managers.getDefaultHistory();
    TaskManager taskManager = Managers.getDefault();
    // проверьте, что InMemoryTaskManager действительно добавляет задачи разного типа и может найти их по id;
    // проверьте, что объект Subtask нельзя сделать своим же эпиком;
    @Test
    void addNewTask() {
        Task task = new Task(0,"Test addNewTask", "Test addNewTask description", Status.NEW);
        final int taskId = taskManager.addTask(task);

        final Task savedTask = taskManager.getTask(taskId);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = taskManager.getTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }

}