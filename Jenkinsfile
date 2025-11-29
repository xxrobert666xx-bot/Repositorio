pipeline {
    agent any

    tools {
        // Deben coincidir con los nombres que configuraste en
        // "Administrar Jenkins -> Global Tool Configuration"
        maven 'Maven'
        jdk 'JDK_17'
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