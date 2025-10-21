pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'cart-service'
        DOCKER_TAG = 'latest'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/dhanushgubba/cartservice.git'
            }
        }

        stage('Build Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                }
            }
        }

        stage('Push to DockerHub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub',
                    usernameVariable: 'DOCKER_USERNAME',
                    passwordVariable: 'DOCKER_PASSWORD'
                )]) {
                    script {
                        sh """
                            echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
                            docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} $DOCKER_USERNAME/${DOCKER_IMAGE}:${DOCKER_TAG}
                            docker push $DOCKER_USERNAME/${DOCKER_IMAGE}:${DOCKER_TAG}
                        """
                    }
                }
            }
        }
        stage('Deploy to EC2') {
			steps {
				sshagent(['ec2-ssh-key']) {
					sh """
					ssh -o StrictHostKeyChecking=no ubuntu@43.204.22.237 '
                    docker pull dhanushgubba/cart-service:latest &&
                    docker stop cart-service || true &&
                    docker rm cart-service || true &&
                    docker run -d -p 8085:8085 --name cart-service dhanushgubba/cart-service:latest
                	'
           		 	"""
        		}
    		}
		}
    }
}
