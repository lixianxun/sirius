# sirius

https://developer.okta.com/blog/2019/04/01/spring-boot-microservices-with-kubernetes

new project:::

spring-boot-msvc


cluster:::
sirius-cluster


connect:::
gcloud container clusters get-credentials sirius-cluster --zone us-central1-c --project spring-boot-msvc


give yourself admin privileges on the cluster:::
kubectl create clusterrolebinding cluster-admin-binding --clusterrole=cluster-admin --user=$(gcloud config get-value core/account)






lixin_pp@cloudshell:~ (spring-boot-msvc)$ gcloud container clusters get-credentials sirius-cluster --zone us-central1-c --project spring-boot-msvc
Fetching cluster endpoint and auth data.
kubeconfig entry generated for sirius-cluster.

lixin_pp@cloudshell:~ (spring-boot-msvc)$ kubectl create clusterrolebinding cluster-admin-binding --clusterrole=cluster-admin --user=$(gcloud config get-value core/account)
Your active configuration is: [cloudshell-1307]
clusterrolebinding.rbac.authorization.k8s.io/cluster-admin-binding created



lixin_pp@cloudshell:~ (spring-boot-msvc)$ kubectl get services --all-namespaces
NAMESPACE      NAME                     TYPE           CLUSTER-IP     EXTERNAL-IP   PORT(S)                                                                                                                                      AGE
default        kubernetes               ClusterIP      10.20.0.1      <none>        443/TCP                                                                                                                                      46m
istio-system   istio-citadel            ClusterIP      10.20.13.182   <none>        8060/TCP,15014/TCP                                                                                                                           34m
istio-system   istio-galley             ClusterIP      10.20.11.157   <none>        443/TCP,15014/TCP,9901/TCP                                                                                                                   34m
istio-system   istio-ingressgateway     LoadBalancer   10.20.2.203    34.69.54.7    15020:31090/TCP,80:30233/TCP,443:30660/TCP,31400:30809/TCP,15029:31279/TCP,15030:31595/TCP,15031:32635/TCP,15032:30322/TCP,15443:32706/TCP   34m
istio-system   istio-pilot              ClusterIP      10.20.13.103   <none>        15010/TCP,15011/TCP,8080/TCP,15014/TCP                                                                                                       34m
istio-system   istio-policy             ClusterIP      10.20.11.158   <none>        9091/TCP,15004/TCP,15014/TCP                                                                                                                 34m
istio-system   istio-sidecar-injector   ClusterIP      10.20.12.133   <none>        443/TCP                                                                                                                                      34m
istio-system   istio-telemetry          ClusterIP      10.20.15.235   <none>        9091/TCP,15004/TCP,15014/TCP,42422/TCP                                                                                                       34m
istio-system   promsd                   ClusterIP      10.20.12.153   <none>        9090/TCP                                                                                                                                     34m
kube-system    default-http-backend     NodePort       10.20.3.96     <none>        80:30359/TCP                                                                                                                                 46m
kube-system    kube-dns                 ClusterIP      10.20.0.10     <none>        53/UDP,53/TCP                                                                                                                                46m
kube-system    metrics-server           ClusterIP      10.20.11.89    <none>        443/TCP                                                                                                                                      46m
lixin_pp@cloudshell:~ (spring-boot-msvc)$




