# nginx 설치
- [aws AMI2에 nginx 설치하는 방법](https://longtermsad.tistory.com/11)
```shell
$ sudo amazon-linux-extras install -y nginx1
$ nginx -v
$ sudo service nginx start
# 접속 테스트. nginx 페이지 서빙되면 잘 설정된거인
$ curl [IP address]  또는 해당 주소 접근
```
- [what is cURL? | IBM](https://developer.ibm.com/articles/what-is-curl-command/)

# tutorials
- 참고: https://nginx.org/en/docs/beginners_guide.html

### 1. Serving Static Content

 