package com.shared.library

import com.fasterxml.jackson.databind.ObjectMapper
import com.shared.library.models.JenkinsProperty
import com.shared.library.models.Project
import com.shared.library.models.ProjectInfo
import com.shared.library.utils.FileExtractorUtils

class SharedLibraryApp {

    static String getContentFromPath(String path) {
        def resource = SharedLibraryApp.class.getClassLoader().getResourceAsStream(path) // Reemplaza con la ruta de tu archivo YAML
        Scanner scanner = new Scanner(resource, "UTF-8").useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }

    static void main(String[] args) {
        JenkinsProperty jenkinsProperty = new JenkinsProperty()
        jenkinsProperty.project = new Project()
        jenkinsProperty.project.project_info = new ProjectInfo()
        jenkinsProperty.project.project_info.app_name = "hola mundo"
        def objectMapper = new ObjectMapper()
        def json = objectMapper.writeValueAsString(jenkinsProperty)
        println json

        // read version from configuration files... (maven, gradle, package.json)
        new FileExtractorUtils().readVersionFromPomFile(getContentFromPath('templates/vars/pom.xml'))
        new FileExtractorUtils().readVersionFromPackageJsonFile(getContentFromPath('templates/vars/package.json'))
        new FileExtractorUtils().readVersionFromAndroidBuildGradleFile(getContentFromPath('templates/vars/build.gradle'))
        new FileExtractorUtils().readVersionFromWebSpringbootBuildGradleFile(getContentFromPath('templates/vars/web.build.gradle'))
    }
}
