# nginx 설치
- [aws AMI2에 nginx 설치하는 방법](https://longtermsad.tistory.com/11)
```shell
$ sudo amazon-linux-extras install -y nginx1
$ nginx -v
$ sudo service nginx start
# 접속 테스트. nginx 페이지 서빙되면 잘 설정된거인
$ curl [IP address]  또는 해당 주소 접근 또는 nginx 로그 확인
```
- [what is cURL? | IBM](https://developer.ibm.com/articles/what-is-curl-command/)

# tutorials
- 참고: https://nginx.org/en/docs/beginners_guide.html

### 0. 기본 정보, 명령어
- 서버 인스턴스: aws AMI 2 이미지로 만듦.
- nginx 설정 파일 위치: `/etc/nginx/nginx.conf`
- nginx 시작: `sudo nginx`
- nginx가 기동 여부를 프로세스로 확인: `ps -ef | grep nginx`
- nginx 재시작: `sudo nginx -s reload`
- nginx 로그 확인: `/var/log/nginx` 아래에서 `access.log`, `error.log` 확인 가능
- nginx 설정 파일 문법 테스트 `nginx -t`
- 로그 확인 명령을 alias로 등록하기: 예시) `alias nginx-access-log='tail -n 20 var/log/nginx/access.log' `

### 1. Serving Static Content
- 웹 서버의 주요 기능 중 하나는 정적 파일 서빙

```shell
   server{
        location / { # 요청 URL이 /로 시작하는 경우를 처리
          root /data/www;
        }

        location /images/ { # 요청 URL이 /images/로 시작하는 경우를 처리
          root /data; # 서버는 ~/data/images 아래에서 파일을 찾음!
        }
        # listen 포트를 설정하지 않으면 http 표준 포트인 80을 리스닝함.
    }
```
### 2. Setting Up a Simple Proxy Server
- nginx는 proxy 서버로도 자주 사용된다고 함.
  - 즉, 요청을 받아서 proxied 서버로 전달하고, 응답을 받아서 유저에게 전달.

```shell
    server{
        location / {
         # root /data/www;
          proxy_pass http://localhost:8080;
        }

       # location /images/ {
       #   root /data;
       # }

        #todo: 정규식 이해 안 됨.
        # 이렇게 설정하면 요청 URL이 .gif 이런식으로 끝나면 서버의 /data/images 디렉토리 아래에서 파일을 찾는다고 함. 
        location ~ \.(gif|jpg|png)$ { 
          root /data/images;
        }
    }

    server {
        listen 8080;
        root /data/upl;

        location / {
        }
    }
}
```

### 3. Setting Up FastCGI Proxying
- FastCGI..? 생략.


# 이슈
- nginx 설정하고 나서 swagger 페이지 접근 안되고 있음