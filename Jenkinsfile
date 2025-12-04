pipeline {
    agent any
    tools {

        maven 'Maven'
        jdk 'JDK_17'
    }

    environment {
        SCANNER_TOKEN = credentials('tokenn')
        DOCKER_IMAGE_NAME = 'ventas_bolivarr' 
        APP_PORT = '8000'
        CONTAINER_NAME = 'ventas_bolivarr'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/xxrobert666xx-bot/Repositorio.git'
            }
        }

        stage('Build & Test') {
            steps {
                echo 'Iniciando compilación y pruebas con Maven...'
                sh 'mvn clean package'
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
               withSonarQubeEnv('Sonarqube_jenkins') {
                    sh "mvn sonar:sonar -Dsonar.projectKey=my-java-app -Dsonar.login=$SCANNER_TOKEN"
               }
            }
        }
                    
        stage('Quality Gate Check') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true 
                }
            }
        }

        stage('Docker Build and Deploy Local') {
            steps {
                echo 'Construyendo imagen Docker localmente...'
                sh "docker build -t ${DOCKER_IMAGE_NAME}:${env.BUILD_NUMBER} ."
                sh "docker tag ${DOCKER_IMAGE_NAME}:${env.BUILD_NUMBER} ${DOCKER_IMAGE_NAME}:latest"
                echo "Desplegando contenedor Docker ${DOCKER_IMAGE_NAME}:${env.BUILD_NUMBER}..."
                sh "docker stop ${CONTAINER_NAME} || true" 
                sh "docker rm ${CONTAINER_NAME} || true" 
                sh "docker run -d --name ${CONTAINER_NAME} -p ${APP_PORT}:${APP_PORT} ${DOCKER_IMAGE_NAME}:latest"
                echo "Contenedor ${DOCKER_IMAGE_NAME}:latest desplegado en el puerto ${APP_PORT}."
            }
        }
   
    } 

    post {
        always {
            echo 'Pipeline finalizada. Revisar estado.'
        }
        success {
            echo 'CI/CD completado exitosamente.'
        }
        failure {
            echo '¡ERROR! La etapa de despliegue falló.'
        }
    }

}








