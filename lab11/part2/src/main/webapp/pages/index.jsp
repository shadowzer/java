<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE HTML>
<html>
    <form action="/" method="post">
        <p>Введите ваше имя:</p>
        <input type="text" name="username"/>

        <p>Введите ваше послание:</p>
        <input type="text" name="message"/>

        <input type="submit" value="Добавить запись"/>
    </form>

    <%@ include file="_messages.jsp" %>

</html>
