<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenido</title>
</head>
<body>
	<form action="auth/login_check" method="post">               
            <fieldset>
                <legend>Ingrese al sistema</legend>
                <label for="username">Usuario:</label>
                <input type="text" id="username" name="username"/>        
                <label for="password">Password:</label>
                <input type="password" id="password" name="password"/>   
                <div class="checkbox">
                    <label><input type="checkbox" id="rememberme" name="remember-me"> Recordar</label>  
                </div>
                <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                <div class="form-actions">
                    <button type="submit">Ingresar</button>
                </div>
            </fieldset>
    </form>
</body>
</html>