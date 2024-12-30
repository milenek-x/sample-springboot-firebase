package com.example.task_microservice.service;

import com.example.task_microservice.data.Task;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TaskService {
    @Autowired
    private Firestore firestore;

    public String addTask(Task task) {
        try {
            ApiFuture<DocumentReference> books = firestore.collection("tasks").add(task);
            return "Tasks Document saved: taskId is " + books.get().getId();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Task> getAllTasks() {
        try {
            ApiFuture<QuerySnapshot> future = firestore.collection("books").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            List<Task> tasks = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) {
                tasks.add(document.toObject(Task.class));
            }
            return tasks;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public Task getTaskById(String id) {
        try {
            DocumentReference docRef = firestore.collection("tasks").document(id);
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                return document.toObject(Task.class);
            } else {
                return null; // or throw an exception if preferred
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public String updateTask(String id, Task task) {
        try {
            DocumentReference docRef = firestore.collection("tasks").document(id);
            ApiFuture<WriteResult> writeResult = docRef.set(task);
            return "Task updated with ID: " + id + " at " + writeResult.get().getUpdateTime();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public String deleteTask(String id) {
        try {
            DocumentReference docRef = firestore.collection("tasks").document(id);
            ApiFuture<WriteResult> writeResult = docRef.delete();
            return "Task with ID: " + id + " has been deleted.";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
