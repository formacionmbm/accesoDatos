<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<script>

function verElemento(id){
	document.getElementById("formVivero").id.value = id;
	document.getElementById("formVivero").accion.value = "VER"; 
	
	document.getElementById("formVivero").submit();
}

function irCrear(){
	document.getElementById("imgPlus").style.display = "none";
	document.getElementById("panelCrearForm").style.display = "block";
}
function visualizar(){
	document.getElementById("imgPlus").style.display = "block";
	document.getElementById("panelCrearForm").style.display = "none";
}
function eliminar(id){
	if(confirm("¿Desea eliminar el vivero?")){
	document.getElementById("formVivero").id.value = id;
	document.getElementById("formVivero").accion.value = "ELIMINAR"; 
	
	document.getElementById("formVivero").submit();
	}
}
</script>
<head>
<meta charset="ISO-8859-1">
<title>${titulo}</title>
<link href="css/estilo.css" rel="stylesheet" type="text/css">
</head>
<body class="vivero">
	<div id="panelView">
		<h1>Viveros</h1>

	<c:if test="${error!=null}">
		<div class="error">${error}</div>
	</c:if>
	</div>	

	<c:forEach items="${elementos}" var="elemento">
		<div class="gallery">
			<a href="javascript:verElemento(${elemento.id})"> <img class="gallery"
				src="img/vegetables.jpg" alt="${elemento.nombre}"> 
			</a>
			<div class="desc">${elemento.nombre}
			<img
				src="img/minus.png" alt="Eliminar Vivero"
				onClick="javascript:eliminar(${elemento.id})">
			</div>
		</div>
	</c:forEach>
	<div>
		<img id="imgPlus" src="img/plus.png" alt="Crear Vivero"
			onClick="javascript:irCrear()">
	</div>
	<div id="panelCrearForm" class="gallery" style="display: none">
		<h2>Nuevo Vivero</h2>
		<form method="post" action="./vivero" id="formVivero">
			<input hidden="true" type="text" name="accion" value="CREAR" />
			<input hidden="true" type="text" name="elemento" value="VIVERO" />
			<input hidden="true" type="text" name="id" value="${proximoId}" />
			<label for="nombre">Identificador:</label> ${proximoId} <br><label
				for="nombre">Nombre:</label> <input id="nombre" type="text"
				name="nombre" value="${elemento.nombre}" required
				pattern="[A-Za-z]+.{3,25}"
				title="El nombre tiene que estar formado por letras y más de 4 caracteress" /><br>
				<input type="submit" value="Crear"> <input type="button"
				value="Volver" onClick="javascript:visualizar()">
		</form>
	</div>
</body>
</html>