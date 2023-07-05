package com.shared.library

import com.fasterxml.jackson.databind.ObjectMapper
import com.shared.library.models.JenkinsProperty
import com.shared.library.models.Project
import com.shared.library.models.ProjectInfo

class SharedLibraryApp {

    static void main(String[] args) {
        JenkinsProperty jenkinsProperty = new JenkinsProperty()
        jenkinsProperty.project = new Project()
        jenkinsProperty.project.project_info = new ProjectInfo()
        jenkinsProperty.project.project_info.app_name = "hola mundo"
        def objectMapper = new ObjectMapper()
        def json = objectMapper.writeValueAsString(jenkinsProperty)
        println json
    }
}
