def label = "jnlp-${UUID.randomUUID().toString()}"

podTemplate(
        label: label,
        serviceAccount: "jenkins",
        namespace: "jenkins",
        containers: [
                containerTemplate(
                        name: 'jnlp',
                        image: 'gbyukg/docker-jnlp-slave:1.0',
                        args: '${computer.jnlpmac} ${computer.name}'
                )
        ]
) {
    node(label) {
        container('jnlp'){
            stage("Hello World"){
                println "This is xiaomage, focus on Cloud Native DevSecOps!!!"
            }
        }
    }
}
