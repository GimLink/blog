<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../layout/header.jsp"%>
<div class="container">

    <div>
        글 번호:<span id="id"><i>${board.id} </i></span>
        작성자:<span id="user"><i>${board.user.username} </i></span>
        조회수:<span id="count"><i>${board.count} </i></span>
    </div>
    <br/>
    <div class="form-group">
        <h3>${board.title}</h3>
    </div>
    <hr />
    <div class="form-group">
        <div>${board.content}</div>
    </div>
    <hr />

    <div class="card">
        <div class="card">
            <div class="card-header">전체 댓글</div>
            <ul id="comment--box" class="list-group">
                <li id="comment--1" class="list-group-item d-flex justify-content-between">
                    <div>댓글 내용</div>
                    <div class="d-flex">
                        <div class="font-italic">작성자 : link&nbsp;</div>
                        <button class="badge">삭제</button>
                    </div>
                </li>
            </ul>
        </div>
        <br/>
        <div>
            <div class="card-body"><textarea class="form-control" row="1"></textarea></div>
            <div class="card-footer d-flex justify-content-end"><button class="btn btn-primary">등록</button></div>
        </div>

    </div>
    <br/>

    <button class="btn btn-secondary" onclick="history.back()" >목록으로</button>
    <c:if test="${board.user.id == principal.user.id}">
        <a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
        <button id="btn-delete" class="btn btn-danger">삭제</button>
    </c:if>
</div>
<script src="/js/board.js"></script>

<%@include file="../layout/footer.jsp"%>




