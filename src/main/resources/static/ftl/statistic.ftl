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
<a href="/statistic/excel">Export to Excel</a>
<a href="/statistic/word">Export to Word</a>
<a href="/statistic/pdf">Export to PDF</a>
</body>
</html>