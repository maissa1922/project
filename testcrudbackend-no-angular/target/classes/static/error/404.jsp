<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h1>My 404 Error Page</h1>
<div id="locationDiv"></div>
<script>
     document.getElementById("locationDiv").innerHTML = "Location: " + window.location.href;
</script></body>
</html>