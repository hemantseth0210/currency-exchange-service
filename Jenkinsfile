pipeline {
   agent any

   tools {
      // Install the Maven version configured as "M3" and add it to the path.
      maven "M3"
   }

   stages {
      stage('SCM Checkout'){
          steps {
            git branch: 'master', url: 'https://github.com/hemantseth0210/currency-exchange-service.git'  
          }
      }
      stage('Build') {
         steps {
            sh "mvn -version"
            sh "mvn clean install"
         }
      }
      stage('Build Docker Image'){
          steps {
              sh 'docker build -t hemantseth0210/currency-exchange-service:1.0.${BUILD_NUMBER} .'
          }
      }
      stage('Push Docker Image'){
          environment {
                DOCKER_HUB_LOGIN = credentials('docker-hub')  
          }
          steps {
              sh 'docker login --username=$DOCKER_HUB_LOGIN_USR --password=$DOCKER_HUB_LOGIN_PSW'
              sh 'docker push hemantseth0210/currency-exchange-service:1.0.${BUILD_NUMBER}'
          }
      }
      stage('Deploy to cluster') {
         steps {
            echo 'Login to cluster'
            sh 'gcloud container clusters get-credentials currency-conversion-cluster --zone us-east1-b --project hemant-seth-0210'
            echo 'Deploy to cluster'
            sh 'kubectl set image deployment/currency-exchange-deployment currency-exchange=hemantseth0210/currency-exchange-service:1.0.${BUILD_NUMBER} --namespace demo'
         }
      }
   }
}