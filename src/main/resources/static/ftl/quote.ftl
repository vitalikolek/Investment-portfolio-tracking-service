<!DOCTYPE html>
<html lang="en">
<head>
    <title>${stockInfo.name}</title>

    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/quote.css">
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
Symbol: ${stockInfo.symbol}
Name: ${stockInfo.name}
Price: ${stockInfo.price}
Day High: ${stockInfo.dayHigh}
Day Low: ${stockInfo.dayLow}
Change: ${stockInfo.change}
Change in Percent: ${stockInfo.changeInPercent}
<#if stockInfo.marketCap??>
    Market Cap: ${stockInfo.marketCap}
<#else>
    Market Cap: N/A
</#if>
Volume: ${stockInfo.volume}

<canvas id="stock-chart"></canvas>

<form id="addStockForm">
    <label>Amount:
        <input id="amount" class="form-control" type="number" min="0" step=any name="amount" required>
    </label>

    <button type="submit" class="btn btn-primary">Submit</button>
</form>

<script src="../js/jquery-3.6.4.min.js"></script>
<script src="../js/chart.js"></script>
<script src="../js/quote.js"></script>
</body>
</html>
