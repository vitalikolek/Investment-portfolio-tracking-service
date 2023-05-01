<!DOCTYPE html>
<html lang="en" data-bs-theme="light">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>

    <link rel="stylesheet" href="../css/bootstrap.min.css">
</head>
<body>
<form id="register">
    Registration
    <div><label> User Name : <input id="username" type="text" name="username"/> </label></div>
    <div><label> Email : <input id="email" type="text" name="email"/> </label></div>
    <div><label> Password: <input id="password" type="password" name="password"/> </label></div>
    <div>
        <label>
            <select id="role" name="role">
                <option value="ROLE_USER" selected>USER</option>
                <option value="ROLE_COMPANY">COMPANY</option>
            </select>
        </label>
    </div>
    <div><input type="submit" value="Sign In"/></div>
    <a href="/registration">Log</a>
</form>
</body>
</html>