<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exception</title>
<link rel="stylesheet" href="../css/estilo.css"  type="text/css"></link>

</head>
<body><pre>

<div id="divErrorNeg">
	<h3 id="error">Lo siento, pero hay un error. Número negativo</h3>
 	<% //exception.printStackTrace(response.getWriter());Ver todo el error por defecto %> 
	<p>Por favor intentalo de nuevo: </p>
	<a href="formulario.jsp">Añadir producto</a>
</div>
</body>
</html>