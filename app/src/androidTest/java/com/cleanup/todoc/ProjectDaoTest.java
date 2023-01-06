package com.cleanup.todoc;

import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Project;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)

public class ProjectDaoTest {
    // FOR DATA
    private TodocDatabase database;
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    // DATA SET FOR TEST
    private static long PROJECT_ID = 1;
    private static long PROJECT_ID_2 = 2;
    private static Project PROJECT_DEMO = new Project(PROJECT_ID, "Test", 0xFFEADAD1);
    private static Project PROJECT_DEMO_2 = new Project(PROJECT_ID_2, "Test_2", 0xFFEADAD1);


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
    public void insertAndGetProject() throws InterruptedException {
        // BEFORE : Adding a new project
        this.database.projectDao().insertProject(PROJECT_DEMO);
        // TEST
        Project project = this.database.projectDao().getProject(PROJECT_ID);
        assertTrue(project.getName().equals(PROJECT_DEMO.getName()) && project.getId() == PROJECT_ID);
    }

    @Test
    public void insertAndGetProjectList() throws InterruptedException {
        // BEFORE : Adding a new project
        this.database.projectDao().insertProject(PROJECT_DEMO);
        this.database.projectDao().insertProject(PROJECT_DEMO_2);
        // TEST
        List<Project> list = this.database.projectDao().getProjects();
        assertTrue(list.get(0).getName().equals(PROJECT_DEMO.getName()) && list.get(0).getId() == PROJECT_ID);
        assertTrue(list.get(1).getName().equals(PROJECT_DEMO_2.getName()) && list.get(1).getId() == PROJECT_ID_2);
    }
}
