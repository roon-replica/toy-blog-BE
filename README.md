### 목표
- pinterest와 비슷한 웹 기반 블로그 서비스 제공
- 개발부터 배포까지 진행 예정
- 개발, 배포, 협업 연습

### 사용
- URIs
  - [nginx 정적 파일 서빙 테스트](http://ec2-43-200-196-233.ap-northeast-2.compute.amazonaws.com/www/test.html)
  - [nginx 프록시 서버 역할 테스트](http://ec2-43-200-196-233.ap-northeast-2.compute.amazonaws.com/www/test.html): not working...
  - [API 문서 확인하기](http://ec2-43-200-196-233.ap-northeast-2.compute.amazonaws.com/swagger-ui.html) : not working...
  
### BE 개발 스택
- java 11+
- spring framework
  - springboot
  - spring mvc
  - spring data jpa
  - spring security
  - spring integration
- gradle
- mysql
- aws
  - ec2
    - tomcat
    - nginx (file serving, reverse proxy?)
  - RDS
    - mariaDB
  - S3
- swagger UI



