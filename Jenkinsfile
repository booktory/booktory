pipeline {
	agent none
	options { skipDefaultCheckout(false) }
	stages {
		stage('git pull') {
			agent any
			steps {
				mattermostSend (
                        color: "#2A42EE", 
                        message: "Build STARTED: ${env.JOB_NAME} #${env.BUILD_NUMBER} (<${env.BUILD_URL}|Link to build>)"
                )  
				checkout scm
			}
		}
		stage('Docker build') {
			agent any
			steps {
				script {
                    try {
						sh 'docker build -t frontend:latest frontend/'
						sh 'docker build -t backend:latest backend/'
					}catch(e) {
                        mattermostSend (
                                color: "danger", 
                                message: "Build FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER} (<${env.BUILD_URL}|Link to build>)"
                        )
                    } 
				}
			}
		}
		stage('Docker run') {
			agent any
			steps {
				script {
                    try {
						sh 'docker ps -f name=frontend -q | xargs --no-run-if-empty docker container stop'
						sh 'docker ps -f name=backend -q | xargs --no-run-if-empty docker container stop'
				
						sh 'docker container ls -a -f name=frontend -q | xargs -r docker container rm'
						sh 'docker container ls -a -f name=backend -q | xargs -r docker container rm'
				
						sh 'docker images -f dangling=true && docker rmi -f $(docker images -f dangling=true -q)' 

						sh 'docker ps'
						sh 'docker images'

						sh 'docker run -d --name frontend \
						-p 80:80 \
						-v /home/ubuntu/sslkey/:/etc/letsencrypt/live/i5a607.p.ssafy.io/ \
						-v /etc/localtime:/etc/localtime:ro \
						--network booktorycicdnetwork \
						frontend:latest'

						sh 'docker ps'

						sh 'docker run -d --name backend \
						-p 8282:8080 \
						--network booktorycicdnetwork backend:latest'

						sh 'docker ps'
						sh 'docker images'
						
					}catch(e) {
						currentBuild.result = "FAILURE"
                    } finally {
						if(currentBuild.result == "FAILURE"){
							mattermostSend (
                                    color: "danger", 
                                    message: "RUN FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER} (<${env.BUILD_URL}|Link to build>)"
                            )
						}
						else{
							mattermostSend (
                                    color: "good", 
                                    message: "RUN SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER} (<${env.BUILD_URL}|Link to build>)"
                            )
						}
					}
				}
			}
		}
	}
}
