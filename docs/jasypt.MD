- 참고 자료
    - [만든사람 github](https://github.com/ulisesbocchio/jasypt-spring-boot)

- [jasypt 암/복호화 사이트](https://www.devglan.com/online-tools/jasypt-online-encryption-decryption)

### 개요
- Jasypt(Java Simplified Encryption)-spring-boot
    - property를 암호화할 수 있게 해준다고 함.

- 사용 이유
    - 설정 파일에 민감한 정보 암호화하기 위해

### 사용 방법
1. 의존성
  ```groovy
  implementation group: 'com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.4'
  ```

2. encryptor 객체를 만들고 bean으로 등록


3. 암호화 secret key 정하고, 암호화한 문자열 프로퍼티 파일에 등록
    - `ENC(암호화 문자열)` <br/>
    - 실제 운영시 secret key는 환경변수로 줘서 노출되지 않도록 해야 함..!


4. 사용하기
    - @Value나 환경변수를 이용해 값을 가져오면 복호화된 값이 가져와짐.


### 주의
- encryptor 객체의 암호화 알고리즘과 실제 암호화를 수행한 알고리즘이 동일해야 복호화가 됨.
  - [관련 이슈 | stackoveflow - Unable to decrypt error](https://stackoverflow.com/questions/59590633/unable-to-decrypt-error-using-jasypt-with-spring-boot)