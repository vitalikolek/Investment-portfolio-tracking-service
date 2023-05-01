<!DOCTYPE html>
<html lang="en" data-bs-theme="light">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <form action="/login" method="post">
        <h1 class="mb-3">Login</h1>
        <div class="mb-3">
            <label class="form-label">Username:</label>
            <input type="text" class="form-control" name="username" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Password:</label>
            <input type="password" class="form-control" name="password" required>
        </div>
        <button type="submit" class="btn btn-primary mb-3">Log in</button>
        <p>Not a member? <a href="/registration">Register</a></p>
    </form>
</div>

<script src="../js/bootstrap.bundle.min.js"></script>
</body>
</html>
