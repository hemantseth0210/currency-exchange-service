pipeline {
   agent any

   //tools {
      // Install the Maven version configured as "M3" and add it to the path.
      //maven "M3"
   //}
   
   stages {
      /*
      stage('SCM Checkout'){
          steps {
            git branch: 'master', url: 'https://github.com/hemantseth0210/currency-exchange-service.git'  
          }
      }
      */
      stage('Build') {
         steps {
             withMaven(maven: 'M3') {
                sh "mvn -version"
                sh "mvn -B -DskipTests clean package"   
             }
         }
      }
      stage('Test') {
            steps {
                withMaven(maven: 'M3') {
                    sh "mvn test"
                }
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
      }
      stage("SonarQube Analysis") {
      	 steps {
      	      withMaven(maven: 'M3') {
      	           withSonarQubeEnv('sonarqube-server') {
            	        sh 'mvn clean package sonar:sonar'
                   }
      	      }
         }
      }
      stage("Quality Gate") {
          steps {
              timeout(time: 1, unit: 'HOURS') {
                  waitForQualityGate abortPipeline: true
                }
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
          	  echo 'Login to Docker Hub'	
              sh 'docker login --username=$DOCKER_HUB_LOGIN_USR --password=$DOCKER_HUB_LOGIN_PSW'
              echo 'Push the image to Docker Hub'
              sh 'docker push hemantseth0210/currency-exchange-service:1.0.${BUILD_NUMBER}'
          }
      }
      stage('Deploy to GKE Cluster') {
         steps {
            echo 'Login to cluster'
            sh 'gcloud container clusters get-credentials currency-conversion-cluster --zone us-east1-b --project hemant-seth-0210'
            echo 'Deploy to cluster'
            sh 'kubectl set image deployment/currency-exchange-deployment currency-exchange=hemantseth0210/currency-exchange-service:1.0.${BUILD_NUMBER} --namespace demo'
         }
      }
     }
     post {
        success {
            echo 'Email notification being sent on build success'
            emailext body: "SUCCESS: Job ${env.JOB_NAME} ${env.BUILD_NUMBER} \n  Check console output at ${env.BUILD_URL}", 
            recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], 
            subject: "Jenkins Build SUCCESS: Job ${env.JOB_NAME}"
        }
        failure {
            echo 'Email notification being sent on build failure'
            emailext body: "FAILED: Job ${env.JOB_NAME} ${env.BUILD_NUMBER} \n  Check console output at ${env.BUILD_URL}", 
            recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], 
            subject: "Jenkins Build FAILED: Job ${env.JOB_NAME}"
        }
    }
}