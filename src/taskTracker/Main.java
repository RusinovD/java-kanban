package taskTracker;

import controllers.Managers;
import controllers.TaskManager;
import Model.*;


import java.util.ArrayList;

public class Main {
    private static TaskManager taskManager = Managers.getDefault();


    public static void main(String[] args) {
        // Создаем задачи
        Task task1 = new Task(0,"Задача 1", "Описание задачи 1", Status.NEW);
        taskManager.addTask(task1);
        Task task2 = new Task(0, "Задача 2", "Описание задачи 2", Status.NEW);
        taskManager.addTask(task2);

        //Создаем первый эпик
        Epic epic1 = new Epic(0, "Эпик 1", "Описание эпика 1", Status.NEW);
        taskManager.addEpic(epic1);

        //Создаем подзадачи к первому эпику
        Subtask subtask1 = new Subtask(0,"Подзадача 1", "Описание подзадачи 1", Status.NEW, 0);
        taskManager.addSubtask(subtask1);

        Subtask subtask2 = new Subtask(0,"Подзадача 2", "Описание подзадачи 2", Status.NEW, 0);
        taskManager.addSubtask(subtask2);

        //Создаем второй эпик
        Epic epic2 = new Epic(0,"Эпик 2", "Описание эпика 2", Status.NEW);
        taskManager.addEpic(epic2);

        // Распечатываем списки задач, эпиков и подзадач
        System.out.println("Задачи:");
        System.out.println(taskManager.getTasks());

        System.out.println("Эпики:");
        System.out.println(taskManager.getEpics());

        System.out.println("Подзадачи:");
        System.out.println(taskManager.getSubtasks());

        // распечатываем списко подзадач одного эпика
        System.out.println("Подзадачи " + epic1 + ":");
        System.out.println(taskManager.getSubtasksOfEpic(3));

        // Изменяем статусы
        taskManager.updateTask(task1);

        // Распечатываем измененные статусы
        System.out.println("Измененные статусы:");
        System.out.println(task1);
        System.out.println(subtask1);
        System.out.println(epic1); //тк обе подзадачи выполнены - статус эпика должен обновиться

        // Удаляем одну из задач
        taskManager.removeTask(1);
        // Удаляем один из эпиков
        taskManager.removeTask(3);

        // Удаляем задачи определенного типа
        taskManager.deleteTasks();

        //получаем задачу, эпик и подзадачу по id
        System.out.println("Задача №: " + 1);
        System.out.println(taskManager.getTask(1));
        System.out.println("Эпик №: " + 3);
        System.out.println(taskManager.getEpic(3));
        System.out.println("Подзадача №: " + 4);
        System.out.println(taskManager.getSubtask(4));

        //вывод истории запросов задач
        System.out.println("История запросов задач:");
        System.out.println(taskManager.getHistory());
    }
}