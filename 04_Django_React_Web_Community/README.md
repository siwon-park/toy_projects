# 04_Django와 React로 구현하는 웹 커뮤니티

## 📺시연

<br>

## ✏기획

### 🎯기획 의도

Django REST Framework에 대한 감각을 잊지 않고

Django에서 Swagger API를 통해 API 문서 자동화 시도

Django에서 Class Based View 형태로 View 코드 짜보기

Django에서 JWT 토큰 기반 인증 로그인을 구현하고 Oauth2.0 기반의 소셜 로그인 구현하여 프론트와 연결

Kakao Pay API 연결

=> 그래도 해당 기능을 구현하려면 기능의 사용성을 염두한 주제의 프로젝트여야함 

=> 거래 게시판 기능을 가진 웹 사이트

=> 실제 서비스라면 채팅이 있어야 할 것 같지만 채팅은 구현하지 않기로함

### 🏗ERD

![image](https://user-images.githubusercontent.com/93081720/183257226-fe1559cc-fa6f-4f4f-b72a-5a3aaedeee49.png)

<br>

### 🎨와이어 프레임

<br>

### 📑요구 사항

사용자 회원 가입, 로그인, 로그아웃

게시판 글 쓰기, 댓글 달기(CRUD)

<br>

### ✨추가 기능

리액트로 대댓글 기능 구현

<br>

## 🛠개발 과정

### ⏳개발 기간

- 22.0

<br>

### 🗺개발 과정

- 22.07.31 - 프로젝트 초기 세팅 빌드, 개발 환경 설정

#### 🗃백엔드

- JWT는 기존의 `django-rest-framework-jwt`가 아니라, `djangorestframework-simplejwt`를 사용함. 그 이유는 `django-rest-framework-jwt`의 개발자가 더 이상 자신의 라이브러리를 쓰지 말고 simplejwt를 쓰는 것을 권장하였음
- TypeError: Object of type User is not JSON serializable 에러를 해결 못하겠음...
  - user를 직접 json화 하였음
  - 이제 데이터가 잘 들어가야함....

<br>

#### 🖼프론트엔드

<br>

### 🧨이슈

<br>

## 🤔토이 프로젝트 후기

### 💡느낀점

<br>

### 🧠배운점

<br>