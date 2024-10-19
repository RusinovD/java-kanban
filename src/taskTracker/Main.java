package taskTracker;

import Model.*;
import controllers.TaskManager;

public class Main {
    private static TaskManager taskManager;

    public static void main(String[] args) {
        // Создаем задачи
        Task task1 = new Task("Задача 1", "Описание задачи 1", 1);
        taskManager.addTask(task1);
        Task task2 = new Task("Задача 2", "Описание задачи 2", 2);
        taskManager.addTask(task2);

        // Создаем первый эпик
        int epicId = 0;
        Epic epic1 = new Epic("Эпик 1", "Описание эпика 1", 3);
        taskManager.addEpic(epic1);

        // Создаем подзадачи к первому эпику
        Subtask subtask1 = new Subtask("Подзадача 1", "Описание подзадачи 1", 4);
        taskManager.addSubtask(epic1, subtask1);

        Subtask subtask2 = new Subtask("Подзадача 2", "Описание подзадачи 2", 5);
        taskManager.addSubtask(epic1, subtask2);

        // Создаем второй эпик
        Epic epic2 = new Epic("Эпик 2", "Описание эпика 2", 6);
        taskManager.addEpic(epic2);

        // Распечатываем списки задач и эпиков
        System.out.println("Задачи:");
        System.out.println(taskManager.getAllTasks());

        System.out.println("Эпики:");
        System.out.println(taskManager.getAllEpics());

        System.out.println("Подзадачи:");
        System.out.println(taskManager.getAllSubtasks());

        // Изменяем статусы
        taskManager.updateTask(task1);

        // Распечатываем измененные статусы
        System.out.println("Измененные статусы:");
        System.out.println(task1);
        System.out.println(subtask1);
        System.out.println(epic1); //тк обе подзадачи выполнены - статус эпика должен обновиться

        // Удаляем одну из задач
        taskManager.removeTask(1);

        // Удаляем все задачи (согласно ТЗ "b. Удаление всех задач.")
        taskManager.removeAllTask();

        // Удаляем задачу
        task1 = null;
        System.out.println("После удаления задачи 1:");
        System.out.println(task1); // Переменная task1 теперь null
    }
}