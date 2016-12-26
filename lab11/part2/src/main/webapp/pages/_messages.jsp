<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table border="1" cellpadding="5">
    <tr>
        <th>Date</th>
        <th>User</th>
        <th>Message</th>
    </tr>
    <c:forEach var="m" items="${records}">
        <tr>
            <td><c:out value="${m.time}"/></td>
            <td><c:out value="${m.username}"/></td>
            <td><c:out value="${m.message}"/></td>
        </tr>
    </c:forEach>
</table>
