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
                   gv.buildImage(env.IMAGE_NAME)

                }
            }
        }
        
    }
}
