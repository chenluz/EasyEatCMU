<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template-top.jsp" />

<p style="font-size:medium">
	Recent Post:
</p>

<jsp:include page="error-list.jsp" />

		<table>
		<c:forEach var="Photo" items="${photoList}">
			
			<tr>
				<td><img src="image.do?id=${Photo.id}" width=300/>
				<a href="view.do?id=${Photo.id}">More Information</a> </td>
				
			</tr>
		</c:forEach>
					
		</table>

<jsp:include page="template-bottom.jsp" />
