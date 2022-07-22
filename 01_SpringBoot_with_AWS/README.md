# 01_스프링부트와 AWS로 혼자 구현하는 웹 서비스

<스프링 부트와 AWS로 혼자 구현하는 웹 서비스(이동욱 지음)>을 읽으면서 진행하는 토이 프로젝트(클론 코딩)

### 📺시연

(생략)

<br>

## 🛠개발 과정

### ⏳개발 기간

22.06.24. ~ 22.07.05.

- 구글 로그인까지만 구현하고 중단

<br>

### 🧨이슈

- #### jcenter() 지원 중단
  
  - 책에는 jcenter()의 생태계로 많은 개발자들이 옮겨가고 있다고 했지만, 지원이 중단되어 더 이상 쓸 수 없는 상태라 mavenCenter()만 적용함

<br>

- #### build.gradle dependencies 설정
  
  - 책에는 compile(), testCompile()을 사용하였으나, 공식 문서를 참조하니 implementation과 testImplementation을 사용하는 것을 바뀐듯하다

<br>

- #### HelloControllerTest 에러 발생

  - 프로젝트 최상단 디렉토리가 한글명 + 스페이스가 있어서 디렉토리 이름 문제인 줄 알았음


![image](https://user-images.githubusercontent.com/93081720/175773640-540d0704-d608-4a2e-8b5c-4e629f67c1ee.png)

![image](https://user-images.githubusercontent.com/93081720/175773895-f3f3bdbb-d208-47f4-9777-aa2a5f289a99.png)

처음보는 에러 발생

- 해결 => build.gradle에서 test부분 주석처리

![image](https://user-images.githubusercontent.com/93081720/175774316-efc22a45-9905-4c70-9a88-d181a8a36386.png)

<br>

- #### 롬복 호환성 문제 발생

  - 해결 => 구글링을 통해 프로젝트의 JDK버전과 롬복과의 호환성 문제가 원인이라는 것을 알아냈고, build.gradle의 롬복 버전을 1.18.20으로 명시해주니 해결되었다.

![image](https://user-images.githubusercontent.com/93081720/175774938-6cdc3585-e689-4d9a-903d-984cfe240840.png)

<br>

- #### H2 콘솔이 로컬호스트에서 열리지 않음

  - 이전에 했던 셋팅을 따라했는데도 404 에러가 발생함
  - (해결) 구글링을 해보니 해당 내용을 build.gradle에 추가해줘야했음

![image](https://user-images.githubusercontent.com/93081720/175800610-de107dcd-f68b-464d-b9ca-fcb31a96b044.png)

<br>

## 🤔토이 프로젝트 후기

### 💡느낀점

클론 코딩이었지만, 따라하면서 많은 내용을 배웠다. 특히 몇 가지 기본 중에 기본적인 것을 잘 배울 수 있었다.

- Entity에는 Setter 사용을 지양
- DI시에 @Autowired를 사용하지 않고 private final을 통해 주입

아직 완전히 스프링부트에 대해 감은 안 잡혔지만, 지속적으로 코드를 짜고 공부하면서 왜 이렇게 해야하는지, 코딩 플로우 등에 대해 잘 익혀야겠다

<br>

### 🧠배운점

[배운점](https://github.com/siwon-park/Toy_Projects/blob/master/01_SpringBoot_with_AWS/%EB%B0%B0%EC%9A%B4%EC%A0%90.md)

<br>

