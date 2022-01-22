<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulario</title>

<link rel="stylesheet" href="../css/estilo.css"  type="text/css"></link>
</head>
<body>
<div id="horario">
Fecha : <%= new java.util.Date() %></div>

<div id="formulario">

	<form action="anadirymostrar.jsp" method="POST">
	<h3 id="parradoinicial">Formulario de coche</h3>
	<p>Marca:
	<input type="text" name="marca" required/></p>
	<p>Puertas:
	<input type="number" name="puerta" required/></p>
	Precio:
	<input type="number" name="precio" required>
	<br>
	<p>¿Extras?</p>
	<p>Camara trasera:
	<select name="camara" id="camara" required>
	  <option value="si">si</option>
	  <option value="no">no</option>
	</select></p>
	<p>Masaje asientos:
	
	<select name="masaje" id="masaje" required>
	  <option value="si">si</option>
	  <option value="no">no</option>
	</select></p>
	<p>Sensores de cercania:
	<select name="sensor" id="sensor" required>
	  <option value="si">si</option>
	  <option value="no">no</option>
	</select></p>
	<br>
	<input type="submit" value="submit">
	</form>
</div>

</body>
</html>