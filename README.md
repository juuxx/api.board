# `📌게시판` CRUD + `👍🏻좋아요` 기능 구현
## 기능구현 목록
1. `header`에 로그인 id 넣어서 인증
```http
#예시
GET http://localhost:9000/api/v1/board
Accept: application/json
Content-Type: application/json
Authentication: user124
```
2. 회원은 모든 게시판 CRUD 및 좋아요 가능
3. 비회원은 게시판 목록 조회 및 게시글 읽기만 가능


### header 인증 방법
`RequestHeaderAuthenticationFilter` 사용하여 인증
```java
public RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter() throws Exception {
  RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter = new RequestHeaderAuthenticationFilter();
  requestHeaderAuthenticationFilter.setPrincipalRequestHeader(SECURITY_HEADER); //인증정보가 담김 헤더 키 지정
  requestHeaderAuthenticationFilter.setAuthenticationManager(authenticationManager()); //위에서 설정한 인증관리자 등록
  requestHeaderAuthenticationFilter.setContinueFilterChainOnUnsuccessfulAuthentication(false); //실패시 계속 진행 여부
  requestHeaderAuthenticationFilter.setExceptionIfHeaderMissing(false); //헤더에 값이 없어도 에러 안나게
  return requestHeaderAuthenticationFilter;
}
```
* <b>비회원(헤더에 인증정보가 없는 경우)</b>
- `RequestHeaderAuthenticationFilter` 이거를 통과하고 `AnonymousAuthenticationFilter` 에서 처리하자!

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
  http.authorizeRequests()
    .anyRequest()
    .permitAll()
    .and()
    .csrf().disable()
    .sessionManagement()
    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //api 요청 할때 마다 세션 새로 생성
    .and()
    .anonymous() //비회원 로그인 권한 설정
      .principal(anonymousPrincipal())
    .authorities("ROLE_" + ROLE_ANONYMOUS)
    .and()
    .addFilterAt(requestHeaderAuthenticationFilter(), RequestHeaderAuthenticationFilter.class)
  ;
}

private Object anonymousPrincipal(){
  return CustomUserDetail.builder()
    .username(ROLE_ANONYMOUS)
    .accountType(ROLE_ANONYMOUS)
    .build();
}
 ```

* 권한이 필요한 method에 `@PreAuthorize("!hasAnyAuthority('ROLE_ANONYMOUS')")` 써주자
 ```java
 //controller
@PreAuthorize("!hasAnyAuthority('ROLE_ANONYMOUS')")
@PostMapping("/api/v1/board")
public void crateBoard(@RequestBody RequestBoardDto requestBoardDto, @AuthenticationPrincipal CustomUserDetail customUserDetail) {
	boardService.writeBoard(customUserDetail.getUsername(), requestBoardDto);
	}
 ```
