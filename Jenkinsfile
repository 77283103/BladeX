// git凭证id
def git_auth = "fdc25f60-4334-4a31-94e7-18ddbed0d9ca"
def harbor_url = "172.17.56.23:8001"
def harbor_project = "blade"
def harbor_auth = "45b7cd95-6feb-438a-8d24-914a58bfc847"
node {

    stage("拉取代码") {
        checkout([$class: "GitSCM", branches: [[name: "*/${branch}"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: "${git_auth}", url: "http://39.107.82.113:7990/scm/blad/bladex.git"]]])
    }
    stage("所有模块编译、安装到本地仓库") {
        // 布尔类型的变量必须用单引号，否则失效
        if("${all_module}" == "true"){
            sh "mvn clean install"
        } else {
            echo "不需要重新编译所有模块……"
        }
    }
    stage("blade-common编译、安装到本地仓库") {
        if("${blade_common}" == "true"){
            sh "mvn -f blade-common clean install"
        } else {
            echo "blade-common不需要重新编译……"
        }
    }

    stage("blade-user-api编译、安装到本地仓库") {
        if("${blade_user_api}" == "true"){
            sh "mvn -f blade-service-api/blade-user-api clean install"
        } else {
            echo "blade-user-api不需要重新编译……"
        }
    }
    stage("blade-system-api编译、安装到本地仓库") {
        if("${blade_system_api}" == "true"){
            sh "mvn -f blade-service-api/blade-system-api clean install"
        } else {
            echo "blade-system-api不需要重新编译……"
        }
    }
    stage("blade-scope-api编译、安装到本地仓库") {
        if("${blade_scope_api}" == "true"){
            sh "mvn -f blade-service-api/blade-scope-api clean install"
        } else {
            echo "blade-scope-api不需要重新编译……"
        }
    }
    stage("blade-dict-api编译、安装到本地仓库") {
        if("${blade_dict_api}" == "true"){
            sh "mvn -f blade-service-api/blade-dict-api clean install"
        } else {
            echo "blade-dict-api不需要重新编译……"
        }
    }
    stage("blade-desk-api编译、安装到本地仓库") {
        if("${blade_desk_api}" == "true"){
            sh "mvn -f blade-service-api/blade-desk-api clean install"
        } else {
            echo "blade-desk-api不需要重新编译……"
        }
    }
    stage("blade-contract-api编译、安装到本地仓库") {
        if("${blade_contract_api}" == "true"){
            sh "mvn -f blade-service-api/blade-contract-api clean install"
        } else {
            echo "blade-contract-api不需要重新编译……"
        }
    }
    stage("blade-flow-api编译、安装到本地仓库") {
        if("${blade_flow_api}" == "true"){
            sh "mvn -f blade-ops-api/blade-flow-api clean install"
        } else {
            echo "blade-flow-api不需要重新编译……"
        }
    }
    stage("blade-resource-api编译、安装到本地仓库") {
        if("${blade_resource_api}" == "true"){
            sh "mvn -f blade-ops-api/blade-resource-api clean install"
        } else {
            echo "blade-resource-api不需要重新编译……"
        }
    }
    stage("${project_name}打包") {
        if("${folder_name}" == "bladex"){
            sh "mvn -f ${project_name} package dockerfile:build"
        } else {
            sh "mvn -f ${folder_name}/${project_name} package dockerfile:build"
        }
        def imageName = "${project_name}:latest"
        sh "docker tag ${imageName} ${harbor_url}/${harbor_project}/${imageName}"
        // 把镜像推送到harbor
        withCredentials([usernamePassword(credentialsId: '${harbor_auth}', passwordVariable: 'password', usernameVariable: 'username')]) {
            // 登录harbor
            sh "docker login -u ${username} -p ${password} ${harbor_url}"
            // 推送镜像
            sh "docker push ${harbor_url}/${harbor_project}/${imageName}"

            sh "echo '镜像推送成功！'"
        }
    }


}
