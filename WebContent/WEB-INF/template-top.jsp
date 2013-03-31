<html>
<head>
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="pragma" content="no-cache">
	<title>Mock Craigslist</title>
	<style>
		.menu-head {font-size: 10pt; font-weight: bold; color: black; }
		.menu-item {font-size: 10pt;  color: black }
    </style>
</head>

<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table cellpadding="4" cellspacing="0">
    <tr>
	    <!-- Banner row across the top -->
        <td width="130" bgcolor="#66AA66">
            <!-- <img border="0" src="login2.jpg" height="75">
            <img border="0" src="login3.jpg" height="75"> --> </td>
        <td bgcolor="#66AA66">&nbsp;  </td>
        <td width="500" bgcolor="#66AA66">
            <p align="center">
            <c:choose>	
            	<c:when test="${ (empty title)}">
            		<font size="7">Mock Craigslist</font>
            	 </c:when>
				<c:otherwise>
		        	<font size="5">${title}</font>
				 </c:otherwise>
			 </c:choose>	
			</p>
		</td>
    </tr>
	
	<!-- Spacer row -->
	<tr>
		<td bgcolor="#66AA66" style="font-size:5px">&nbsp;</td>
		<td colspan="2" style="font-size:5px">&nbsp;</td>
	</tr>
	
	<tr>
		<td bgcolor="#66AA66" valign="top" height="500">
			<!-- Navigation bar is one table cell down the left side -->
            <p align="left">
            <form method="get" action="search.do">
            <input type="text" name="search" value=""/><br>
            <input type="submit" name="button" value="Search"/>
            </form>
	<c:choose>
		<c:when test="${ (empty user)}">
				<span class="menu-item"><a href="login.do">Login</a></span><br/>
				<span class="menu-item"><a href="register.do">Register</a></span><br/>
				<span class="menu-item"><a href="browse.do">Return to Home</a></span><br/>
		</c:when>
		<c:otherwise>
				<span class="menu-head">${user.firstName} ${user.lastName}</span><br/>
				<span class="menu-item"><a href="manage.do">Manage Your Photos</a></span><br/>
				<span class="menu-item"><a href="change-pwd.do">Change Password</a></span><br/>
				<span class="menu-item"><a href="logout.do">Logout</a></span><br/>
				<span class="menu-item">&nbsp;</span><br/>
				<span class="menu-head">Photos From:</span><br/>
		</c:otherwise>
	</c:choose>

	<c:forEach var="user" items="${userList}">
			    <span class="menu-item">
					<a href="list.do?userName=${user.userName}">
						${user.firstName} ${user.lastName}
					</a>
				</span>
				<br/>
	</c:forEach>
			</p>
		</td>
		
		<td>
			<!-- Padding (blank space) between navbar and content -->
		</td>
		<td  valign="top">
