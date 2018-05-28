package com.example.a14049472.p06_taskmanager;

import java.io.Serializable;

public class Task implements Serializable {
    private int id;
    private String taskname;
    private String description;

    public Task(int id, String taskname, String description) {
        this.id = id;
        this.taskname = taskname;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTaskname() {
        return taskname;
    }

    public String getDescription() {
        return description;
    }
}
