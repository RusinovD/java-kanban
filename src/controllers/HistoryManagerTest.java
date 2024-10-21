package controllers;

import Model.Task;
import Model.Status;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

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

        assertEquals(task1, task3, "задача добавляется корректно");
        assertEquals(task3, task2, "задачи добавляются некорректно!");
    }

    @Test
    void add() {
        Task task = new Task(1,"", "", Status.NEW);
        hm.add(task);
        final List<Task> history = hm.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");
    }

}