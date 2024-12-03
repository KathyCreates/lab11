package com.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ToDoListApp extends Application {

    public ObservableList<String> tasks;

    @Override
    public void start(Stage stage) {
        tasks = FXCollections.observableArrayList();

        ListView<String> taskList = new ListView<>(tasks);
        TextField taskInput = new TextField();
        Button addButton = new Button("Add Task");
        Button removeButton = new Button("Remove Selected Task");

        addButton.setOnAction(e -> {
            // Додаємо задачу, якщо поле не порожнє
            addTask(taskInput.getText());
            taskInput.clear();
        });

        removeButton.setOnAction(e -> {
            // Видаляємо вибрану задачу
            String selectedTask = taskList.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                removeTask(selectedTask);
            }
        });

        VBox layout = new VBox(10, taskInput, addButton, removeButton, taskList);
        Scene scene = new Scene(layout, 300, 400);

        stage.setScene(scene);
        stage.setTitle("To Do List");
        stage.show();
    }

    // Додаємо задачу до списку
    public void addTask(String task) {
        if (task != null && !task.isEmpty()) {
            tasks.add(task);
        }
    }

    // Видаляємо задачу зі списку
    public void removeTask(String task) {
        tasks.remove(task);
    }

    // Отримуємо список задач
    public ObservableList<String> getTasks() {
        return tasks;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
