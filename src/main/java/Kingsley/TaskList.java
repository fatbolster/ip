package Kingsley;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task remove(int taskNumber) {
        return tasks.remove(taskNumber);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task t: this.tasks) {
            if (t.getDescription().contains(keyword)) {
                res.add(t);
            }
        }
        return res;

    }






}
