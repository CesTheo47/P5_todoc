package com.cleanup.todoc;

import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.TaskAndProject;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)

public class TaskDaoTest {
    // FOR DATA
    private TodocDatabase database;
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    // DATA SET FOR TEST
    private static long TASK_ID = 1;
    private static long TASK_ID_2 = 2;
    private static long PROJECT_ID = 1;
    private static Project PROJECT_DEMO = new Project(PROJECT_ID, "Test", 0xFFEADAD1);
    private static Task TASK_DEMO = new Task(1, 1, "Task", 123);
    private static Task TASK_DEMO_2 = new Task(2, 1, "Task_2", 123);


    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                        TodocDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void insertAndGetTasks() throws InterruptedException {
        this.database.projectDao().insertProject(PROJECT_DEMO);
        this.database.taskDao().insertTask(TASK_DEMO);
        this.database.taskDao().insertTask(TASK_DEMO_2);

        LiveData<List<TaskAndProject>> list = (LiveData<List<TaskAndProject>>) LiveDataTestUtil.getValue(this.database.taskDao().getTasks());
        assertTrue(list.getValue().equals(TASK_DEMO.getName())) && list
    }


    @Test
    public  void insertAndDeleteTask() throws InterruptedException {
        // BEFORE : Adding a new task & Project
        this.database.projectDao().insertProject(PROJECT_DEMO);
        this.database.taskDao().insertTask(TASK_DEMO);

        Task taskAdded = LiveDataTestUtil.getValue(this.database.taskDao().getTasks(PROJECT_ID)).get(0);
        this.database.taskDao().deleteTask(taskAdded.getId());
        //TEST
        List<Task> list = LiveDataTestUtil.getValue(this.database.taskDao().getTasks(PROJECT_ID));
        assertTrue(list.isEmpty());
    }
}
