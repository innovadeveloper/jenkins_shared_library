package com.shared.library.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.shared.library.models.JenkinsProperty
import com.shared.library.models.PomModel
import org.apache.commons.lang3.StringUtils

class DependencyToolUtils {
//    def getContentFromPath

    String getContentFromPath(String path) {
        def resource = WebJenkinFileUtils.class.getClassLoader().getResourceAsStream(path) // Reemplaza con la ruta de tu archivo YAML
        Scanner scanner = new Scanner(resource, "UTF-8").useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }

    static void main(String[] args) {
//        def yamlContent = new WebJenkinFileUtils().getContentFromPath("templates/vars/template.dev.yaml") // Reemplaza con la ruta de tu archivo YAML
//        def objectMapper = new ObjectMapper(new YAMLFactory())
//        def jenkinsProperty = objectMapper.readValue(yamlContent, JenkinsProperty)
//        def dataText = new WebJenkinFileUtils().generateKubernetesResources(jenkinsProperty, true)
//        dataText.collect(){
//            def rootPath = "./output/"
//            def file = new File(rootPath + it.key)
//            file.write(it.value)
//        }
        // read version from configuration files... (maven, gradle, package.json)
        new WebJenkinFileUtils().readVersionFromPomFile('templates/vars/pom.xml')
        new WebJenkinFileUtils().readVersionFromPackageJsonFile('templates/vars/package.json')
        new WebJenkinFileUtils().readVersionFromAndroidBuildGradleFile('templates/vars/build.gradle')
        new WebJenkinFileUtils().readVersionFromWebSpringbootBuildGradleFile('templates/vars/web.build.gradle')
    }

    /**
     * read pom version from file of main repository (ex : web service, queue flow, etc)
     * @param path String
     * @return
     */
    def readVersionFromPomFile(String path){
        def pomContent = this.getContentFromPath(path)
        XmlMapper xmlMapper = new XmlMapper();
        PomModel pom = xmlMapper.readValue(pomContent, PomModel.class);
        return pom.getVersion()
    }


    /**
     * read pom version from file of main repository (ex : web service, gps service, etc)
     * @param path String
     * @return
     */
    def readVersionFromPackageJsonFile(String path){
        def packageJsonContent = this.getContentFromPath(path)
        def mapper = new ObjectMapper()
        def packageJson = mapper.readValue(packageJsonContent, Map)
        return packageJson.version
    }



    /**
     * read pom version from file of main repository (ex : gps, mpos, tsir, etc)
     * @param path String
     * @return
     */
    def readVersionFromAndroidBuildGradleFile(String path){
        def buildGradleContent = this.getContentFromPath(path)
        def versionName = null
        def pattern = /versionName\s+"(.*?)"/
        def matcher = buildGradleContent =~ pattern
        if (matcher.find()) {
            versionName = matcher.group(1)
        }
        if(versionName == null)throw new Exception("version name not found")
        return versionName
    }


    /**
     * read pom version from file of main repository (ex : web service, gps service, etc)
     * @param path String
     * @return
     */
    def readVersionFromWebSpringbootBuildGradleFile(String path){
        def buildGradleContent = this.getContentFromPath(path)
        def versionRegex = /version\s*=\s*["']([^"']+)["']/
        def match = buildGradleContent =~ versionRegex
        if (match) {
            def versionName = match[0][1]
            return versionName
        } else {
            throw new IllegalStateException("Could not find version in build.gradle")
        }
    }

}
