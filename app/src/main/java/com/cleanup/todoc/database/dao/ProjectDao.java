package com.cleanup.todoc.database.dao;

import androidx.room.Insert;
import androidx.room.Query;

import com.cleanup.todoc.model.Project;

import java.util.List;

public interface ProjectDao {
    @Query("SELECT * FROM Project" )
    List<Project> getProjects();

    @Insert
    long insertProject(Project project);

}