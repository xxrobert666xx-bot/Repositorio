pipeline {
    agent any

    tools {
        // Deben coincidir con los nombres que configuraste en
        // "Administrar Jenkins -> Global Tool Configuration"
        maven 'Maven'
        jdk 'JDK_17'
    }

    environment {
       // Usa el ID de la credencial que creaste en Jenkins
       SCANNER_TOKEN = credentials('SonarQube_Auth_Token')
    }

    stages {
        stage('Checkout') {
            steps {
                // Obtener el código de GitHub
                git branch: 'main', url: 'https://github.com/xxrobert666xx-bot/Repositorio.git'
            }
        }

        stage('Build & Test') {
            steps {
                // Ejecuta la compilación y las pruebas (clean package)
                // Esto generará el archivo JAR en target/
                echo 'Iniciando compilación y pruebas con Maven...'
                sh 'mvn clean package'
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
               // 'Mi_Servidor_Sonar' debe coincidir con el nombre de tu servidor en la configuración de Jenkins
               withSonarQubeEnv('Sonarqube_jenkins') {
                  sh "mvn sonar:sonar -Dsonar.projectKey=my-java-app -Dsonar.host.url=... -Dsonar.login=${SCANNER_TOKEN}"
               }
            }
        }
                   
        stage('Quality Gate Check') {
           steps {
                 timeout(time: 5, unit: 'MINUTES') {
               // Detiene la pipeline si no pasa el Quality Gate
                 waitForQualityGate abortPipeline: true 
             }
           }
        }
    }
  }
    

    post {
        // Notificaciones o limpieza después de cada ejecución
        always {
            // Ejemplo de notificaciones (requiere plugins)
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
