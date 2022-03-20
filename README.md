# Springboot + Spring security + JWT


** 기존 session 방식 인증
1. username, password 로그인 정상
2. 서버쪽 세션 ID 생성
3. 클라이언트 쿠키 세션 ID 응답
4. 서버에 요청시, 쿠키 값 세션 ID를 항상 들고 서버쪽으로 요청하기 때문에 서버는 세션 ID가 유요한지 판단해서 유요하면 인증이 필요한 페이지로 접근

** token 방식 인증
1. username, password 로그인 정상
2. jwt token 생성
3. 클라이언트 쪽으로 jwt token을 응답
4. 요청 할때마다 jwt token을 가지고 요청
5. 서버는 jwt token이 유효한지 판 => filter를 사용




