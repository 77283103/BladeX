// git凭证id
def git_auth = "fdc25f60-4334-4a31-94e7-18ddbed0d9ca"
node {

    stage('拉取代码') { // for display purposes
    echo "开始拉取代码"
    checkout([$class: 'GitSCM', branches: [[name: "*/${branch}"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: "${git_auth}", url: 'http://39.107.82.113:7990/scm/blad/bladex.git']]])
    }
}
