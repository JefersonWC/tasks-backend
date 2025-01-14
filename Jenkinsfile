pipeline {
    agent any
    stages {
        stage ('Build Backend') {
            steps {
                sh 'mvn clean package -DskipTests=true' //Limpa empacota mas nao executa o testes com -DskipTests=true
            }
        }
        stage ('Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }
        stage ('Sonar Analysis') {
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps {
               withSonarQubeEnv('SONAR_SCANNER') {
                    sh "${scannerHome}/bin/sonar-scanner -e -Dsonar.sonar.projectKey=DeployBack -Dsonar.sonar.host.url=http://localhost:9000 -Dsonar.sonar.login=ad773c2f14ec2b8659f1288f40d507ffaab8e38e -Dsonar.sonar.java.binaries=target -Dsonar.sonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**/Application.java"
                }
            }
        }
        stage ('Quality Gate') {
            steps {
                sleep(10)
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage ('Deploy Backend') {
            steps {
               deploy adapters: [tomcat6(credentialsId: 'tomcat_login', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks-backend', war: 'target/surefire-reports/*.xml' 
            }
        }
        stage ('API Test') {
            steps {
                ls('api-test') {
                git 'https://github.com/JefersonWC/tasks-api-test.git'
                bat 'mvn test' 
                }
            }
        }
        stage ('Deploy Frontend') {
            steps {
                ls('frontend') {
                git 'https://github.com/JefersonWC/tasks-frontend' 
                bat 'mvn clean package'
                deploy adapters: [tomcat6(credentialsId: 'tomcat_login', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks', war: 'target/tasks.war'
                }   
            }
        }
    }
}
