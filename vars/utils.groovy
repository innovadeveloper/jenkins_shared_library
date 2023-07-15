import com.shared.library.utils.FileExtractorUtils

String getMessage(){
    return "hola mundo"
}

String getVersionFromPackageJSON(String path){
    def fileContent = readFile(file: path)
    def versionFromPackageJson = new FileExtractorUtils().readVersionFromPackageJsonFile(fileContent)
    return versionFromPackageJson
}

String getVersionFromPom(String path){
    def fileContent = readFile(file: path)
    echo fileContent
    def versionFromPackageJson = new FileExtractorUtils().readVersionFromPomFile(fileContent)
    return versionFromPackageJson
}