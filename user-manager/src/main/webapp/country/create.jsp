<%--
  Created by IntelliJ IDEA.
  User: FX
  Date: 8/5/2022
  Time: 2:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Country Management</title>
</head>
<body>
<center>
    <h1>Country Management</h1>
    <h2>
<%--        <a href="country?action=users">List All Country</a>--%>
    <a href="country">List All Country</a>

    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Add New Country</h2>
            </caption>
            <tr>
                <th>Country Name:</th>
                <td>
                    <input type="text" name="name" id="name" size="45"/>
                </td>
            </tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
