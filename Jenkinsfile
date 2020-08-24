/*
 	blade-auth@8100,blade-gateway@8080,blade-user@8102,blade-system@8106,blade-desk@8105,blade-contract@8107,blade-log@8103,blade-resource@8010,blade-flow@8008,blade-bpmnjs-design@9008
 	认证中心,网关,用户中心,系统管理,工作台,合同管理,日志管理,资源中心,工作流,流程设计器
 	blade-common,blade-flow-api,blade-resource-api,blade-contract-api,blade-desk-api,blade-dict-api,blade-scope-api,blade-system-api,blade-user-api
 */
// git凭证id
// def git_auth = "fdc25f60-4334-4a31-94e7-18ddbed0d9ca"
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
                "${currentInstallName}" == "blade-user-api"){

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

            sh "echo 检查${currentName}容器是否存在"
            // 查询容器是否存在，存在则删除
            def existContainerId = sh (script:"docker ps -a | grep -w ${currentName}:2.5.0.RELEASE | awk \'{print \$1}\'", returnStdout:true)
            if("${existContainerId}"){
                sh "docker stop ${existContainerId}"
                sh "docker rm ${existContainerId}"
                sh "echo 成功删除容器"
            } else{
                sh "echo ${currentName}容器不存在"
            }

            // 查询镜像是否存在，存在则删除
            sh "echo 检查${currentName}镜像是否存在"
            def existImageId = sh (script:"docker images | grep -w ${currentName} | awk \'{print \$3}\'", returnStdout:true)
            if("${existImageId}"){
                sh "docker rmi ${existImageId}"
            }

            sh "echo ${currentName}开始打包"
            // 打包并推送镜像
            if("${currentName}" == "blade-auth" || "${currentName}" == "blade-gateway"){
                sh "mvn -f ${currentName} clean package dockerfile:build"
            }
            else if("${currentName}" == "blade-bpmnjs-design" ||
                 "${currentName}" == "blade-flow" ||
                 "${currentName}" == "blade-log" ||
                 "${currentName}" == "blade-resource"){

                sh "mvn -f blade-ops/${currentName} clean package dockerfile:build"
            }
            else if("${currentName}" == "blade-contract" ||
                "${currentName}" == "blade-desk" ||
                "${currentName}" == "blade-system" ||
                "${currentName}" == "blade-user"){

                sh "mvn -f blade-service/${currentName} clean package dockerfile:build"
            }

            sh "echo ${currentName}打包并镜像推送成功"

            // 启动镜像

            sh "docker run -d -p ${currentPort}:${currentPort} -m 512m -e \"SPRING_PROFILES_ACTIVE=test\" ${currentName}:2.5.0.RELEASE"
            sh "echo ${currentName}镜像启动成功"
        }

        // ============向harbor仓库推送镜像，暂时不需要===================

        /*
        def imageName = "${project_name}:${tag}"
        sh "docker tag ${imageName} ${harbor_url}/${harbor_project}/${imageName}"
        // 把镜像推送到harbor
        withCredentials([usernamePassword(credentialsId: "${harbor_auth}", passwordVariable: 'password', usernameVariable: 'username')]) {
            // 登录harbor
            sh "docker login -u ${username} -p ${password} ${harbor_url}"
            // 推送镜像
            sh "docker push ${harbor_url}/${harbor_project}/${imageName}"
            sh "echo '镜像推送成功！'"
        }
        */
    }


}
