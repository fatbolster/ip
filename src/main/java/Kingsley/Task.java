package Kingsley;

/**
* Represents a task in the to-do list. This is abstract and different types of task will extend from this class.
* */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the input description. It is initalised as not done.
     *
     * @param description descripton of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns completion status. ("X" if completed, "" if not completed)
     *
     * @return String representation of completion
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }



    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }


    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Converts the task into a string to be added into the saved file.
     */
    public abstract String toSaveFormat();



}