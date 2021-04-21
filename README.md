# jenkins-operator

Using the jenkins operator to install Jenkins on Kubernetes, it is a Kubernetes native style to install jenkins on Kubernetes. The core
for this style is the Jenkins Pipeline and Caas(**Configuration as Code**). Detail info can be found [github](https://github.com/jenkinsci/kubernetes-operator) and [jenkins operator docs](https://jenkinsci.github.io/kubernetes-operator/).

## Installation

#### Pre-requirement

* A kubernetes cluster(version is 1.11+)
* kubectl version is 1.11+
* [sops with gpg](https://github.com/mozilla/sops)

#### Installation

Enter into the dir you want to install jenkins, for example, installing jenkins with dev environment, replace the ingress info with the actual environment's info, then run the below command

```
$ kustomize build --enable-alpha-plugins . | kubectl -n jenkins apply -f -
```

Checking the pods under jenkins namespace
```
$ kubectl -n jenkins get pods
NAME                                                    READY   STATUS    RESTARTS   AGE
jenkins-jenkins                                         2/2     Running   0          28h
jenkins-operator-5cd7d8887c-f74vg                       1/1     Running   0          25d
seed-job-agent-jenkins-64d6fdd89f-7nbdw                 1/1     Running   0          28h
```

Getting the login url
```
$ kubectl -n jenkins get ing
NAME      CLASS    HOSTS                    ADDRESS       PORTS     AGE
jenkins   <none>   jenkins.dev.devops.com   x.x.x.x   80, 443   25d
```

Getting the init login password
```
$ kubectl -n jenkins get secret jenkins-operator-credentials-jenkins -o yaml | grep password | head -n 1 | awk -F ":" '{print $2}' | base64 -D
```

Login `https://jenkins.dev.devops.com` with username `jenkins-operator` and password obtained in last step.
