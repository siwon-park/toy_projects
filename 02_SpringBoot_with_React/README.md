# 02_리액트, 스프링부트, AWS로 배우는 웹 개발

<React.js, 스프링부트, AWS로 배우는 웹 개발 101(김다정 지음)>을 읽으면서 진행하는 토이 프로젝트(클론 코딩)

### 📺시연

<br>

## 🛠개발 과정

#### ⏳개발 기간

22.07.01. ~

- 22.07.01- 프로젝트 초기 셋업 및 빌드 / 책 개념 정독

<br>

#### 🗺개발 과정

- 프로젝트 초기 셋업; 스프링 이니셜라이저 사용

![image](https://user-images.githubusercontent.com/93081720/176720586-e3bb7139-c5df-40f5-b8ba-7ddce7d65329.png)

<br>



### 🧨이슈

너무 많은 이슈가 있어서 여기에 다 기록을 하지 못함

- 일단 프론트의 경우 책에서는 Class 컴포넌트를 썼으며 전체적으로 코드 작성 방식이 권장하는 형태가 아니었다. 그래서 함수형 컴포넌트로 다시 작성하였다
- Entity에 Setter 메서드를 쓰지 않음으로써 아예 새롭게 빌더 패턴으로 구현했는데 맞는지 모르겠다.

- WebSecurityConfigurerAdapter를 더 이상 사용할 수 없음

- JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted 에러
  - 해결 => SECRET_KEY를 바이트화하였음

  - 나는 postConstruct를 통해 생성 후에 SECRET_KEY를 바이트화 한다고 생각했는데, 그게 아니었다


![image](https://user-images.githubusercontent.com/93081720/179504399-4980764b-d143-4e57-9647-1754d64eca19.png)

해당 코드가 SECRET_KEY를 바이트화 한다고 생각했음

![image](https://user-images.githubusercontent.com/93081720/179499196-bb011cd1-92f1-40e3-b81b-c163ae89bec0.png)

<br>

- react-route-dom
  - 공식적으로 react-router-dom이 버전 6로 업그레이드 되면서, Switch를 더이상 지원을 안하게 되어 export Switch 에러가 발생함
  - Switch를 Routes로 대체함

![image](https://user-images.githubusercontent.com/93081720/179564584-0728fc11-af29-4fa5-8483-5929beead130.png)

- ... is not a <Route> component. All component children of <Routes> must be a <Route> or <React.Fragment> 에러 발생
  - react-router-dom v6부터는
    - Switch 대신 Routes를 사용
    - Route 안에 component 대신 element 사용 => Routes의 자식으로 Route밖에 가질 수 없기 때문

![image](https://user-images.githubusercontent.com/93081720/179567522-2b31c57a-b14a-458a-8767-3295f1629e9e.png)

## 🤔토이 프로젝트 후기

### 💡느낀점

<br>

### 🧠배운점

<br>