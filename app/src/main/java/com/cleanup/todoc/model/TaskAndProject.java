package com.cleanup.todoc.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.Comparator;

public class TaskAndProject {
    @Embedded
    public Task task;
    @Relation(
            parentColumn = "projectId",
            entityColumn = "id"
    )
    public Project project;

    public TaskAndProject() {
    }

    public TaskAndProject(Task task, Project project) {
        this.task = task;
        this.project = project;
    }

    public static class TaskAZComparator implements Comparator<TaskAndProject> {
        @Override
        public int compare(TaskAndProject left, TaskAndProject right) {
            return left.task.getName().toLowerCase().compareTo(right.task.getName().toLowerCase());
        }
    }

    public static class TaskZAComparator implements Comparator<TaskAndProject> {
        @Override
        public int compare(TaskAndProject left, TaskAndProject right) {
            return right.task.getName().toLowerCase().compareTo(left.task.getName().toLowerCase());
        }
    }

    public static class TaskRecentComparator implements Comparator<TaskAndProject> {
        @Override
        public int compare(TaskAndProject left, TaskAndProject right) {
            return (int) (right.task.getCreationTimestamp() - left.task.getCreationTimestamp());
        }
    }

    public static class TaskOldComparator implements Comparator<TaskAndProject> {
        @Override
        public int compare(TaskAndProject left, TaskAndProject right) {
            return (int) (left.task.getCreationTimestamp() - right.task.getCreationTimestamp());
        }
    }
}
