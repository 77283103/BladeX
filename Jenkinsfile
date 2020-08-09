// git凭证id
def git_auth = "fdc25f60-4334-4a31-94e7-18ddbed0d9ca"
node {

    stage("拉取代码") {
        checkout([$class: "GitSCM", branches: [[name: "*/${branch}"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: "${git_auth}", url: "http://39.107.82.113:7990/scm/blad/bladex.git"]]])
    }
    stage("所有模块编译、安装到本地仓库") {
        if("${all-module}" == "true"){
            sh "mvn clean install"
        } else {
            echo "不需要重新编译所有模块……"
        }
    }
    stage("blade-common编译、安装到本地仓库") {
        if("${blade-common}"){
            sh "mvn -f blade-common clean install"
        } else {
            echo "blade-common不需要重新编译……"
        }
    }

    stage("blade-user-api编译、安装到本地仓库") {
        if("${blade-user-api}"){
            sh "mvn -f blade-service-api/blade-user-api clean install"
        } else {
            echo "blade-user-api不需要重新编译……"
        }
    }
    stage("blade-system-api编译、安装到本地仓库") {
        if("${blade-system-api}"){
            sh "mvn -f blade-service-api/blade-system-api clean install"
        } else {
            echo "blade-system-api不需要重新编译……"
        }
    }
    stage("blade-scope-api编译、安装到本地仓库") {
        if("${blade-scope-api}"){
            sh "mvn -f blade-service-api/blade-scope-api clean install"
        } else {
            echo "blade-scope-api不需要重新编译……"
        }
    }
    stage("blade-dict-api编译、安装到本地仓库") {
        if("${blade-dict-api}"){
            sh "mvn -f blade-service-api/blade-dict-api clean install"
        } else {
            echo "blade-dict-api不需要重新编译……"
        }
    }
    stage("blade-desk-api编译、安装到本地仓库") {
        if("${blade-desk-api}"){
            sh "mvn -f blade-service-api/blade-desk-api clean install"
        } else {
            echo "blade-desk-api不需要重新编译……"
        }
    }
    stage("blade-contract-api编译、安装到本地仓库") {
        if("${blade-contract-api}"){
            sh "mvn -f blade-service-api/blade-contract-api clean install"
        } else {
            echo "blade-contract-api不需要重新编译……"
        }
    }
    stage("blade-flow-api编译、安装到本地仓库") {
        if("${blade-flow-api}"){
            sh "mvn -f blade-ops-api/blade-flow-api clean install"
        } else {
            echo "blade-flow-api不需要重新编译……"
        }
    }
    stage("blade-resource-api编译、安装到本地仓库") {
        if("${blade-resource-api}"){
            sh "mvn -f blade-ops-api/blade-resource-api clean install"
        } else {
            echo "blade-resource-api不需要重新编译……"
        }
    }
    stage("${project_name}打包") {
        if("${folder_name}" == "bladex"){
            sh "mvn -f ${folder_name} package"
        } else {
            sh "mvn -f ${folder_name}/${folder_name} package"
        }
    }


}
