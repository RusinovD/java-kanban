package controllers;

import Model.Epic;
import Model.Subtask;
import Model.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

  //  HistoryManager historyManager = new HistoryManager();

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
        int id1 = 1;
        int id2 = 1;
        int id3 = 2;

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



}














