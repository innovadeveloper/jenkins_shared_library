//import groovy.json.JsonSlurper
//import com.cloudbees.groovy.cps.sandbox.SandboxInvoker
import java.io.InputStream
import com.cloudbees.groovy.cps.sandbox.SandboxInvoker
import com.shared.library.utils.WebJenkinFileUtils

String simpleMessage() {
    return 'hola'
}
String readAsInputStream() {
    def myResource = this.class.getResourceAsStream('test.txt')
    return myResource
}
String readAsInputStreamTwo() {
    def myResource = this.getClass().getClassLoader().getResourceAsStream('test.txt')
    return myResource
}

String readAsResource(filePath) {
    def myResource = libraryResource(filePath)
    return myResource
}

def executeReadAndCreateFile(){
    def myClosure = { param ->
        // Lógica del closure utilizando el parámetro
        return readAsResource(param)
    }
    echo "read 6"
    def webJenkinFileUtils = new WebJenkinFileUtils()
    def messageFromResource = webJenkinFileUtils.readByClosure2(myClosure)
    messageFromResource.collect(){
        // /var/lib/jenkins/jobs/TrainingSharedLibrary/workspace
        writeFile file: "./${it.key}-2", text: it.value
        echo 'escrito file 2'
        sh "pwd"
        sh "ls -l"
    }
}