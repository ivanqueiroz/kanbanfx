package dev.ivanqueiroz.kanbanfx.domain.model;

import java.util.List;

public class Column {

    private String name;
    private int workInProgressLimit;
    private List<Task> tasks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorkInProgressLimit() {
        return workInProgressLimit;
    }

    public void setWorkInProgressLimit(int workInProgressLimit) {
        this.workInProgressLimit = workInProgressLimit;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
