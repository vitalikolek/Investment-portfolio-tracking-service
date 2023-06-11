<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>

    <link rel="stylesheet" href="../css/bootstrap.min.css">
</head>

<body>
<div class="container">
    <form action="/registration" method="post">
        <h1 class="mb-3">Registration</h1>
        <div class="mb-3">
            <label class="form-label" for="username">Username:</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="mb-3">
            <label class="form-label" for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <div class="mb-3">
            <label class="form-label" for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="mb-3">
            <label class="form-label" for="role">Role:</label>
            <select id="role" class="form-select" name="role">
                <option value="1" selected>User</option>
                <option value="2">Company</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary mb-3">Sign In</button>
        <p>Already registered? <a href="/login">Log in</a></p>
    </form>
</div>

<script src="../js/bootstrap.bundle.min.js"></script>
</body>
</html>
