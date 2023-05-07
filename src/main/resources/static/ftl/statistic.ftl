<!DOCTYPE html>
<html lang="en" data-bs-theme="light">
<head>
    <meta charset="UTF-8">
    <title>Statistic</title>

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
    <div class="d-grid gap-2">
        <form action="/statistic/excel" method="get">
            <button type="submit" class="btn btn-primary w-100">Export portfolio to Excel</button>
        </form>
        <form action="/statistic/word" method="get">
            <button type="submit" class="btn btn-primary w-100">Export stocks statistic to Word</button>
        </form>
        <form action="/statistic/pdf" method="get">
            <button type="submit" class="btn btn-primary w-100">Export your statistic to PDF</button>
        </form>
    </div>
</div>

<script src="../js/bootstrap.bundle.min.js"></script>
</body>
</html>