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
		
		script{
			GIT_COMMIT_EMAIL = sh (script: 'git --no-pager show -s --format=\'%ae\'',returnStdout: true).trim()
			//git log -n 1 --pretty=format:'%h
			GIT_COMMIT_HASH = sh (script: 'git log -n 1 --pretty=format:\'%h\'',returnStdout: true).trim()
		}
            }
	    post{
	    always{
			mail to:"pr393314@wipro.com,ch354288@wipro.com", 
			subject:"${currentBuild.fullDisplayName} ${env.STAGE_NAME} Started",
			body: "Jenkins: ${env.BUILD_URL} , GitLab: ${env.GIT_URL} ,  ${env.STAGE_NAME} Started from by ${GIT_COMMIT_EMAIL}."	
		}
 	   success {
			mail to:"pr393314@wipro.com,ch354288@wipro.com", 
			subject:"${currentBuild.fullDisplayName} ${env.STAGE_NAME} SUCCESS",
			body: "Jenkins_Url: ${env.BUILD_URL} , GitLab_Url: ${env.GIT_URL},  ${env.STAGE_NAME} Success."
                }
	    failure{
			mail to:"pr393314@wipro.com,ch354288@wipro.com", 
			subject:"${currentBuild.fullDisplayName} ${env.STAGE_NAME} FAILURE", 
			body: "Jenkins_Url: ${env.BUILD_URL} , GitLab_Url: ${env.GIT_URL},  ${env.STAGE_NAME} Failure."
		}
	     }
        }
	  stage ('Unit Test') {
		steps {
			sh 'echo "### ${STAGE_NAME} Execution Started ###"'
			sh 'echo "Git Commit ID ${GIT_COMMIT}"'
			//git_commit=${GIT_COMMIT}
			script {
				sh "mvn clean jacoco:prepare-agent test jacoco:report -Dv=${GIT_COMMIT_HASH}"
			}
			sh 'echo "### ${STAGE_NAME} Execution Ended ###"'
		}
		post {
		always{
			junit 'target/surefire-reports/**/*.xml' 
		}
                success {
			mail to:"pr393314@wipro.com,ch354288@wipro.com",
			subject:"${currentBuild.fullDisplayName} ${env.STAGE_NAME} SUCCESS",
			body: "Jenkins_Url: ${env.BUILD_URL} , GitLab_Url: ${env.GIT_URL} , ${env.STAGE_NAME} Success."
                }
	    failure{
			mail to:"pr393314@wipro.com,ch354288@wipro.com",
			subject:"${currentBuild.fullDisplayName} ${env.STAGE_NAME} FAILURE", 
			body: "Jenkins_Url: ${env.BUILD_URL} , GitLab_Url: ${env.GIT_URL} , ${env.STAGE_NAME} Failure."
		}
		unstable {
            		mail to:"pr393314@wipro.com,ch354288@wipro.com",
			subject:"${currentBuild.fullDisplayName} ${env.STAGE_NAME} UNSTABLE", 
			body: "Jenkins_Url: ${env.BUILD_URL} , GitLab_Url: ${env.GIT_URL} , ${env.STAGE_NAME} Unstable."
	        }
            }
	}
	stage ('Artifactory Deploy'){
		steps{
			sh 'echo "### ${STAGE_NAME} Started ###"'
			//sh 'mvn clean deploy -Dmaven.test.skip=true -Dv=${BUILD_NUMBER}'
			script {
				//def server = Artifactory.server('artifactory')
				//def rtMaven = Artifactory.newMavenBuild()
				//def commit_hash=${GIT_COMMIT_HASH}
				//echo "${commit_hash}"
				//rtMaven.resolver server: server, releaseRepo:  'Apache-Remote', snapshotRepo: 'libs-snapshot'
				//rtMaven.deployer server: server, releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local'
				//rtMaven.tool = 'maven3'
				//def buildInfo = rtMaven.run pom: 'pom.xml', goals: 'install -Dmaven.test.skip=true -Dv=GIT_COMMIT_HASH'
				//server.publishBuildInfo buildInfo
				
				sh "mvn clean deploy -Dmaven.test.skip=true -Dv=${GIT_COMMIT_HASH}"	
				}
			sh 'echo "### ${STAGE_NAME} Ended ###"'
		}	
		post {
		always{
			mail to:"pr393314@wipro.com,ch354288@wipro.com",
			subject:"${currentBuild.fullDisplayName} ${env.STAGE_NAME} STARTED: ",
			body: "Jenkins_Url: ${env.BUILD_URL} , GitLab_Url: ${env.GIT_URL} , ${env.STAGE_NAME} Started ."
		}
                success {
			mail to:"pr393314@wipro.com,ch354288@wipro.com",
			subject:"${currentBuild.fullDisplayName} ${env.STAGE_NAME} SUCCESS",
			body: "Jenkins_Url: ${env.BUILD_URL} , GitLab_Url: ${env.GIT_URL} , ${env.STAGE_NAME} Success."
                }
		failure{
			mail to:"pr393314@wipro.com,ch354288@wipro.com", 
			subject:"${currentBuild.fullDisplayName} ${env.STAGE_NAME}FAILURE", 
			body: "Jenkins_Url: ${env.BUILD_URL} , GitLab_Url: ${env.GIT_URL} , ${env.STAGE_NAME} Failure."
		}
		unstable {
            		mail to:"pr393314@wipro.com,ch354288@wipro.com", 
			subject:"${currentBuild.fullDisplayName} ${env.STAGE_NAME} UNSTABLE", 
			body: "Jenkins_Url: ${env.BUILD_URL} , GitLab_Url: ${env.GIT_URL} , ${env.STAGE_NAME} Unstable."
	        }
            }
	}

	stage ('Integration Test'){
		steps {
			sh 'echo "### ${STAGE_NAME} Started ###"'
			echo 'Stopping Running Docker Containers if any...!'
			sh 'ansible-playbook -i ansible/hosts ansible/container_stop.yml'
			script {
				sh "ansible-playbook -i ansible/hosts ansible/pull-artifactory.yml --extra-vars 'version=${GIT_COMMIT_HASH}'"
			}
			sh 'ansible-playbook -i ansible/hosts ansible/container_start.yml'
			echo '### Sleeping for 30 secs for Test containers to get stable ###'
			sh 'sleep 30s'
			script {
				sh "mvn clean jacoco:prepare-agent verify  jacoco:report -fn -Dmaven.test.failure.ignore=true -Dmaven.test.error.ignore=true -Dv=${GIT_COMMIT_HASH}"
			}
			sh 'ansible-playbook -i ansible/hosts ansible/container_stop.yml'
			sh 'echo "### ${STAGE_NAME} Ended ###"'
		}
		post {
		always{
			junit 'target/surefire-reports/**/*.xml' 
		}
                success {
			mail to:"pr393314@wipro.com,ch354288@wipro.com",
			subject:"${currentBuild.fullDisplayName} ${env.STAGE_NAME} SUCCESS",
			body: "Jenkins Url: ${BUILD_URL} , GitLab Url: ${GIT_URL} ,  ${env.STAGE_NAME}Success."
                }
		failure{
			mail to:"pr393314@wipro.com,ch354288@wipro.com",
			subject:"${currentBuild.fullDisplayName} ${env.STAGE_NAME} FAILURE", 
			body: "Jenkins Url: ${BUILD_URL} , GitLab Url: ${GIT_URL},  ${env.STAGE_NAME} Failure."
		}
		//unstable {
            	//	mail to:"pr393314@wipro.com,ch354288@wipro.com", 
		//	subject:"${currentBuild.fullDisplayName} ${env.STAGE_NAME} UNSTABLE", 
		//	body: "Jenkins Url: ${BUILD_URL} , GitLab Url: ${GIT_URL},  ${env.STAGE_NAME} Unstable."
	       // }
	    }
	}
	stage ('SonarQube Analysis'){
		steps{
			withSonarQubeEnv('sonarqube') {
				sh 'echo "### ${STAGE_NAME} Started ###"'
				script {
					sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar -Dv=${GIT_COMMIT_HASH}"
				}
				sh 'echo "### ${STAGE_NAME} Ended ###"'
			}
			//timeout(time: 1, unit: 'HOURS') {
               		 //waitForQualityGate abortPipeline: true
			//}
		}
	}

  stage ('Deploy to Dev/Test Environment'){
	when { 
		branch 'feature/*'
	 }
	steps{
		sh 'echo "### ${STAGE_NAME} Started ###"'
		sh 'ansible-playbook -i ansible/hosts ansible/container_stop.yml'
		sh 'ansible-playbook -i ansible/hosts ansible/container_start.yml'
		sh 'echo "### ${STAGE_NAME} Ended ###"'
	}
   post{
	success{
	mail to:"pr393314@wipro.com,ch354288@wipro.com", subject:"${currentBuild.fullDisplayName} ${env.STAGE_NAME} SUCCESS ", body: "Jenkins Url: ${BUILD_URL} , GitLab Url: ${GIT_URL} ,  ${env.STAGE_NAME} Success."

	}
	failure{
	mail to:"pr393314@wipro.com,ch354288@wipro.com", subject:"${currentBuild.fullDisplayName} ${env.STAGE_NAME} FAILURE", body: "Jenkins Url: ${BUILD_URL} , GitLab Url: ${GIT_URL},  ${env.STAGE_NAME}  Failed...!."
	}
   }
}

  stage ('Deploy to Staging Environment'){
	when { 
		expression { return env.BRANCH_NAME == 'develop' || env.BRANCH_NAME == 'release/*' }
	 }
	steps{
		sh 'echo "### ${STAGE_NAME} Started ###"'
		sh 'ansible-playbook -i ansible/hosts ansible/container_stop.yml'
		sh 'ansible-playbook -i ansible/hosts ansible/container_start.yml'
		sh 'echo "### ${STAGE_NAME} Ended  ###"'
	}
   post{
	success{
	mail to:"pr393314@wipro.com,ch354288@wipro.com", subject:"${currentBuild.fullDisplayName} ${env.STAGE_NAME} SUCCESS ", body: "Jenkins Url: ${BUILD_URL} , GitLab Url: ${GIT_URL} ,  ${env.STAGE_NAME} Success."

	}
	failure{
	mail to:"pr393314@wipro.com,ch354288@wipro.com", subject:"${currentBuild.fullDisplayName} ${env.STAGE_NAME} FAILURE", body: "Jenkins Url: ${BUILD_URL} , GitLab Url: ${GIT_URL},  ${env.STAGE_NAME}  Failed...!."
	}
   }
}
  stage ('Deploy to Prod Environment'){
	when { 
		expression { return env.BRANCH_NAME == 'master' || env.BRANCH_NAME == 'hotfix/*' }
	 }
    	steps{
     		 sh 'echo "### ${STAGE_NAME} Started ###"'
		 sh 'ansible-playbook -i ansible/hosts ansible/container_stop.yml'
		 sh 'ansible-playbook -i ansible/hosts ansible/container_start.yml'
		 sh 'echo "### ${STAGE_NAME} End ###"'

    }
   post{
	always{
	mail to:"pr393314@wipro.com,ch354288@wipro.com", subject:"${currentBuild.fullDisplayName} ${env.STAGE_NAME} STARTED ", body: "Jenkins Url: ${BUILD_URL} , GitLab Url: ${GIT_URL} ,  ${env.STAGE_NAME} Success."
	}
	success{
	mail to:"pr393314@wipro.com,ch354288@wipro.com", subject:"${currentBuild.fullDisplayName} ${env.STAGE_NAME} SUCCESS ", body: "Jenkins Url: ${BUILD_URL} , GitLab Url: ${GIT_URL} ,  ${env.STAGE_NAME} Success."
	}

	failure{
	mail to:"pr393314@wipro.com,ch354288@wipro.com", subject:"${currentBuild.fullDisplayName} ${env.STAGE_NAME} FAILURE", body: "Jenkins Url: ${BUILD_URL} , GitLab Url: ${GIT_URL},  ${env.STAGE_NAME}  Failed...!."
	}
   }
  }
    }
}
