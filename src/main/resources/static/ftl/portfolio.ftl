<!DOCTYPE html>
<html lang="en" data-bs-theme="light">
<head>
    <meta charset="UTF-8">
    <title>Portfolio</title>

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
                <#if (role?? && role = "ROLE_COMPANY")>
                    <li class="nav-item">
                        <a href="/statistic" class="nav-link">Statistic</a>
                    </li>
                </#if>
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
<div class="container mt-5">
    <#if username??>
        <h2 class="text-center mb-4">${username} portfolio</h2>
    <#else>
        <h2 class="text-center mb-4">Your portfolio</h2>
        <h4>Sum of your stocks in Bitcoin: ${sumOfStocksInBitcoin}</h4>
    </#if>
    <div id="data" class="table-responsive">
        <table id="portfolio-table" class="table table-hover">
            <thead>
            <tr>
                <th scope="col" class="stockSymbolCol">Symbol</th>
                <th scope="col" class="stockNameCol">Name</th>
                <th scope="col" class="stockPriceCol">Price</th>
                <th scope="col" class="stockChangeInPercentCol">% Change</th>
                <th scope="col" class="stockBalanceCol">Balance</th>
                <th scope="col" class="stockValueCol">Value</th>
                <#if !username??>
                    <th scope="col" class="deleteStockCol">Delete</th>
                </#if>
            </tr>
            </thead>
            <tbody>
                <#list customerStocks as stock>
                    <tr>
                        <th class="stockSymbolCol"><a href="/quote/${stock.symbol}">${stock.symbol}</th>
                        <td class="stockNameCol">${stock.name}</td>
                        <td class="stockPriceCol">${stock.price}</td>
                        <td class="stockChangeInPercentCol">${stock.changeInPercent}</td>
                        <td class="stockMarketCapCol">${stock.amount}</td>
                        <td class="stockVolumeCol">${stock.value}</td>
                        <#if !username??>
                            <td class="deleteStockCol">
                                <form method="post" action="/delete/${stock.symbol}">
                                    <button type="submit" class="btn btn-warning btn-sm text-white">Delete</button>
                                </form>
                            </td>
                        </#if>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
</div>


<script src="../js/jquery-3.6.4.min.js"></script>
<script src="../js/bootstrap.bundle.min.js"></script>
</body>