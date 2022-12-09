package com.cleanup.todoc.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class MainViewModel extends ViewModel {

    // REPOSITORIES
    private final TaskDataRepository taskDataRepository;
    private final ProjectDataRepository projectDataRepository;
    private final Executor executor;

    public MainViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {
        this.taskDataRepository = taskDataSource;
        this.projectDataRepository = projectDataSource;
        this.executor = executor;
    }

    public LiveData<List<Task>> getTaskListLiveData() {
        return this.taskDataRepository.getTasks();
    }

    public List<Project> getProjects() {
        return projectDataRepository.getProjects();
    }
}
