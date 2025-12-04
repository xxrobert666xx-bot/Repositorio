pipeline {
    agent any
    tools {

        maven 'Maven'
        jdk 'JDK_17'
    }

    environment {
        SCANNER_TOKEN = credentials('tokenn')
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




