package com.cleanup.todoc.database.dao;

import androidx.room.Insert;

import com.cleanup.todoc.model.Project;

public interface ProjectDao {
    @Insert
    long insertProject(Project project);

}