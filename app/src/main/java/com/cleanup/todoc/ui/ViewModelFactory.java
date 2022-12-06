package com.cleanup.todoc.ui;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final TaskDataRepository taskDataRepository;
    private final ProjectDataRepository projectDataRepository;
    private final Executor executor;
    private static ViewModelFactory factory;

    public static ViewModelFactory getInstance(Context context) {
        if (factory == null) {
            synchronized (ViewModelFactory.class) {
                if (factory == null) {
                    factory = new ViewModelFactory(context);
                }
            }
        }
        return factory;
    }

    private ViewModelFactory(Context context) {
        TodocDatabase database = TodocDatabase.getInstance(context);
        this.taskDataRepository = new TaskDataRepository(database.taskDao());
        this.projectDataRepository = new ProjectDataRepository(database.projectDao());
        this.executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public <T extends ViewModel>  T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(taskDataRepository, projectDataRepository, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
