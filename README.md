

## Kubernetes測試報告

- 本專案主要針對kubernates的教學進行學習、測試以及紀錄
- 建立Spring boot專案
- 產生docker image
- 使用kubernetes minikube對外溝通

[Tutorials Website](https://kubernetes.io/docs/tutorials)
## Tool Install
- Minikube
```
brew install minikube
brew install kubectl
```

- [Docker](https://docs.docker.com/get-docker/) 

## Usage

1. 下載上述工具
2. 打開Spring boot專案
3. 使用Maven install產生target資料夾HappyDemo-0.0.1-SNAPSHOT.jar
4. 新增docker file(如下)
5. 修改image tag 為自己docker account/app名字(lnc2244/happy-demo)並產生local image
6. push to repository
---
(terminal)
7. 開啟minikube 並create deployment(pod)
```
minikube start
```
![minikube start](https://github.com/lnc0119/k8s-review/blob/8d9ddecc4527b98ffc651b4dc04924f76a67e148/src/images/demo/minikube-start.png)
8. 編輯.yaml檔案以產生deployment/pods(如下)
9. 啟用.yaml檔案
```
kubectl apply -f happy-app-deployment.yaml
```
10. 檢查pods中的application是否正常運作
```
kubectl logs [pods name]
```
![kubectl-logs](https://github.com/lnc0119/k8s-review/blob/8d9ddecc4527b98ffc651b4dc04924f76a67e148/src/images/demo/kubectl-logs.png)
11. 創建Serivce 讓pods可與外部溝通
```
minikube tunnel
kubectl expose deployment happyapp --name=happyapp-service --port --target-pirt=8080 --type=LoadBalancer

```
<<<<<<< HEAD
![kubectl expose](https://github.com/lnc0119/k8s-review/blob/8d9ddecc4527b98ffc651b4dc04924f76a67e148/src/images/demo/kubectl-expose.png)
=======
>>>>>>> 8d9ddecc4527b98ffc651b4dc04924f76a67e148
12. 取得對外溝通localhost
```
minikube service happyapp-service --url 
--> http://127.0.0.1:56506
```
![get url](https://github.com/lnc0119/k8s-review/blob/8d9ddecc4527b98ffc651b4dc04924f76a67e148/src/images/demo/minikube-get-url.png)
13. 在瀏覽器中輸入[localhost]/[springboot url]
![final demo](https://github.com/lnc0119/k8s-review/blob/8d9ddecc4527b98ffc651b4dc04924f76a67e148/src/images/demo/final-demo.png)
![]()




## Docker file
```
FROM openjdk:17-jdk
VOLUME /tmp
COPY target/HappyDemo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## happy-app-deployment.yaml
```
kind: Deployment
apiVersion: apps/v1
metadata:
  name: happyapp
  labels:
    app: happyapp
spec:
  replicas: 2
  selector:
    matchLabels:
      app: happyapp
  template:
    metadata:
      labels:
        app: happyapp
    spec:
      containers:
        - name: happy-demo
          image: lnc2244/happy-demo
          ports:
          - containerPort: 80
```


