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
    <h1>${stockInfo.symbol} Chart</h1>
    <div class="row">
        <div class="col-12 col-md-8">
            <canvas id="stock-chart"></canvas>
        </div>
        <div class="col-6 col-md-4">
            <h5><strong>Symbol:</strong> ${stockInfo.symbol}</h5>
            <h5><strong>Name:</strong> ${stockInfo.name}</h5>
            <h5><strong>Price:</strong> ${stockInfo.price}</h5>
            <h5><strong>Day High:</strong> ${stockInfo.dayHigh}</h5>
            <h5><strong>Day Low:</strong> ${stockInfo.dayLow}</h5>
            <h5><strong>Change:</strong> ${stockInfo.change}</h5>
            <h5><strong>Change in Percent:</strong> ${stockInfo.changeInPercent}</h5>
            <h5><strong>Market Cap:</strong> <#if stockInfo.marketCap??>${stockInfo.marketCap}<#else>N/A</#if></h5>
            <h5><strong>Volume:</strong> <#if stockInfo.volume??>${stockInfo.volume}<#else>N/A</#if></h5>
        </div>
    </div>
    <form id="addStockForm">
        <div class="form-group mb-3">
            <label for="amount">Add to the portfolio or change the amount</label>
            <input id="amount" class="form-control" type="number" min="0" step=any name="amount" required>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

<script src="../js/jquery-3.6.4.min.js"></script>
<script src="../js/chart.js"></script>
<script src="../js/quote.js"></script>
</body>
</html>