lixin_pp@cloudshell:~ (spring-boot-msvc)$ kubectl get pods --all-namespaces
NAMESPACE      NAME                                                       READY   STATUS      RESTARTS   AGE
istio-system   istio-citadel-58f5d45db8-k8dft                             1/1     Running     0          36m
istio-system   istio-galley-789957bcc9-qphgm                              1/1     Running     0          36m
istio-system   istio-ingressgateway-bf489d5bc-b69qk                       1/1     Running     0          36m
istio-system   istio-pilot-6798bbbbbb-hthfj                               2/2     Running     0          36m
istio-system   istio-policy-84bf65849d-gc8dr                              2/2     Running     3          36m
istio-system   istio-security-post-install-1.2.10-gke.3-9lvlm             0/1     Completed   0          36m
istio-system   istio-sidecar-injector-cb8cb6fb8-mzzmg                     1/1     Running     0          36m
istio-system   istio-telemetry-7d57788966-2vqk8                           2/2     Running     1          36m
istio-system   promsd-b9966d7d5-8httt                                     2/2     Running     1          36m
kube-system    kube-dns-autoscaler-8687c64fc-8ghs2                        1/1     Running     0          46m
kube-system    kube-dns-d699458f6-b6mng                                   4/4     Running     0          46m
kube-system    kube-dns-d699458f6-db97z                                   4/4     Running     0          46m
kube-system    kube-proxy-gke-sirius-cluster-default-pool-8838ccd9-ft2l   1/1     Running     0          46m
kube-system    kube-proxy-gke-sirius-cluster-default-pool-8838ccd9-vlk4   1/1     Running     0          46m
kube-system    kube-proxy-gke-sirius-cluster-default-pool-8838ccd9-vsz1   1/1     Running     0          46m
kube-system    l7-default-backend-8f479dd9-stncj                          1/1     Running     0          46m
kube-system    metrics-server-v0.3.1-5c6fbf777-9kbfn                      2/2     Running     0          46m





lixin_pp@cloudshell:~ (spring-boot-msvc)$ vim deployment-mongo.yml
lixin_pp@cloudshell:~ (spring-boot-msvc)$ kubectl apply -f deployment-mongo.yml
deployment.apps/mongodb created
service/mongodb created
lixin_pp@cloudshell:~ (spring-boot-msvc)$




lixin_pp@cloudshell:~ (spring-boot-msvc)$ kubectl get pods
NAME                       READY   STATUS    RESTARTS   AGE
mongodb-64598595c9-dqfqf   1/1     Running   0          43s
lixin_pp@cloudshell:~ (spring-boot-msvc)$





lixin_pp@cloudshell:~ (spring-boot-msvc)$ git clone https://github.com/lixianxun/sirius.git
Cloning into 'sirius'...
remote: Enumerating objects: 63, done.
remote: Counting objects: 100% (63/63), done.
remote: Compressing objects: 100% (42/42), done.
remote: Total 63 (delta 9), reused 53 (delta 2), pack-reused 0
Unpacking objects: 100% (63/63), done.


git pull
gradle clean build





lixin_pp@cloudshell:~ (spring-boot-msvc)$ gcloud auth configure-docker
WARNING: Your config file at [/home/lixin_pp/.docker/config.json] contains these credential helper entries:

{
  "credHelpers": {
    "gcr.io": "gcloud",
    "us.gcr.io": "gcloud",
    "eu.gcr.io": "gcloud",
    "asia.gcr.io": "gcloud",
    "staging-k8s.gcr.io": "gcloud",
    "marketplace.gcr.io": "gcloud"
  }
}
Adding credentials for all GCR repositories.
WARNING: A long list of credential helpers may cause delays running 'docker build'. We recommend passing the registry name to configure only the registry you are using.
gcloud credential helpers already registered correctly.
lixin_pp@cloudshell:~ (spring-boot-msvc)$


git reset --hard origin/master
git pull
gradle clean build -x test
docker build -t kayak-app:1.0 .
docker tag kayak-app:1.0 gcr.io/spring-boot-msvc/kayak-app:1.0
docker push gcr.io/spring-boot-msvc/kayak-app:1.0

lixin_pp@cloudshell:~/sirius (spring-boot-msvc)$ kubectl apply -f deployment.yml
service/kayak-service unchanged
deployment.extensions/kayak-service configured


ixin_pp@cloudshell:~/sirius (spring-boot-msvc)$  kubectl apply -f istio-gateway.yml
gateway.networking.istio.io/kayak-gateway created
virtualservice.networking.istio.io/kayak-service created



export INGRESS_HOST=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.status.loadBalancer.ingress[0].ip}');
export INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].port}');
echo "$INGRESS_HOST, HTTP PORT=$INGRESS_PORT";

