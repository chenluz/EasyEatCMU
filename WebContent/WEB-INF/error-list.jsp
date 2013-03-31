<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p style="font-size:medium; color:red ">
<c:forEach var="error" items="${errors}">
	 ${error} <br/> 
</c:forEach>
</p>