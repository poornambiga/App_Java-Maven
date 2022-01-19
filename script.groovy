def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'a5366721-3854-4385-a1eb-bfecbf41c60f', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t poornambiga/demo-app:jma-2.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push poornambiga/demo-app:jma-2.0'
    }
} 


return this
