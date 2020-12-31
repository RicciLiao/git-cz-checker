package com.github.ricciliao.gitczchecker.services

import com.intellij.openapi.project.Project
import com.github.ricciliao.gitczchecker.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
