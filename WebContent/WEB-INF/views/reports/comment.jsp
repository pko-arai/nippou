<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" />
            <br />
        </c:forEach>

    </div>
</c:if>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h1>コメント編集ページ</h1>

        <br />
        <br />

        <form method="POST"
            action="<c:url value = '/reports/commentcreate' />">
            <label for="comment">コメント</label> <br />

            <textarea name="comment" rows="5" cols="40" maxlength="200"
                placeholder="ここにコメントを入力してください" wrap="hard" required></textarea>
            <br /> <br /> <input type="hidden" name="comment"
                value="${ reportcomment.comment}" /> <input type="hidden"
                name="report_id" value="${report.id }" />

            <button type="submit">投稿</button>
        </form>

        </html>

    </c:param>
</c:import>