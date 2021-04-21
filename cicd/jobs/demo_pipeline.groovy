#!/usr/bin/env groovy

pipelineJob('Demo') {
    displayName('Demo')

    logRotator {
        numToKeep(10)
        daysToKeep(30)
    }


    parameters {
        choiceParam('ENV', ['SVT1', 'SBX2'])
        stringParam('Release', 'v1.0.0', 'Please input your release number!')
    }

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('your-github-repo-url')
                        credentials('your-github-ssh-key')
                    }
                    branches('*/master')
                }
            }
            scriptPath('cicd/pipelines/demo.groovy')
        }
    }
}
