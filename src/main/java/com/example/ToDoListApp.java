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
    private ObservableList<String> completedTasks;

    @Override
    public void start(Stage stage) {
        tasks = FXCollections.observableArrayList();
        completedTasks = FXCollections.observableArrayList();

        ListView<String> taskList = new ListView<>(tasks);
        TextField taskInput = new TextField();
        Button addButton = new Button("Add Task");
        Button removeButton = new Button("Remove Selected Task");
        Button markDoneButton = new Button("Mark as Done");

        addButton.setOnAction(e -> {
            addTask(taskInput.getText());
            taskInput.clear();
        });

        removeButton.setOnAction(e -> {
            String selectedTask = taskList.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                removeTask(selectedTask);
            }
        });

        markDoneButton.setOnAction(e -> {
            String selectedTask = taskList.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                markTaskAsDone(selectedTask, taskList);
            }
        });

        VBox layout = new VBox(10, taskInput, addButton, markDoneButton, removeButton, taskList);
        Scene scene = new Scene(layout, 300, 400);

        stage.setScene(scene);
        stage.setTitle("To Do List");
        stage.show();
    }

    // Додаємо задачу до списку
    public void addTask(String task) {
        if (task != null && !task.isEmpty() && !tasks.contains(task)) {
            tasks.add(task);
        }
    }

    // Видаляємо задачу зі списку
    public void removeTask(String task) {
        tasks.remove(task);
        completedTasks.remove(task);
    }

    // Маркуємо задачу як виконану
    public void markTaskAsDone(String task, ListView<String> taskList) {
        if (!completedTasks.contains(task)) {
            completedTasks.add(task);

            // Додаємо стилі для виконаних завдань
            taskList.setCellFactory(lv -> {
                return new javafx.scene.control.ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {
                            setText(item);
                            if (completedTasks.contains(item)) {
                                setStyle("-fx-text-fill: gray; -fx-strikethrough: true;");
                            } else {
                                setStyle("");
                            }
                        }
                    }
                };
            });

            taskList.refresh();
        }
    }

    // Отримуємо список задач
    public ObservableList<String> getTasks() {
        return tasks;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
