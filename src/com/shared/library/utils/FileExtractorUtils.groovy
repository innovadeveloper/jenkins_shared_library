package com.shared.library.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.shared.library.models.JenkinsProperty
import com.shared.library.models.PomModel
import org.apache.commons.lang3.StringUtils

/**
 * extractor de propiedades de archivos de proyecto como :
 *  - Dependency Manager (pom, package.json, build.gradle)
 */
class FileExtractorUtils {

    /**
     * read pom version from file of main repository (ex : web service, queue flow, etc)
     * @param path String
     * @return version
     */
    def readVersionFromPomFile(String pomContent){
        XmlMapper xmlMapper = new XmlMapper()
        PomModel pom = xmlMapper.readValue(pomContent, PomModel.class)
        println("holaaaaaaa")
        println(pom)
        return pom.getVersion()
    }

    /**
     * read pom version from file of main repository (ex : web service, gps service, etc)
     * @param path String
     * @return version
     */
    def readVersionFromPackageJsonFile(String packageJsonContent){
        def mapper = new ObjectMapper()
        def packageJson = mapper.readValue(packageJsonContent, Map)
        return packageJson.version
    }

    /**
     * read pom version from file of main repository (ex : gps, mpos, tsir, etc)
     * @param path String
     * @return version
     */
    def readVersionFromAndroidBuildGradleFile(String buildGradleContent){
        def versionName = null
        def pattern = /versionName\s+"(.*?)"/
        def matcher = buildGradleContent =~ pattern
        if (matcher.find()) {
            versionName = matcher.group(1)
        }
        if(versionName == null)throw new Exception("version name not found")
        println(versionName)
        return versionName
    }

    /**
     * read pom version from file of main repository (ex : web service, gps service, etc)
     * @param path String
     * @return version
     */
    def readVersionFromWebSpringbootBuildGradleFile(String buildGradleContent){
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

