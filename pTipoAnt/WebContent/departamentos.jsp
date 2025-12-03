<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<script>
function verElemento(id){
	document.getElementById("formDepartamento").id.value = id;
	document.getElementById("formDepartamento").accion.value = "V"; 
	
	document.getElementById("formDepartamento").submit();
}
</script>
<style>

	label{
		color: #0040aa;
	}
</style>
<head>
<meta charset="ISO-8859-1">
<title>Departamentos</title>
</head>
<body>
<h1>Departamentos</h1>
<div >
<form method="post" action="./departamento" id="formDepartamento">
 <input hidden="true" type="text" name="accion" value="" />
 <input hidden="true" type="text" name="id" value="" />
</form>
</div>
<ul>
<c:forEach items="${listado}" var="elemento">
<li><a href="javascript:verElemento(${elemento.id})">${elemento.nombre}</a>
</li>
</c:forEach>
</ul>


<c:if test="${error!=null}">
<div style="color:red">
   ${error}
</div>
</c:if>
</body>
</html>