lixin_pp@cloudshell:~/sirius (spring-boot-msvc)$ kubectl get pods
NAME                             READY   STATUS    RESTARTS   AGE
kayak-service-8665b6bdb8-p2w77   1/1     Running   0          15m
mongodb-64598595c9-dqfqf         1/1     Running   0          101m





https://dev-554301-admin.okta.com/admin/app/oidc_client/instance/0oacou2wuHC5kY4044x6/#tab-general
clientid:  0oacou2wuHC5kY4044x6
client secret:  ZNHF7oAfkX2aBdnitBkPUD1_JkES860HxHRHM_1f

https://dev-554301-admin.okta.com/oauth2/default



docker build -t kayak-app-auth:1.0 .
docker tag kayak-app-auth:1.0 gcr.io/spring-boot-msvc/kayak-app-auth:1.0
docker push gcr.io/spring-boot-msvc/kayak-app-auth:1.0;  

#delete the old one 
kubectl delete -f deployment.yml

#update  image
image: gcr.io/spring-boot-msvc/kayak-app-auth:1.0
kubectl apply -f deployment.yml





https://dev-554301.okta.com/oauth2/default/v1/authorize


POST {tokenEndpoint}
Content-Type: application/x-www-form-urlencoded
 
grant_type=authorization_code&
code=XXoRymISq0c0ZhELtNMD&
client_id=0oacou2wuHC5kY4044x6&
client_secret=ZNHF7oAfkX2aBdnitBkPUD1_JkES860HxHRHM_1f&
redirect_uri=https%3A%2F%2Foidcdebugger.com%2Fdebug




---------------------------token generated from api/tokens page
00JtsPsr-F6iJFiaWFSsaRbnMkuyH2TIYWcfnypkg-

http 34.69.54.7/kayaks Authorization:"Bearer eyJraWQiOiJlLTIwUHczaHkxbEt0MnZNekdiV3VPVURIS1VVcnMzOW5ZX3JhLUxjTTZzIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULlhLN043bV9ycHcwZlp0dXVHcVRzcVc4Nlg2NEZhb3hjQ1FhY0lIeEdmQ3ciLCJpc3MiOiJodHRwczovL2Rldi01NTQzMDEub2t0YS5jb20vb2F1dGgyL2RlZmF1bHQiLCJhdWQiOiJhcGk6Ly9kZWZhdWx0IiwiaWF0IjoxNTg5OTgyMDIxLCJleHAiOjE1ODk5ODU2MjEsImNpZCI6IjBvYWNvdTJ3dUhDNWtZNDA0NHg2IiwidWlkIjoiMDB1Y29maWFrZGdNUE5xVnk0eDYiLCJzY3AiOlsib3BlbmlkIl0sInN1YiI6IjEzMDIzMjE4ODE5QDE2My5jb20ifQ.gS8NR0VDu8TVY2Cxs3m0Zut3woyHoyyFFRToNCTudUjSfL2M8iECkW3xTwcj6zeGg80O68OQ4dknF5QTJp4THCrUUk9OHtM8I46hC_hEkIfWnLi5nP75i8w8Y9lkBTVdRACZRBdMduEkEjfbW469HLa_47Ei0b0BZFhabjEqNedmHClE_RbA3u-dZMljlfxbTeF0jTU8DHgZUsa9IY1FktB2cOF6N1mkvYk636mCVCscXKu9UDAMU-_RtbTQ9-uUeOS4gx0a6psT9wXtSDJoYEMvE74RA-zofD3IlyIWaJvSvP8vxnJHUhvGakBMBaAvhEHKmIr-a_SKZy_KmX82fA"


-------------------------------------------------------------------------------------------------------------
(lixianxun) C:\Users\i355880>http 34.69.54.7
HTTP/1.1 503 Service Unavailable
content-length: 19
content-type: text/plain
date: Wed, 20 May 2020 13:22:50 GMT
server: istio-envoy

no healthy upstream


gcloud auth configure-docker