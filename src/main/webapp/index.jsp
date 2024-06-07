<%@ page import="java.util.List" %>
<%@ page import="org.sici.entity.User" %>
<%@ page import="java.util.ArrayList" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>User Management</title>
    <script>
        function showUpdateForm(id, username, name, gender, age, phone, salary) {
            console.log("hh")
            document.getElementById('id').value = id;
            document.getElementById('updateUsername').value = username;
            document.getElementById('updateName').value = name;
            document.getElementById('updateAge').value = age;
            document.getElementById('updateGender').value = gender;
            document.getElementById('updatePhone').value = phone;
            document.getElementById('updateSalary').value = salary;
            document.getElementById('updateForm').style.display = 'block';
        }

        function showAddForm() {
            document.getElementById('addForm').style.display = 'block';
        }

        function hideUpdateForm() {
            document.getElementById('updateForm').style.display = 'none';
        }

        function hideAddForm() {
            document.getElementById('addForm').style.display = 'none';
        }

        function getAllUser() {
            document.location.href = "<%=path%>/user.do?dir=getAllUser";
        }

        function deleteUser(id) {
            document.location.href = "<%=path%>/user.do?dir=deleteUser&id=" + id;
        }
    </script>
</head>
<body>
<h1>User Management</h1>
<button onclick="getAllUser()">Get All Users</button>
<button onclick="showAddForm()">Add User</button>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Name</th>
        <th>Gender</th>
        <th>Age</th>
        <th>Phone</th>
        <th>Salary</th>
        <th>Operation</th>
            <%
            List<Object[]> objList = (List<Object[]>) session.getAttribute("users");

            System.out.println(session.getAttribute("users"));

            if (objList != null) {
                for (Object[] obj: objList) {
            %>
    <tr>
        <td><%= obj[0] %>
        </td>
        <td><%= obj[1] %>
        </td>
        <td><%= obj[2]%>
        </td>
        <td><%= obj[3] %>
        </td>
        <td><%= obj[4] %>
        </td>
        <td><%= obj[5] %>
        </td>
        <td><%= obj[6] %>
        </td>
        <td>
            <button onclick="deleteUser(<%=obj[0]%>)">Delete</button>
            <button onclick="showUpdateForm(<%=obj[0]%>, '<%=obj[1]%>', '<%=obj[2]%>',
                '<%=obj[3]%>', <%=obj[4]%>, '<%=obj[5]%>', <%=obj[6]%>)">Update</button>
        </td>
    </tr>
    <%
            }
        }
    %>
    </tr>
</table>

<!-- Update User Form -->
<div id="updateForm" style="display:none;">
    <h2>Update User</h2>
    <form action="user.do" method="get">
        <input type="hidden" name="dir" value="updateUser">
        <input type="hidden" id="id" name="id">
        Username: <input type="text" id="updateUsername" name="username">
        Name: <input type="text" id="updateName" name="name"><br>
        Age: <input type="number" id="updateAge" name="age"><br>
        Gender: <input type="text" id="updateGender" name="gender"><br>
        Phone: <input type="text" id="updatePhone" name="phone"><br>
        Salary: <input type="number" id="updateSalary" name="salary" step="0.01"><br>
        <button type="submit">Update</button>
    </form>
</div>

<!-- Add User Form -->
<div id="addForm" style="display:none;">
    <h2>Add User</h2>
    <form action="/user.do" method="get">
        <input type="hidden" name="dir" value="save">
        Username: <input type="text" name="username"><br>
        Name: <input type="text" name="name"><br>
        Age: <input type="number" name="age"><br>
        Gender: <input type="text" name="gender"><br>
        Phone: <input type="text" name="phone"><br>
        Salary: <input type="number" name="salary" step="0.01"><br>
        <button type="submit">Add</button>
    </form>
</div>
</body>
</html>

