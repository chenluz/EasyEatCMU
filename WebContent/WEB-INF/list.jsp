<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-top.jsp" />
	<table>
	<c:forEach var="photo" items="${photoList}">
		<tr>
			<td><a href="view.do?id=${ photo.id }">${ photo.caption }</a></td>
		</tr>
	</c:forEach>
	</table>
<jsp:include page="template-bottom.jsp" />
