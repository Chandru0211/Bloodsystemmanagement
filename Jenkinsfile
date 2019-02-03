pipeline {
    agent any
    tools {
        maven 'MAVEN_HOME'
        jdk 'JAVA_HOME'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install' 
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
			mail to:"praveenero38@gmail.com", subject:"SUCCESS: ${currentBuild.fullDisplayName}", body: "Yay, we passed." 
                }
		failure{
			mail to:"praveenero38@.com", subject:"FAILURE: ${currentBuild.fullDisplayName}", body: "Boo, we failed."
		}
            } 
        }
    }
}
