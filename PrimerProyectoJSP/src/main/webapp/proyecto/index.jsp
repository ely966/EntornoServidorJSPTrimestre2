<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio</title>
<link rel="stylesheet" href="../css/estilo.css"  type="text/css"></link>

</head>
<body id="bodyIndex">
<jsp:useBean id = "garajee" class = "clases.Garaje" scope="session"> 
    
    <jsp:setProperty name = "garajee" property = "nombre" value = "GarajeX"/>
    </jsp:useBean>
    
    <div id="presentacion">
    	<p>
    	Bienvenido al <jsp:getProperty name="garajee" property="nombre"/>
    	</p>
		Aquí podrás registrar el automóvil que deseas con los extras que quieres incluir, para saber el precio de este de antemano.    	<p><a href="formulario.jsp">Ir al formulario</a></p>
    <br>
    
    </div>	
    <div id="imagen">
    	<img src="../imagen/car.png" alt="red car" width="390" height="250" id="image">
    </div>
  
    <br>

</body>
</html>