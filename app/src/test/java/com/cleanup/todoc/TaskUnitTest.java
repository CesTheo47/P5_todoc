package com.cleanup.todoc;

import static org.junit.Assert.assertSame;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.TaskAndProject;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class TaskUnitTest {

    private Project project = new Project(1, "Test", 0xFFEADAD1);
    @Test
    public void test_az_comparator() {
        final Task task1 = new Task(1, 1, "aaa", 123);
        final Task task2 = new Task(2, 2, "zzz", 124);
        final Task task3 = new Task(3, 3, "hhh", 125);

        final TaskAndProject taskAndProject1 = new TaskAndProject(task1, project);
        final TaskAndProject taskAndProject2 = new TaskAndProject(task2, project);
        final TaskAndProject taskAndProject3 = new TaskAndProject(task3, project);



        final ArrayList<TaskAndProject> taskAndProjectList = new ArrayList<>();
        taskAndProjectList.add(taskAndProject1);
        taskAndProjectList.add(taskAndProject2);
        taskAndProjectList.add(taskAndProject3);
        Collections.sort(taskAndProjectList, new TaskAndProject.TaskAZComparator());

        assertSame(taskAndProjectList.get(0), taskAndProject1);
        assertSame(taskAndProjectList.get(1), taskAndProject3);
        assertSame(taskAndProjectList.get(2), taskAndProject2);
    }

   /* @Test
    public void test_za_comparator() {
        final Task task1 = new Task(1, 1, "aaa", 123);
        final Task task2 = new Task(2, 2, "zzz", 124);
        final Task task3 = new Task(3, 3, "hhh", 125);

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new Task.TaskZAComparator());

        assertSame(tasks.get(0), task2);
        assertSame(tasks.get(1), task3);
        assertSame(tasks.get(2), task1);
    }

    @Test
    public void test_recent_comparator() {
        final Task task1 = new Task(1, 1, "aaa", 123);
        final Task task2 = new Task(2, 2, "zzz", 124);
        final Task task3 = new Task(3, 3, "hhh", 125);

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new Task.TaskRecentComparator());

        assertSame(tasks.get(0), task3);
        assertSame(tasks.get(1), task2);
        assertSame(tasks.get(2), task1);
    }

    @Test
    public void test_old_comparator() {
        final Task task1 = new Task(1, 1, "aaa", 123);
        final Task task2 = new Task(2, 2, "zzz", 124);
        final Task task3 = new Task(3, 3, "hhh", 125);

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new Task.TaskOldComparator());

        assertSame(tasks.get(0), task1);
        assertSame(tasks.get(1), task2);
        assertSame(tasks.get(2), task3);
    }*/
}
