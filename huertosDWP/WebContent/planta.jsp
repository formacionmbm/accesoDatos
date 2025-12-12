<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<script>
	function visualizar() {
		document.getElementById("panelView").style.display = "block";
		document.getElementById("panelModificarForm").style.display = "none";
	}

	function modificar() {
		if (confirm("¿Desea modificar este elemento?")) {

			document.getElementById("formPlanta").accion.value = "MODIFICAR";
			document.getElementById("formPlanta").submit();
			document.getElementById("panelView").style.display = "block";
			document.getElementById("panelModificarForm").style.display = "none";
		}
	}
	function aModificar() {
		document.getElementById("panelView").style.display = "none";
		document.getElementById("panelModificarForm").style.display = "block";
	}
</script>
<head>
<meta charset="ISO-8859-1">
<title>${titulo}</title>
<link href="css/estilo.css" rel="stylesheet" type="text/css">
</head>
<body class="planta">
	<form method="get" action="./vivero" id="formVivero">
		<input hidden="true" type="text" name="elemento" value="VIVERO" /> <input
			hidden="true" type="text" name="accion" value="VER" /> <input
			hidden="true" type="text" name="id"
			value="${elemento.maceta.huerto.vivero.id}" />
	</form>
	<form method="get" action="./huerto" id="formHuerto">
		<input hidden="true" type="text" name="elemento" value="HUERTO" /> <input
			hidden="true" type="text" name="accion" value="VER" /> <input
			hidden="true" type="text" name="id"
			value="${elemento.maceta.huerto.id}" />
	</form>
	<form method="get" action="./maceta" id="formMaceta">
		<input hidden="true" type="text" name="elemento" value="MACETA" /> <input
			hidden="true" type="text" name="accion" value="VER" /> <input
			hidden="true" type="text" name="id" value="${elemento.maceta.id}" />
	</form>
	<div id="panelView">
		<div class="card">
			<div style="display: inline-block;">

				<a href="#"
					onclick="document.getElementById('formVivero').submit();"> <img
					src="img/vegetables.jpg"
					alt="${elemento.maceta.huerto.vivero.nombre}" width="100"
					height="100">
				</a> <a href="#"
					onclick="document.getElementById('formHuerto').submit();"> <img
					src="img/huerto.jpg" alt="${elemento.maceta.huerto.nombre}"
					width="100" height="100">
				</a> <a href="#"
					onclick="document.getElementById('formMaceta').submit();"> <img
					src="img/macetas.jpg" alt="${elemento.maceta.nombre}" width="100"
					height="100">
				</a>
			</div>

			<h1 class="verde">

				${titulo} ${elemento.nombre} <a href="javascript:aModificar()"
					class="w  nobutton">?</a>
			</h1>
		</div>
	</div>
	<div id="panelModificarForm" style="display: none" class="card">
		<div style="display: inline-block;">
			<a href="./vivero?id=${elemento.maceta.huerto.vivero.id}"> <img
				src="img/vegetables.jpg"
				alt="${elemento.maceta.huerto.vivero.nombre}" width="100"
				height="100">
			</a> <a href="./huerto?id=${elemento.maceta.huerto.id}"> <img
				src="img/huerto.jpg" alt="${elemento.maceta.huerto.nombre}"
				width="100" height="100">
			</a> <a href="./maceta?id=${elemento.maceta.id}"> <img
				src="img/macetas.jpg" alt="${elemento.maceta.nombre}" width="100"
				height="100">
			</a>
		</div>
		<h1 class="verde">${titulo}${elemento.nombre}</h1>
		<form method="post" action="./planta" id="formPlanta">
			<input hidden="true" type="text" name="accion" value="MODIFICAR" />
			<input hidden="true" type="text" name="id" value="${elemento.id}" />
			Id:${elemento.id} <br> <label for="nombre">Nombre:</label> <input
				id="nombre" type="text" name="nombre" value="${elemento.nombre}"
				required pattern="[A-Za-z]+.{3,25}"
				title="El nombre tiene que estar formado por letras y más de 4 caracteress" /><br>
			<input type="submit" value="Modificar"> <input type="button"
				onClick="javascript:visualizar()" value="Volver" />
		</form>
	</div>

	<c:if test="${error!=null}">
		<div class="error">${error}</div>
	</c:if>

	<br />
</body>
</html>