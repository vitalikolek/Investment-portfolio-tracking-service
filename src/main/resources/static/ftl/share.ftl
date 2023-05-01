<!DOCTYPE html>
<html lang="en" data-bs-theme="light">
<head>
    <meta charset="UTF-8">
    <title>Share</title>

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
                    <form action="/logout" method="post">
                        <input type="submit" value="Log out" class="btn btn-primary">
                    </form>
                </li>
            </ul>
        </div>
    </div>
</header>
<div class="container mt-5">
    <h2 class="text-center mb-4">Today's Cryptocurrency Prices</h2>
    <div id="data" class="table-responsive">
        <table id="stocks-table" class="table table-hover">
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
            <#list shares as share>
                <tr>
                    <th class="stockSymbolCol"><a href="/quote/${share.symbol}">${share.symbol}</th>
                    <td class="stockNameCol">${share.name}</td>
                    <td class="stockPriceCol">${share.price}</td>
                    <td class="stockChangeCol">${share.change}</td>
                    <td class="stockChangeInPercentCol">${share.changeInPercent}</td>
                    <td class="stockMarketCapCol">${share.marketCap}</td>
                    <td class="stockVolumeCol">${share.volume}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>

<script src="../js/jquery-3.6.4.min.js"></script>
<script src="../js/bootstrap.bundle.min.js"></script>
</body>