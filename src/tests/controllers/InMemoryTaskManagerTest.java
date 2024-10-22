package tests.controllers;

import Model.Epic;
import Model.Status;
import Model.Subtask;
import Model.Task;
import controllers.HistoryManager;
import controllers.Managers;
import controllers.TaskManager;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryTaskManagerTest {
    HistoryManager hm = Managers.getDefaultHistory();
    TaskManager taskManager = Managers.getDefault();

    // проверьте, что объект Subtask нельзя сделать своим же эпиком;
    @Test
    void addNewTask() {
        Task task = new Task(0,"задача 1", "Описание задачи 1", Status.NEW);
        final int taskId = taskManager.addTask(task);

        final Task savedTask = taskManager.getTask(taskId);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = taskManager.getTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }
    // проверьте, что InMemoryTaskManager действительно добавляет задачи разного типа и может найти их по id;
    @Test
    void testAddAndFindTaskById() {
        Task task = new Task(0,"задача 1", "Описание задачи 1", Status.NEW);
        taskManager.addTask(task);

        Task foundTask = taskManager.getTask(task.getId());
        assertNotNull(foundTask);
        assertEquals(task.getId(), foundTask.getId(), "созданная и полученная задачи равны");
        assertEquals("задача 1", foundTask.getTitle());
        assertEquals("Описание задачи 1", foundTask.getDescription());
    }

    @Test
    void testAddAndFindEpicById() {
        Epic epic = new Epic(0, "Эпик 1", "Описание эпика 1", Status.NEW);
        taskManager.addEpic(epic);

        Epic foundEpic = taskManager.getEpic(epic.getId());
        assertNotNull(foundEpic);
        assertEquals(epic.getId(), foundEpic.getId(), "созданный и полученный эпики равны");
        assertEquals("Эпик 1", foundEpic.getTitle());
        assertEquals("Описание эпика 1", foundEpic.getDescription());
    }

    @Test
    void testAddAndFindSubtaskById() {
        Epic epic = new Epic(0, "Эпик 1", "Описание эпика 1", Status.NEW);
        taskManager.addEpic(epic);

        Subtask subtask = new Subtask(0,"Подзадача 1", "описание подзадачи 1", Status.NEW, epic.getId());
        taskManager.addSubtask(subtask);

        Subtask foundSubtask = taskManager.getSubtask(subtask.getId());
        assertNotNull(foundSubtask);
        assertEquals(subtask.getId(), foundSubtask.getId(), "созданная и полученная подзадачи равны");
        assertEquals("Подзадача 1", foundSubtask.getTitle());
        assertEquals("описание подзадачи 1", foundSubtask.getDescription());
    }

}