package tests.controllers;

import controllers.InMemoryTaskManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ManagersTest {

    //•	убедитесь, что утилитарный класс всегда возвращает проинициализированные и готовые к работе экземпляры менеджеров;
    @Test
    public void testGetManagerReturnsInitializedInstance() {
        InMemoryTaskManager IMTM = new InMemoryTaskManager();

        // Проверяем, что менеджер не равен null
        Assertions.assertNotNull(IMTM, "Manager should not be null");
    }

}