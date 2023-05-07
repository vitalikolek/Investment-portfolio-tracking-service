<!DOCTYPE html>
<html lang="en" data-bs-theme="light">
<head>
    <meta charset="UTF-8">
    <title>Crypto</title>

    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/autocomplete.css">
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
                <#if (principal??)>
                    <li class="nav-item">
                        <form action="/logout" method="post">
                            <input type="submit" value="Log out" class="btn btn-primary">
                        </form>
                    </li>
                <#else>
                    <li class="nav-item">
                        <form action="/login" method="post">
                            <input type="submit" value="Log in" class="btn btn-primary">
                        </form>
                    </li>
                </#if>
            </ul>
        </div>
    </div>
</header>
<div class="container mt-5">
    <h2 class="text-center mb-4">Today's Cryptocurrency Prices</h2>

    <form id="search">
        <div class="input-group mb-3">
            <input type="search" class="form-control" placeholder="symbol" id="symbol" aria-describedby="basic-addon2">
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="submit">Search</button>
            </div>
        </div>
    </form>

    <div id="data" class="table-responsive">
        <table id="crypto-table" class="table table-hover">
            <thead>
            <tr>
                <th scope="col" class="stockSymbolCol">Symbol</th>
                <th scope="col" class="stockNameCol">Name</th>
                <th scope="col" class="stockPriceCol">Price</th>
                <th scope="col" class="stockChangeCol">Change</th>
                <th scope="col" class="stockChangeInPercentCol">% Change</th>
                <th scope="col" class="stockMarketCapCol">Market cap</th>
                <th scope="col" class="stockVolumeCol">Volume</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>

<div class="mx-auto">

    <#assign first = 1>
    <#assign prev = currentPage - 1>
    <#assign next = currentPage + 1>
    <#assign last = pageCount>

    <ul class="pagination justify-content-center">
        <#if (currentPage > 2)>
            <li class="page-item">
                <a class="prev-link page-link" href="?page=1">First</a>
            </li>
        </#if>
        <#if (currentPage > 1)>
            <li class="page-item">
                <a class="prev-link page-link" href="?page=${prev}">Previous</a>
            </li>
        </#if>
        <#if (currentPage > 1)>
            <li class="page-item">
                <a class="page-link" href="?page=${prev}">${prev}</a>
            </li>
        </#if>
        <li class="page-item active">
            <a class="page-link"> <span class="sr-only">${currentPage}</span></a>
        </li>
        <#if (next <= last)>
            <li class="page-item">
                <a class="page-link" href="?page=${next}">${next}</a>
            </li>
        </#if>
        <#if (next <= last)>
            <li class="page-item">
                <a class="page-link" href="?page=${next}">Next</a>
            </li>
        </#if>
        <#if (currentPage + 2 < last)>
            <li class="page-item">
                <a class="prev-link page-link" href="?page=${last}">Last</a>
            </li>
        </#if>
    </ul>
</div>

<script src="../js/jquery-3.6.4.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script src="../js/bootstrap.bundle.min.js"></script>
<script src="../js/crypto.js"></script>
</body>