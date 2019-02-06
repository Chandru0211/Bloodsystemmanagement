pipeline {
    agent any
    tools {
        maven 'maven3'
        jdk 'jdk8'
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
	stage ('SonarQube Analysis'){
		steps{
			//dir("src/main/java"){
			withSonarQubeEnv('sonarqube') {
			sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar'
			}	}
		//}
	}

        stage ('Build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install' 
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
			mail to:"pr393314@wipro.com", subject:"SUCCESS: ${currentBuild.fullDisplayName}", body: "Yay, we passed." 
                }
		failure{
			mail to:"pr393314@wipro.com", subject:"FAILURE: ${currentBuild.fullDisplayName}", body: "Boo, we failed."
		}
            } 
        }
	stage ('Artifactory Deploy'){
		//when {
		//	branch "master"
		//}
		steps{
		//dir("project_templates/java_project_template"){
			script {
				def server = Artifactory.server('artifactory')
				def rtMaven = Artifactory.newMavenBuild()
				rtMaven.resolver server: server, releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot'
				rtMaven.deployer server: server, releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local'
				rtMaven.tool = 'MAVEN_HOME'
				def buildInfo = rtMaven.run pom: 'pom.xml', goals: 'install'
				server.publishBuildInfo buildInfo
				}
			//}
		}
	}
    }
}
