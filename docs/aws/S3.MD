- https://www.baeldung.com/aws-s3-java

### 사용법
1. 의존성 설정
  `implementation 'com.amazonaws:aws-java-sdk-s3:1.12.340'`

2. aws 계정 준비

3. s3 서비스에 접근하기 위한 자바 클라이언트 구성
    ```java
   // baeldung에서는 이런식으로 하면 된다고 함
    AWSCredentials credentials = new BasicAWSCredentials(
      "<AWS accesskey>", 
      "<AWS secretkey>"
    );
    
    AmazonS3 s3client = AmazonS3ClientBuilder
    .standard()
    .withCredentials(new AWSStaticCredentialsProvider(credentials))
    .withRegion(Regions.US_EAST_2)
    .build();
    ```

4. 이제 aws s3 bucket을 다룰 수 있음


### 문제, 개선이 필요한 부분 
- s3를 read에 대해 public access를 허용하면 보안상 위험한 이유는?
  - 예상가는건 요청 너무 많이해서 먹통되게 하는 방법(DOS ?) 때문?

- swagger 접근 시 예외 발생

- 비밀 정보 암호화 방법 개선 필요

- 파일 저장 위치 설정 필요

- static 필드에 프로퍼티 주입
  - https://www.baeldung.com/spring-inject-static-field