<jsp:include page="template-top.jsp" />

<img src="image.do?id=${photo.id}"/><br>

<table>
	<tr>
		<td>Description: ${photo.description }</td>
	</tr>
	<tr>
		<td>Price: ${photo.price } Dollars</td>
	</tr>
	<tr>
		<td>Date: ${photo.date }</td>
	</tr>
	<tr>
		<td>Seller: ${photo.owner }</td>
	</tr>
	<tr>
		<td>Email:<a href="mailto:${emailP}">${emailP}</a>
</table>

<a href="browse.do">Return to Home Page</a>

<jsp:include page="template-bottom.jsp" />

 <!-- item's description, price, picture, date of listing, and the name and e-mail address of seller. -->