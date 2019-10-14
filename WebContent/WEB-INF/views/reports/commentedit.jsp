<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>コメント編集ページ</h2>

        <br />
        <br />
        <form method="post"
            action="<c:url value = '/reports/commentupdate' />">
            <label for="comment">コメント</label><br />

            <textarea name="comment" rows="5" cols="40" maxlength="200">${reportcomment.comment}</textarea>
            <input type="hidden" name="comment" value="${reportcomment.comment }" />
            <input type="hidden" name="comment_id" value="${reportcomment.id }" />
            <br /> <br />

            <button type="submit">修正</button>

        </form>

        <br />

        <form method="post"
            action="<c:url value = '/reports/commentdestroy' />">

            <input type="hidden" name="comment_destroy_id"
                value="${reportcomment.id }" />
            <button type="submit">コメント削除</button>
        </form>


    </c:param>
</c:import>