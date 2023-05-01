<!DOCTYPE html>
<html lang="en" data-bs-theme="light">
<head>
    <meta charset="UTF-8">
    <title>Comments</title>

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
                <#list cryptocurrencies as crypto>
                    <tr>
                        <th class="stockSymbolCol"><a href="/quote/${crypto.symbol}?stock=cryptocurrency">${crypto.symbol}</th>
                        <td class="stockNameCol">${crypto.name}</td>
                        <td class="stockPriceCol">${crypto.price}</td>
                        <td class="stockChangeCol">${crypto.change}</td>
                        <td class="stockChangeInPercentCol">${crypto.changeInPercent}</td>
                        <td class="stockMarketCapCol">${crypto.marketCap}</td>
                        <td class="stockVolumeCol">${crypto.volume}</td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
</div>

<script src="../js/jquery-3.6.4.min.js"></script>
<script src="../js/bootstrap.bundle.min.js"></script>
</body>