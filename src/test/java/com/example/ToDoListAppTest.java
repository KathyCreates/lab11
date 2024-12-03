package com.example;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import javafx.collections.FXCollections;

public class ToDoListAppTest {

    private ToDoListApp app;

    @Before
    public void setUp() {
        // Ініціалізуємо об'єкт ToDoListApp
        app = new ToDoListApp();
        // Ініціалізація tasks без виклику start
        app.tasks = FXCollections.observableArrayList();
    }

    @Test
    public void testAddTask() {
        app.addTask("New Task");
        assertEquals(1, app.getTasks().size());
    }

    @Test
    public void testRemoveTask() {
        app.addTask("Task to Remove");
        app.removeTask("Task to Remove");
        assertEquals(0, app.getTasks().size());
    }
}
