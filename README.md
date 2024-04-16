<p>스프링부트와 스프링 시큐리티를 이용한 게시판 개인 프로젝트입니다.</p>
<p>간단한 게시판 CRUD용 프로젝트인만큼 따로 배포는 하지 않을 예정이지만</p>
<p>추후 S3연동, 유효성 검사, 관리자 권한 등등 추가하여 서버리스로 배포 해도 좋을듯 합니다.</p>

<br/>
<h1>사용한 기술스택</h1>
<div>
    <ul>
        <li>
          <strong>JAVA 17</strong>
        </li>
        <li>
          <strong>Spring Boot</strong>
        </li>
        <li>
          <strong>Spring Security</strong>
          <ul>
            <li>Spring Security 로그인</li>
            <li>Spring Security OAuth2 카카오톡 회원가입 및 로그인</li>
          </ul>
        </li>
        <li>
          <strong>Spring Data JPA</strong>
        </li>
        <li>
          <strong>Querydsl</strong>
        </li>
        <li>
          <strong>JSP</strong>
        </li>
        <li>
          <strong>ajax 이용한 비동기 통신</strong>
        </li>
        <li>
          <strong>BootStrap4</strong>
        </li>
        <li>
          <strong>MYSQL</strong>
        </li>
    </ul>
</div>
<br/>
<br/>
<h1>구현 기능</h1>
<div>
    <ul>
        <li>
            <strong>계정 관련</strong>
            <ul>
                <li>로그인,로그아웃,회원가입</li>
                <li>비로그인 사용자 이용제한</li>
                <li>카카오톡 API 활용한 회원가입 및 로그인 기능</li>
            </ul>
        </li>
        <li>
            <strong>게시판</strong>
            <ul>
                <li>글쓰기</li>
                <li>댓글기능</li>
            </ul>
        </li>
    </ul>
</div>
<br/>
<br/>
<h1>이미지</h1>
<br/>
<div>
    <ul>
        <li>
            <strong>게시판화면</strong>
            <ul>
                <li>로그인 페이지 버튼</li>
                <li>회원가입 버튼</li>
                <li>
                  게시판에 작성된 글이 없는 상태
                </li>
                <li>
                    <img width="1466" alt="스크린샷 2024-04-16 17 13 11" src="https://github.com/GimLink/blog/assets/88365662/35d655e6-afee-4f32-8545-0cc7fafe029a">
                </li>
            </ul>
        </li>
        <br/>
        <li>
            <strong>로그인</strong>
            <ul>
                <li>Spring Security 로그인 구현</li>
                <li>
                    <img width="1464" alt="스크린샷 2024-04-16 17 22 41" src="https://github.com/GimLink/blog/assets/88365662/62342350-3e56-4823-91b8-34e0c4d65420">
                </li>
            </ul>
        </li>
        <br/>
        <li>
            <strong>회원가입</strong>
            <ul>
                <li>
                    <img width="1467" alt="스크린샷 2024-04-16 17 28 24" src="https://github.com/GimLink/blog/assets/88365662/00163ecf-1298-4fdc-9a2b-5d87d00668a6">
                </li>
            </ul>
        </li>
        <br/>
        <li>
            <strong>게시판 글 작성</strong>
            <ul>
                <li>summernote 오픈소스 웹 에디터 사용하여 글작성 폼 구현</li>
                <li>작성한 글이 html 스트링 타입으로 변환돼서 데이터베이스에 저장</li>
                <li>내용에 이미지가 있을 경우 Base64로 인코딩 되어 그대로 DB에 저장되므로 추후 AWS S3를 이용하는 방식으로 바꿔줄 예정</li>
                <li>게시글이 있을 경우 게시판 홈화면에 표시</li>
                <li>
                    <img width="1468" alt="스크린샷 2024-04-16 17 38 31" src="https://github.com/GimLink/blog/assets/88365662/cd153fbf-a664-476b-a6c1-9a0c9f524131">
                </li>
                <li>
                    <img width="1468" alt="스크린샷 2024-04-16 17 48 26" src="https://github.com/GimLink/blog/assets/88365662/4bb890ea-41af-4b17-8126-2f2f640a5210">
                </li>
                <li>
                  <img width="1467" alt="스크린샷 2024-04-16 17 49 19" src="https://github.com/GimLink/blog/assets/88365662/8325d067-16b7-4150-84c1-0c726a80b5e2">
                </li>
            </ul>
        </li>
        <br/>
        <li>
            <strong>게시글 상세 페이지</strong>
            <ul>
                <li>게시글 수정 삭제 버튼은 글작성자에게만 표시</li>
                <li>
                  <img width="1469" alt="스크린샷 2024-04-16 17 53 41" src="https://github.com/GimLink/blog/assets/88365662/0db0312e-f6b6-449e-81cf-fecd0d4d17a1">
                </li>
            </ul>
        </li>
        <br/>
        <br/>
        <li>
            <strong>댓글 등록</strong>
            <ul>
                <li>댓글 작성 순선대로 전체 댓글 목록에 표시 및 댓글 작성자 표시</li>
                <li>댓글 작성자에게만 댓글 삭제 버튼 보이도록 설정</li>
                <li>
                    <img width="1147" alt="스크린샷 2024-04-16 17 55 28" src="https://github.com/GimLink/blog/assets/88365662/27751789-7d25-4f7a-92db-96cc86e3c3d1">
                </li>
            </ul>
        </li>
        <br/>
