// git凭证id
def git_auth = "fdc25f60-4334-4a31-94e7-18ddbed0d9ca"
def harbor_url = "172.17.56.23:8001"
def harbor_project = "blade"
def harbor_auth = "45b7cd95-6feb-438a-8d24-914a58bfc847"
def tag = "2.5.0.RELEASE"

// 参数化构建时，参数名不能用'-'，要用'_'。否则会报错
// ${变量名} 引用变量时不能用单引号，用双引号
node {
    // 选择需要部署的项目名称
    def selectedProjectNames = "${project_name}".split(",")
    // 选择需要重新依赖的项目名称
    def selectedInstallProjectNames = "${install_project_name}".split(",")

    stage("拉取代码") {
        checkout([$class: "GitSCM", branches: [[name: "*/${branch}"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: "${git_auth}", url: "http://39.107.82.113:7990/scm/blad/bladex.git"]]])
    }
    stage("编译、安装到本地仓库") {
        for(int i=0; i<selectedInstallProjectNames.length; i++){
            def currentInstallName = selectedInstallProjectNames[i];
            sh "echo '${currentInstallName}编译！'"
            if("${currentInstallName}" == "blade-common"){

                sh "mvn -f blade-common clean install"

            }else if("${currentInstallName}" == "blade-flow-api" || "${currentInstallName}" == "blade-resource-api"){

                sh "mvn -f blade-ops-api/${currentInstallName} clean install"

            }else if("${currentInstallName}" == "blade-contract-api" ||
                "${currentInstallName}" == "blade-desk-api" ||
                "${currentInstallName}" == "blade-dict-api" ||
                "${currentInstallName}" == "blade-scope-api" ||
                "${currentInstallName}" == "blade-system-api" ||
                "${currentInstallName}" == "blade-user-api")

                sh "mvn -f blade-service-api/${currentInstallName} clean install"

            }
        }
    }


    stage("项目打包，推送镜像") {

        for(int i=0; i<selectedProjectNames.length; i++){
            def currentProjectInfo = selectedProjectNames[i];
            // 当前项目名称
            def currentName = "${currentProjectInfo}".split("@")[0]
            // 当前项目端口
            def currentPort = "${currentProjectInfo}".split("@")[1]
            //
            if("${currentName}" == "blade-auth" || "${currentName}" == "blade-gateway"){
                sh "mvn -f ${currentName} package dockerfile:build"
            }
            else if("${currentName}" == "blade-bpmnjs-design" ||
                 "${currentName}" == "blade-flow" ||
                 "${currentName}" == "blade-log" ||
                 "${currentName}" == "blade-resource"){

                sh "mvn -f blade-ops/${currentName} package dockerfile:build"
            }
            else if("${currentName}" == "blade-contract" ||
                "${currentName}" == "blade-desk" ||
                "${currentName}" == "blade-system" ||
                "${currentName}" == "blade-user"){

                sh "mvn -f blade-service/${currentName} clean install"
            }

        }

        sh "docker images"

        // ============向harbor仓库推送镜像，暂时不需要===================

 //       def imageName = "${project_name}:${tag}"
//        sh "docker tag ${imageName} ${harbor_url}/${harbor_project}/${imageName}"
        // 把镜像推送到harbor
//        withCredentials([usernamePassword(credentialsId: "${harbor_auth}", passwordVariable: 'password', usernameVariable: 'username')]) {
            // 登录harbor
//            sh "docker login -u ${username} -p ${password} ${harbor_url}"
            // 推送镜像
//            sh "docker push ${harbor_url}/${harbor_project}/${imageName}"
//            sh "echo '镜像推送成功！'"
//        }
    }


}
