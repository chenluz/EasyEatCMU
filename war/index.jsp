<%--
    Purpose    : Provide an interface of BigInteger calculator
    Document   : index.jsp
    Created on : Jan 23, 2013, 1:36:06 AM
    Author     : Yin Xu
    Course     : 95-702
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calculator</title>
        <!--JavaScript codes are used to check whether the input is a number -->
        <script type="text/javascript">
            //Global variable, true if both input numbers are Number data type
            //checkNum() is used to check whether both two input numbers are Number data type
            function checkNum()
            {
                var1 = Number(document.getElementById("x").value);
                var2 = Number(document.getElementById("y").value);
                if(isNaN(var1-0)||isNaN(var2-0))  //comparison algorithm "isNaN(var-0)" is a source of stackoverflow.com
                {
                    alert("Only numbers are accepted!");
                    return false;
                
                }
                else return true;
            }
        </script>
    </head>
    <body>
        <!--JSP codes are used to avoid null being displayed. -->
        <% String result = (String) request.getAttribute("answer");
            String xStr = (String) request.getAttribute("x");
            String yStr = (String) request.getAttribute("y");
            String opr = (String)request.getAttribute("op");
            if (result == null) {
                result = "";
            }
            if (xStr == null) {
                xStr = "";
            }
            if (yStr == null) {
                yStr = "";
            }                   
        %>
 
        
        <form name="calculator" method="get" action="easyeatcmu" onsubmit="return checkNum();">
            <table align="center" border="1" bordercolor="#FFFFFF" bgcolor="#74B27B" >
                <tr> <td align="center"> X:  </td>
                    <td> <input type="text" size="40" value="<%=xStr%>" id="x" 
                                name="x" style="background:beige;color:black;"/> 
                    </td>
                <tr> <td align="center"> Y:  </td>
                    <td> <input type="text" size="40" value="<%=yStr%>" id="y" 
                                name="y" style="background:beige;color:black;"/> 
                    </td>
                    <!-- format the columns -->
                <tr> <td align="center" colspan="2">
                        <select name="calcOp">
                        <option value="add" <%= "add".equals(opr)? "selected" :"" %>>
                            add </option> 
                        <option value="multiply" <%= "multiply".equals(opr)? "selected" :"" %>>
                            multiply </option>
                        <option value="relativelyPrime" <%= "relativelyPrime".equals(opr)? "selected" :"" %>>
                            relativelyPrime </option>
                        <option value="mod" <%= "mod".equals(opr)? "selected" :"" %> >
                            mod </option>
                        <option value="modInverse" <%= "modInverse".equals(opr)? "selected" :"" %> >
                            modInverse </option>
                        <option value="power" <%= "power".equals(opr)? "selected" :"" %> >
                            power </option>
                        </select>
                        <input type="submit"/>
                    </td>
                <tr> <td align="center"> Answer:  </td>
                    <td> <input type="text" size="40" value="<%=result%>" name="answer" style="background:beige;color:black;" disabled/> </td>
            </table>
        </form>
    </body>
</html>