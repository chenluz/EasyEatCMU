<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template-top.jsp" />

<p style="font-size:medium">
	Search Result: ${numOfResult } items
</p>

<jsp:include page="error-list.jsp" />


<p>
	
	
		<table>
		<c:forEach var="Photo" items="${photoList}">
			
			<tr>
				<td><img src="image.do?id=${Photo.id}"/><a href="view.do?id=${Photo.id}">More Information</a> </td>
				
			</tr>
		</c:forEach>
			
			
			
		</table>
		

</p>


<jsp:include page="template-bottom.jsp" />
