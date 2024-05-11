<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="entidades.Subject"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-
1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Sistema de mantenimientos</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarScroll">
      <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Inicio</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Mantenimiento Cursos</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Mantenimiento Alumnos</a>
        </li>
      </ul>
      <form action="AuthServlet" class="d-flex">
      	<input type="hidden" name="type" value="logout">
        <button class="btn btn-outline-light" type="submit">Cerrar Sesión</button>
      </form>
    </div>
  </div>
</nav>


	<div class="container">
		<div class="row">
			<h3>Usuario logueado: ${sessionScope.nombre} ${sessionScope.apellido} - ${sessionScope.codigo}</h3>
			<div class="col-12 text-center">
				<h3>Mantenimiento de Cursos</h3>
			</div>
			<div class="col-12">
				<button type="button" class="btn btn-primary"
					onclick="window.location='subjectRegister.jsp'">Registrar</button>
			</div>
			<br>
			<div class="col-12">
					<table class="table">
						<thead>
							<tr>
								<th>Id</th>
								<th>Código</th>
								<th>Nombre</th>
								<th>Nivel</th>
								<th>Profesor</th>
								<th>Acciones</th>
							</tr>
						</thead>
						<tbody>
							<%
							List<Subject> listSubject = (List<Subject>) request.getAttribute("data");
																	if (listSubject != null) {
																		for (Subject item : listSubject) {
							%>
							<tr>
								<td><%=item.getIdSubject()%></td>
								<td><%=item.getCode()%></td>
								<td><%=item.getName()%></td>
								<td><%=item.getLevel()%></td>
								<td><%=item.getTeacher()%></td>
								<td><a
									href="SubjectServlet?type=info&id=<%=item.getIdSubject()%>"> <img
										alt="" src="img/ic_info.png" width="20" height="20"
										title="Editar">
								</a> <a href="SubjectServlet?type=delete&id=<%=item.getIdSubject()%>">
										<img alt="" src="img/ic_delete.png" width="20" height="20"
										title="Eliminar">
								</a></td>
							</tr>
							<%
							}
							}
							%>
						</tbody>
					</table>
			</div>
		</div>

		<div class="row"></div>
	</div>
	
</body>
</html>