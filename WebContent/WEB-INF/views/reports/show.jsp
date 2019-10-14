<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
	<c:param name="content">
		<c:choose>
			<c:when test="${report != null}">
				<h2>日報 詳細ページ</h2>

				<table>
					<tbody>
						<tr>
							<th>氏名</th>
							<td><c:out value="${report.employee.name}" /></td>
						</tr>
						<tr>
							<th>日付</th>
							<td><fmt:formatDate value='${report.report_date}'
									pattern='yyyy-MM-dd' /></td>
						</tr>
						<tr>
							<th>内容</th>
							<td><pre>
                                    <c:out value="${report.content}" />
                                </pre></td>
						</tr>
						<tr>
							<th>登録日時</th>
							<td><fmt:formatDate value="${report.created_at}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
						<tr>
							<th>更新日時</th>
							<td><fmt:formatDate value="${report.updated_at}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>

						<c:if
							test='${reportcomment.comment != null and reportcomment.comment.equals("") == false}'>

							<tr>
								<th>コメント</th>
								<td><c:out value="${reportcomment.comment}" /></td>
							</tr>

							<tr>
								<th>コメント記入者</th>
								<td><c:out value="${reportcomment.employee.name }" /></td>
							</tr>

							<tr>
								<th>コメント更新日時</th>
								<td><c:out value="${reportcomment.updated_at }" /></td>
							</tr>

						</c:if>

					</tbody>
				</table>

				<c:if test="${sessionScope.login_employee.id == report.employee.id}">
					<p>
						<a href="<c:url value='/reports/edit?id=${report.id}' />">この日報を編集する</a>
					</p>

				</c:if>
			</c:when>
			<c:otherwise>
				<h2>お探しのデータは見つかりませんでした。</h2>
			</c:otherwise>
		</c:choose>

		<p>
			<a href="<c:url value='/reports/index' />">一覧に戻る</a>
		</p>

		<c:if
			test="${sessionScope.login_employee.id.equals(reportcomment.employee.id) == false and
            sessionScope.login_employee.id.equals(report.employee.id) == false and
            reportcomment_count < 1 and sessionScope.login_employee.admin_flag == 1}">
			<p>
				<a href="<c:url value='/reports/comment?id=${report.id}' />">コメントを記入する
				</a>
			</p>
		</c:if>

		<c:if
			test="${sessionScope.login_employee.id.equals(reportcomment.employee.id) == true }">
			<p>
				<a href="<c:url value='/reports/commentedit?id=${report.id }' />">コメントを編集する</a>
		</c:if>

		<c:if
			test="${sessionScope.login_employee.id.equals(reportfavo.employee.id) == false and
             sessionScope.login_employee.id.equals(report.employee.id) == false}">
			<form method="POST" action="<c:url value='/reports/favo' />">
				<p align="right">
					<input type="hidden" name="report_id" value="${report.id}" />
					<button type="submit">いいね！</button>
				</p>
			</form>
		</c:if>
		<c:if
			test="${sessionScope.login_employee.id.equals(reportfavo.employee.id) }">
			<form method="POST" action="<c:url value='/reports/destroy' />">
				<p align="justify">
					<input type="hidden" name="report_id" value="${report.id}" />
					<button type="submit">いいね取り消し</button>
				</p>
			</form>
		</c:if>

		<c:if
			test="${sessionScope.login_employee.admin_flag == 1 and
            sessionScope.login_employee.id.equals(report.employee.id) == false and
            report.approve_flag == 0}">
			<form method="post" action="<c:url value='/reports/approve' />">
				<p align="center">
					<input type="hidden" name="report_id" value="${report.id }" /> <input
						type="hidden" name="employee_name"
						value="${sessionScope.login_employee.name}" />
					<button type="submit">承認</button>
				</p>
			</form>
		</c:if>

		<c:if
			test="${sessionScope.login_employee.admin_flag == 1 and
            sessionScope.login_employee.id.equals(report.employee.id) == false and
            report.approve_flag == 1}">

			<form method="post"
				action="<c:url value='/reports/approvedestroy' />">
				<p align="center">
					<input type="hidden" name="report_id" value="${report.id }" />
					<button type="submit">承認取り消し</button>
				</p>
			</form>
		</c:if>
	</c:param>
</c:import>