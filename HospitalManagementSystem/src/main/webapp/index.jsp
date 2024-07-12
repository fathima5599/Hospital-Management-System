<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hospital Management System</title>
    <style>
        body {
            background-color: white;
            color: blue;
            font-family: Arial, sans-serif;
        }
        h1 {
            color: darkblue;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            margin: 10px 0;
        }
        a {
            color: blue;
            text-decoration: none;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h1>Welcome to Hospital Management System</h1>
    <ul>
        <li><a href="addPatient.jsp">Add New Patient</a></li>
        <li><a href="addDoctor.jsp">Add New Doctor</a></li>
        <li><a href="bookAppointment.jsp">Book Appointment</a></li>
    </ul>
</body>
</html>
