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
						sh 'pwd'
						sh 'ls -a'
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
				
						sh 'docker images -f dangling=true && docker rmi $(docker images -f dangling=true -q)' 

						sh 'docker run -d --name frontend \
						-p 80:80 \
						-p 443:443 \
						-v /home/ubuntu/sslkey/:/etc/letsencrypt/live/i5a607.p.ssafy.io/ \
						-v /etc/localtime:/etc/localtime:ro \
						--network booktorycicdnetwork \
						frontend:latest'

						sh 'pwd'
						sh 'ls -l'

						sh 'docker run -d --name backend \
						--network booktorynetwork backend:latest'

						sh 'pwd'
						sh 'ls -l'
						
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
