#!/usr/bin/env groovy

def gv
pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    environment {
        IMAGE_NAME = 'ppornambiga/demo-app:java-maven-2.0'
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }   
        stage('build app') {
            steps {
               script {
                  echo 'building application jar...'
                  gv.buildJar()
               }
            }
        }
        stage('build image') {
            steps {
                script {
                   echo 'building docker image...'
                   gv.buildImage()

                }
            }
        }
        stage('deploy') {
            steps {
                script {
                   echo 'deploying docker image to EC2...'

                   def shellCmd = "bash ./server-cmds.sh ${IMAGE_NAME}"
                   def ec2Instance = "ubuntu@13.233.12.225"
                    def dockercmd = "docker run -p 3080:3080 -d ${IMAGE_NAME}"

                   sshagent(['ec2-server-key']) {

                       sh "ssh -o StrictHostKeyChecking=no ${ec2Instance} ${dockercmd}"
                   }
                }
            }
        }
     }             
        
    }

