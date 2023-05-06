<!DOCTYPE html>
<html lang="en" data-bs-theme="light">
<head>
    <meta charset="UTF-8">
    <title>Search user</title>

    <link rel="stylesheet" href="../css/bootstrap.min.css">
</head>
<body>
<header class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a href="#" class="navbar-brand">
            <img src="../img/logo.png" alt="" height="40">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a href="/portfolio" class="nav-link">Portfolio</a>
                </li>
                <li class="nav-item">
                    <a href="/crypto" class="nav-link">Crypto</a>
                </li>
                <li class="nav-item">
                    <a href="/share" class="nav-link">Shares</a>
                </li>
                <li class="nav-item">
                    <a href="/currency" class="nav-link">Currency</a>
                </li>
                <li class="nav-item">
                    <a href="/search" class="nav-link">Search</a>
                </li>
                <li class="nav-item">
                    <form action="/logout" method="post">
                        <input type="submit" value="Log out" class="btn btn-primary">
                    </form>
                </li>
            </ul>
        </div>
    </div>
</header>

<div class="container">
    <h1 class="text-center mb-3">Search user by username</h1>
    <form method="get" action="/search">
        <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="username" name="username" aria-describedby="basic-addon2">
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="submit">Search</button>
            </div>
        </div>
    </form>

    <h1 class="text-center mt-5">Result</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Username</th>
            <th>Crypto</th>
            <th>Currency</th>
            <th>Share</th>
        </tr>
        </thead>
        <tbody>
            <#list users as user>
                <tr>
                    <th><a href="/portfolio/${user.username}">${user.username}</a></th>
                    <td>${user.crypto}</td>
                    <td>${user.currency}</td>
                    <td>${user.share}</td>
                </tr>
            </#list>
        </tbody>
    </table>
</div>

<script src="../js/bootstrap.bundle.min.js"></script>
</body>
</html>