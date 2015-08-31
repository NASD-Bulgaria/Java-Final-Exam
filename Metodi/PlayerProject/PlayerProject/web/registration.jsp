

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert title here</title>
    </head>
    <body>
        <form method="POST" action="RegistrationServlet">
            <p>Username:</p>
            <br/>
            <input type="text" name="username"/>
            <p>Password</p>
            <br/>
            <input type="password" name="password1"/>
            <p>First name</p>
            <br/>
            <input type="text" name="firstname"/>
            <p>Last name</p>
            <br/>
            <input type="text" name="lastname"/>
            <p>Insert your balance</p>
            <br/>
            <input type="text" name="balance"/>
            <br/>
            <input type="submit" value="Register"/> 
        </form>
    </body>
</html>
