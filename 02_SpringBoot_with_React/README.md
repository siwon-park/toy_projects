# 02_리액트, 스프링부트, AWS로 배우는 웹 개발

<React.js, 스프링부트, AWS로 배우는 웹 개발 101(김다정 지음)>을 읽으면서 진행하는 토이 프로젝트(클론 코딩)

### 📺시연

![springboot_with_react_test](https://user-images.githubusercontent.com/93081720/180462382-fdab2b4a-1691-4ec5-8d5f-a7e4c2871afb.gif)

- jwt토큰을 성공적으로 localStorage에 저장했고, 로그아웃할 때도 성공적으로 삭제해주었음
- TodoList를 새로 작성하고, 체크 및 수정한 다음 새로 고침을 해도 데이터가 그대로 유지되고 있음 

<br>

## 🛠개발 과정

#### ⏳개발 기간

22.07.01. ~ 22.07.22

<br>

#### 🗺개발 과정

##### 🗃백엔드

- 프로젝트 초기 셋업; 스프링 이니셜라이저 사용

![image](https://user-images.githubusercontent.com/93081720/176720586-e3bb7139-c5df-40f5-b8ba-7ddce7d65329.png)

<br>

##### 🖼프론트엔드

리액트, MUI 사용

<br>

### 🧨이슈

#### 너무 많은 이슈가 있어서 여기에 다 기록을 하지 못함 => 큰 이슈만 등록함

- ##### WebSecurityConfigurerAdapter를 더 이상 사용할 수 없음 => 필터체인을 만들고 직접 Bean으로 등록해줌


![image](https://user-images.githubusercontent.com/93081720/180457189-fd5ee0cc-76e2-4ecb-9ac1-cdf14c04c302.png)

- ##### JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted 에러

  - 해결 => SECRET_KEY를 바이트화하였음

  - 나는 @postConstruct를 통해 생성 후에 SECRET_KEY를 바이트화 한다고 생각했는데, 그게 아니었다(@postConstruct를 사용하는 방법을 쓰지않고 다른 방법으로 변경해서 JWT를 받아옴)


![image](https://user-images.githubusercontent.com/93081720/179504399-4980764b-d143-4e57-9647-1754d64eca19.png)

해당 코드가 SECRET_KEY를 바이트화 한다고 생각했음. 그러나 setSigningKey를 입력할 때도 바이트화된 secretKey값을 넣어줘야함

![image](https://user-images.githubusercontent.com/93081720/180455159-e046c2ba-6966-4482-a40b-e9d8692d548e.png)

<br>

- ##### react-route-dom
  
  - 공식적으로 react-router-dom이 버전 6로 업그레이드 되면서, Switch를 더이상 지원을 안하게 되어 export Switch 에러가 발생함
  - Switch를 Routes로 대체함

![image](https://user-images.githubusercontent.com/93081720/179564584-0728fc11-af29-4fa5-8483-5929beead130.png)

- ... is not a <Route> component. All component children of <Routes> must be a <Route> or <React.Fragment> 에러 발생
  - react-router-dom v6부터는
    - Switch 대신 Routes를 사용
    - Route 안에 component 대신 element 사용 => Routes의 자식으로 Route밖에 가질 수 없기 때문

![image](https://user-images.githubusercontent.com/93081720/179567522-2b31c57a-b14a-458a-8767-3295f1629e9e.png)

<br>

- ##### JWT 인증 문제

  - 분명히 JWT를 로그인했을 때는 JWT를 잘 받아왔는데, TodoList를 조회할 때 자꾸만 백엔드 서버에서 JWT의 형태가 맞지않다는 malFormedException이 발생하고 있었음 => String이 2개여야한다는데, 자꾸 4개를 받아온다고 나와있었다.
  - 3일 간 해결을 하지 못하다가, 백에서 System.out.println을 찍고, 프론트에서도 console.log를 해서 파악한 결과 call함수를 호출했을 때, headers에 Authorization을 설정하는 부분에서 문제가 있었다.
  - 분명 책에는 header.append를 쓰면 되는 것처럼 나왔는데, 실제 동작과정을 보니까 커스텀한 jwt인증 서블렛 필터가 2번씩 호출되어, 처음에 jwt를 받아올 땐 정상적으로 받아오는데, 두번째 받아올 때엔 append이니까 Authorization이 2번 들어가서 jwt + jwt 인 문자열로 받아오고 있었다
  - 그래서 accessToken에 대한 Authorization을 지정해주는 부분에서 아예 그냥 다시 생성하는 방향으로 하니 해결되었다.

![image](https://user-images.githubusercontent.com/93081720/180454813-171b3ea7-8d4e-4229-af94-c4dd96de5dd7.png)

<br>

## 🤔토이 프로젝트 후기

### 💡느낀점

클론 코딩을 할 목적으로 시작했지만 클론 코딩 + 자체 개발을 하였던 것 같다.

처음 써보는 프레임워크를 사용해서 리액트(프론트)와 스프링부트(백엔드) 통합 프로젝트를 구현했다는데 의의를 둔다.

<br>

#### 책의 장점

개념적으로는 내용이 좋다고 느꼈다.

#### 책의 단점

그러나 심각할 정도로 클론 코딩에 좋지 않다. 

- 21년도 하반기에 출간된 책임에도 불구하고 옛날 형태의 코딩 스타일을 고수하고 있다.

  - 백엔드의 경우 private final이 아닌 @Autowired를 통해서 DI를 해주고 있었다.
    - 하도 버그가 많아서 레딧에 갔는데 다른 스프링부트 코드의 @Autowired를 통해 생성자를 주입하는 코딩 스타일에 대해서 계속 지적하는 것으로 보아 기본 중에 기본도 안 됐다고 생각한다 .
  - 백엔드 api의 경우, Todo를 생성, 수정하는 url이 http request만 POST, PUT으로 다르고 url이 같다.
    - 엄격하게 RESTful하게 설계하려면 api에 CRUD가 드러나지 않아야 한다고 들었던 것 같은데, 그래도 이건 좀 아닌 것 같다는 생각이 들었다 => 생각 없이 따라치다가 어? 하는 순간 깨달았다.
  - Entity에 Setter를 사용하고 있다.
    - Entity는 DB테이블과 맵핑되는 객체로서 가급적이면 Setter 사용을 지양해야한다.
    - 그래서 Entity에 Setter 메서드를 쓰지 않고 Setter를 써서 데이터를 수정하려고 할 때 빌더 패턴으로 새로운 데이터를 담은 객체를 생성하는 방식으로 구현했는데 맞는지 모르겠다.

  - 프론트의 경우 Class 컴포넌트를 사용해서 코딩을 하고 있었다.
    - 전부 작성하면서 함수형으로 바꿔서 동작하게끔 구현하였다.
  - 그외에 다른 코딩 스타일도 약간 옛날 방식이 아닌가 싶었다. fetch나 call에 있어서도.

<br>

### 🧠배운점

책이 좋진 않았지만, 물론 버그를 수정하는 과정에서 많이 배웠다.

[배운점 정리](https://github.com/siwon-park/Toy_Projects/blob/master/02_SpringBoot_with_React/%EB%B0%B0%EC%9A%B4%EC%A0%90.md)

<br>