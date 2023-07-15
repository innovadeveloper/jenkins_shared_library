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
    echo "[start]\n$fileContent\n[end]"
    def objectType = fileContent.getClass().getSimpleName()
    echo "Type $objectType"
    def versionFromPackageJson = new FileExtractorUtils().readVersionFromPomFile(fileContent)
    return versionFromPackageJson
}