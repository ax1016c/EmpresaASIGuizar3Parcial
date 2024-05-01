<%-- 
    Document   : error
    Created on : Mar 19, 2024, 10:46:17 AM
    Author     : axelg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="container well">
    <center>
        <h3>Sistema de Administracion de Usuarios ASI Guizar</h3>
    </center>
    <div class="thumbnail">
        <center>
            <h2>Mensaje Del Sistema</h2>
            <h3><i>Imprima esta pantalla y póngase en contacto con el administrador del sitio:</i></h3>
            <h4>paginas.wow.admin@gmail.com</h4>
            <hr>
            <%@page isErrorPage="true" %>
            <div class="alert alert-danger" role="alert">
                <h3>Ocurrio un error: </h3>
                <h3><i><%=exception%></i></h3>
            </div>
            <a href="principalfrontend.jsp"><h4>Regresar al inicio</h4></a>
            <hr>
            <h3>Fecha y hora del sistema: <%= new java.util.Date()%></h3>
        </center>
    </div>
</div>
