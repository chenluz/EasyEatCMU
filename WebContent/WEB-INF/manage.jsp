<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-top.jsp" />

<p style="font-size:medium">
	Add a new item:
</p>

<jsp:include page="error-list.jsp" />


<p>
	<form method="post" action="upload.do" enctype="multipart/form-data">
	
		<table>
			<tr>
				<td>File: </td>
				<td colspan="2"><input type="file" name="file" value="${filename}"/></td>
			</tr>
			<tr>
				<td>Caption: </td>
				<td><input type="text" name="caption" value="${caption}"/></td>
				<td>(optional)</td>
			</tr>

			<tr>
				<td>Description: </td>
				<td><input type="text" name = "description" value = "${description}"/></td>
			</tr>
			<tr>
				<td>Price: </td>
				<td><input type="text" name = "price" value ="${price}" /></td>
			</tr>
			<tr>
				<td colspan="3" align="right">
					<input type="submit" name="button" value="Add"/>
				</td>
			</tr>
		</table>
		
	</form>
</p>
<hr/>
<p>
	<table>
	<c:forEach var="photo" items="${photoList}">
        <tr>
            <td valign="top">
                <form method="POST" action="remove.do">
                    <input type="hidden" name="id" value="${ photo.id }"/>
                    <input type="submit" value="X"/>
                </form>
            </td>
            <td valign="top">
                <form method="POST" action="move-up.do">
                    <input type="hidden" name="id" value="${ photo.id }" />
                    <input type="submit" value="&uarr;" ${  i==0 ? "disabled" : "" }/>
                </form>
            </td>
            <td valign="top">
                <form method="POST" action="move-down.do">
                    <input type="hidden" name="id" value="${ photo.id }"/>
                    <input type="submit" value="&darr;" ${  i==photos.length-1 ? "disabled" : "" } />
                </form>
            </td>
            <td valign="top"><a href="view.do?id=${ photo.id }">${ photo.caption }</a></td>
        </tr>
	</c:forEach>

	</table>
</p>

<jsp:include page="template-bottom.jsp" />
