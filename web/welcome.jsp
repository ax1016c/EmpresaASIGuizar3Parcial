<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
</head>
<body>
    <h2>Welcome, <%= session.getAttribute("username") %></h2>
    
    <%-- Retrieve additional user information from session attributes --%>
    <% int workerID = (Integer) session.getAttribute("id"); %>
    <% String role = (String) session.getAttribute("rol"); %>
    <% java.sql.Date startDate = (java.sql.Date) session.getAttribute("fecha_inicio_empresa"); %>
    
    <%-- Convert the startDate to a formatted String --%>
    <% String formattedStartDate = ""; %>
    <% if (startDate != null) { %>
        <% SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); %>
        <% formattedStartDate = sdf.format(startDate); %>
    <% } %>
    
    <%-- Display user information --%>
    <p>Worker ID: <%= workerID %></p>
    <p>Privilege: <%= role %></p>
    <p>Joined: <%= formattedStartDate %></p>
</body>
</html>
