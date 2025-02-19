<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            padding: 20px;
        }

        .container {
            background: white;
            padding: 20px;
            border-radius: 5px;
            width: 500px;
            margin: auto;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }

        input, button {
            width: 100%;
            margin-bottom: 10px;
            padding: 8px;
        }

        button {
            cursor: pointer;
        }
    </style>

    <script>
        function fetchEmployees() {
            window.location.href = "hello-servlet";
        }

        function addEmployee(event) {
            
            event.preventDefault();
            console.log("add employee called");
            let formData = new FormData(document.getElementById("employeeForm"));
            fetch("hello-servlet", {
                method: "POST",
                body: new URLSearchParams(formData),
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                }
            }).then(response => response.text()).then(data => {
                alert(data)
            })
        }

        function updateEmployee(event) {
            event.preventDefault();
            let formdata = new FormData(event.target);
            fetch("hello-servlet", {
                method: "POST",
                body: new URLSearchParams(formdata),
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                }
            }).then(response => response.text().then(data => alert(data)))
        }

        function deleteEmployee(event) {
            event.preventDefault();
            let formdata = new FormData(event.target);
            fetch("hello-servlet", {
                method: "POST",
                body: new URLSearchParams(formdata),
                headers: {
                    "Content-type": "application/x-www-form-urlencoded"
                }
            }).then(response => response.text()).then(data => {
                alert(data)
            })
        }
    </script>
</head>
<body>
<div class="container">
    <h2>Employee Management</h2>
    <form id="employeeForm" onsubmit="addEmployee(event)">
        <input type="text" value="addEmployee" name="action" hidden/>
        <input type="text" name="name" placeholder="Employee Name" required>
        <input type="text" name="department" placeholder="Department" required>
        <input type="number" name="salary" placeholder="Salary" required>
        <button type="submit">Add Employee</button>
    </form>
    <hr>
    <button onclick="fetchEmployees()">Fetch All Employees</button>
    <hr>
    <form id="updateForm" onsubmit="updateEmployee(event)">
        <h2>Enter details which you want to update: </h2>
        <input type="text" name="action" value="updateEmployee" hidden>
        <input type="number" name="id" placeholder="Employee id" required>
        <input type="text" name="name" placeholder="Employee Name" required>
        <input type="text" name="department" placeholder="Department" required>
        <input type="number" name="salary" placeholder="Salary" required>
        <button type="submit">Update Employee</button>
    </form>

    <form id="deleteForm" onsubmit="deleteEmployee(event)">
        <h2>Enter the id of the employee which you want to delete: </h2>
        <input type="text" name="action" value="deleteEmployee" hidden>
        <input type="number" placeholder="Enter employee id" name="employeeId" required>
        <button type="submit">Delete Employee</button>
    </form>


</div>

</body>
</html>
