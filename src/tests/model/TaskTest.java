package tests.model;

import Model.Epic;
import Model.Status;
import Model.Subtask;
import Model.Task;
import controllers.Managers;
import controllers.TaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    TaskManager taskManager = Managers.getDefault();

    //проверьте, что экземпляры класса Task равны друг другу, если равен их id;
    @Test
    void objectsAreEqualsIfIdsAreEquals() {
        int id1 = 1;
        int id2 = 1;
        int id3 = 2;

        Task task1 = taskManager.getTask(id1);
        Task task2 = taskManager.getTask(id2);
        Task task3 = taskManager.getTask(id3);

        assertEquals(task1, task2, "задачи с одинаковым id равны");
        assertEquals(task1, task3, "задачи с разными id неравны");
    }

    // проверьте, что наследники класса Task равны друг другу, если равен их id;
    @Test
    void InstancesOfTaskAreEqualIfTheirIDsAreEqual() {
        int id1 = 3;
        int id2 = 3;
        int id3 = 6;

        Epic epic1 = taskManager.getEpic(id1);
        Epic epic2 = taskManager.getEpic(id2);
        Epic epic3 = taskManager.getEpic(id3);

        assertEquals(epic1, epic2, "эпики с одинаковым id равны");
        assertEquals(epic1, epic3, "эпики с разными id неравны");

        System.out.println("_____________");

        Subtask subtask1 = taskManager.getSubtask(id1);
        Subtask subtask2 = taskManager.getSubtask(id2);
        Subtask subtask3 = taskManager.getSubtask(id3);

        assertEquals(subtask1, subtask2, "подзадачи с одинаковым id равны");
        assertEquals(subtask1, subtask3, "подзадачи с разными id неравны");
    }

    //•	создайте тест, в котором проверяется неизменность задачи (по всем полям) при добавлении задачи в менеджер
    @Test
    public void immutabilityOfTaskIsCheckedWhenAddingTaskToManager() {
        TaskManager taskManager = Managers.getDefault();
        Task task = new Task(1, "Задача №1", "Описание задачи №1", Status.NEW);

        // Запоминаем исходные значения
        String title = task.getTitle();
        String description = task.getDescription();
        int id = task.getId();

        // Добавляем задачу в менеджер
        taskManager.addTask(task);

        // Проверяем, что поля задачи остались неизменными
        assertEquals(title, task.getTitle(), "Заголовки совпадают");
        assertEquals(description, task.getDescription(), "Описания совпадают");
        assertEquals(id, task.getId(), "Описания совпадают");

    }
}














