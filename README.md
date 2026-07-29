# kosa-spring-boot

Using Spring Boot in 2024  
📌:  KOSA 전자정부 표준 프레임워크 기반 공공프로젝트 개발 전문가 양성과정 - Spring Boot 실습 프로젝트 모음

[![Last Commit](https://img.shields.io/github/last-commit/FlounderAround/kosa-spring-boot)](https://github.com/FlounderAround/kosa-spring-boot/commits/main)

## 🚀 Introduction
KOSA 전자정부 표준 프레임워크 기반 공공프로젝트 개발 전문가 양성과정에서 진행한 Spring Boot 실습 프로젝트 모음입니다. REST API 설계, 인증/인가(JWT, Spring Security, OAuth2), MyBatis/JPA 연동 등 실무에서 자주 쓰이는 주제를 독립된 프로젝트 단위로 구성했습니다.

---

## ✨ Features

* 📂 **독립 프로젝트 구조**
  주제별로 4개의 독립된 Spring Boot(Maven) 프로젝트로 구성되어 각각 실행 및 테스트 가능

* 🔐 **JWT 인증 (jwt)**
  `JwtAuthenticationFilter`, `JwtAuthorizationFilter`를 통한 JWT 기반 로그인/인가 처리, Spring Security와 연동한 커스텀 필터 체인 실습 (H2, JPA)

* 🍽️ **레스토랑 API (restaurant)**
  메뉴/리뷰 등록 및 조회 기능을 갖춘 REST API 서버, MyBatis 매퍼 기반 데이터 처리와 Swagger(OpenAPI) 문서화 적용

* 🌐 **RESTful API 실습 (restful)**
  사용자/게시글(Post) 리소스에 대한 CRUD API, 예외 처리(`CustomizedResponseEntityExceptionHandler`), HATEOAS, Swagger 문서화, JPA/MyBatis 혼합 사용 실습

* 🛡️ **Spring Security & OAuth2 (security)**
  폼 로그인과 OAuth2 소셜 로그인(`PrincipalOauth2UserService`)을 함께 다루는 인증/인가 실습, `PrincipalDetail`을 통한 커스텀 유저 인증 처리

* 📄 **API 문서 자동화**
  `restaurant`, `restful` 프로젝트에 `springdoc-openapi`를 적용하여 Swagger UI로 API 명세 확인 가능

---

## 📂 Folder Structure

```plaintext
kosa-spring-boot/
├── jwt/                     # JWT 기반 인증/인가
│   └── src/main/java/com/kcc/jwt/
│       ├── config/           # CORS, Filter, Security 설정
│       ├── controller/
│       ├── filter/           # JWT 인증/인가 필터
│       ├── model/
│       └── repository/
├── restaurant/              # 레스토랑 메뉴/리뷰 REST API
│   └── src/main/java/com/kcc/restaurant/
│       ├── bean/
│       ├── config/           # Swagger 설정
│       ├── controller/
│       ├── dto/
│       ├── exception/
│       ├── mapper/           # MyBatis 매퍼
│       └── service/
├── restful/                 # 사용자/게시글 RESTful API
│   └── src/main/java/com/kcc/restful/
│       ├── bean/
│       ├── config/
│       ├── controller/
│       ├── exception/
│       ├── mapper/
│       ├── repository/       # JPA 리포지토리
│       └── service/
└── security/                # Spring Security & OAuth2 인증
    └── src/main/java/com/kcc/security/
        ├── auth/              # 인증 커스텀 클래스
        ├── config/
        ├── controller/
        ├── model/
        ├── oauth/             # OAuth2 소셜 로그인
        └── repository/
```

---

## 🛠️ Built With

* **Language**: Java
* **Framework**: Spring Boot, Spring Security, Spring Data JPA, MyBatis
* **Auth**: JWT, OAuth2
* **API Docs**: springdoc-openapi (Swagger UI)
* **Database**: H2
* **Build Tool**: Maven

---

## 📧 Contact

* **Name**: Eunseo Yu
* **E-mail**: [eunseoyu0825@gmail.com](mailto:eunseoyu0825@gmail.com)
* **GitHub**: [221B0825](https://github.com/221B0825)
