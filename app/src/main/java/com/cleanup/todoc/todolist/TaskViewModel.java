package com.cleanup.todoc.todolist;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.concurrent.Executor;

import javax.sql.DataSource;

public class TaskViewModel extends ViewModel {

    // REPOSITORIES

    private final TaskDataRepository taskDataRepository;

    private final ProjectDataRepository projectDataRepository;

    private final Executor executor;

    // DATA

    @Nullable

    private LiveData<Project> currentProject;

    public TaskViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {

        this.taskDataRepository = taskDataSource;

        this.projectDataRepository = projectDataSource;

        this.executor = executor;

    }

    public void init(long taskId) {

        if (this.currentProject != null) {

            return;

        }

        currentProject = projectDataRepository.getUser(projectId);

    }

    // -------------
}
