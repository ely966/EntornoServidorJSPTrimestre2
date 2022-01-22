<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page errorPage = "errorNumeroNegativo.jsp" %>
     <!-- Indica -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Añadir datos</title>
<link rel="stylesheet" href="../css/estilo.css"  type="text/css"></link>

</head>
<body>
	<h3>Aqui puedes ver los registros introducidos y el precio total</h3>
	<% 
	//String nomb=request.getParameter("nombre"); 
	//Integer preci=Integer.parseInt(request.getParameter("precio"));
	String marcaCoche=request.getParameter("marca"); 
	Integer puertaCoche= Integer.parseInt(request.getParameter("puerta"));
	Integer precioCoche= Integer.parseInt(request.getParameter("precio"));
	String camaraCoche=request.getParameter("camara");
	String masajeCoche=request.getParameter("masaje");
	String sensoresCoche=request.getParameter("sensor");
	%>
    <jsp:useBean id="vehiculo" class="clases.Automovil">
    </jsp:useBean>
    
    <%
    vehiculo.setMarca(marcaCoche);
    if(puertaCoche < 0){
    	throw new RuntimeException("Error, numero negativo en el numero de puertas");
    	}
    else{
    	vehiculo.setPuertas(puertaCoche);
    	}
    if(precioCoche < 0){
    	throw new RuntimeException("Error, numero negativo en el precio");
    	}
    else{
    	 vehiculo.setPrecio(precioCoche);
    	 vehiculo.setPreciototal(precioCoche,false);
    	}
 
    Integer precioTotal=precioCoche;
    if(camaraCoche.equals("si")){
    	 precioTotal=precioTotal+100;
    	vehiculo.setPreciototal(precioTotal,true);
    }
    if(masajeCoche.equals("si")){
    	precioTotal=precioTotal+400;
    	vehiculo.setPreciototal(precioTotal,true);
    }
    if(sensoresCoche.equals("si")){
    	precioTotal=precioTotal+100;
    	vehiculo.setPreciototal(precioTotal,true);
    }
    
    out.println(vehiculo.toStringMod());
    //System.out.println(vehiculo.toStringMod()); 
    %>
  
	<br>
 <a href="formulario.jsp">Volver añadir otro producto</a>	
	
</body>
</html>