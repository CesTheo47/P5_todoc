package com.cleanup.todoc.repositories;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectDataRepository {
    private final ProjectDao projectDao;

    public ProjectDataRepository(ProjectDao projectDao) { this.projectDao = projectDao; }

    public List<Project> getProjects() {
        return projectDao.getProjects();
    }

    //public LiveData<Project> getUser(long projectId) { return this.projectDao.getProjects(projectId); }
}

