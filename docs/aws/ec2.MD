### ec2에 배포하기 (중단이 있는 수동 배포..)
1. aws 콘솔에 로그인 (계정 번호,id,pw 잘 적어두기)
2. AMI 선택해서 ec2 인스턴스 생성
3. 보안그룹 설정 (ssh 접속 pem 키 잘 보관하기, 접근 허용 IP 설정)
   - pem 키 발급: ec2 > 네트워크 및 보안 > 키 페어에서 생성 가능
   - chmod 400 {pem 키 파일}
4. 고정 IP 할당

5. ec2 인스턴스에 접속: `ssh -i {pem key 위치} {유저명}@{ec2 ip address}`
   - ~/.ssh/config에 Host 등록하면 더 간편히 접속 가능: `ssh {config 파일에 등록한 서비스명}`
   - [접속 오류 발생 시 해결방법](https://stackoverflow.com/questions/16918859/ssh-permission-denied-publickey-on-amazon-ec2)
6. [ec2에 JDk 설치](https://pompitzz.github.io/blog/Java/awsEc2InstallJDK11.html), 타임존 변경
7. (RDS 설정됐다 가정, ec2에서 RDS 접근해보기)
8. git 설치, 배포할 레포지토리 clone 
   - `sudo yum install git`
   - `git clone {repo 주소}`

9. 빌드 스크립트 작성
10. 빌드 스크립트 실행하여 배포