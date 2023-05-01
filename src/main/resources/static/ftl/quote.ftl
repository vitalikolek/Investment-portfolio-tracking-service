<!DOCTYPE html>
<html lang="en">
<head>
    <title>${stockInfo.name}</title>

    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/quote.css">
</head>
<body>
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
            <label>Name:
                <input id="amount" class="form-control" type="number" min="0" step=any name="amount" required>
            </label>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

<script src="../js/jquery-3.6.4.min.js"></script>
<script src="../js/chart.js"></script>
<script src="../js/quote.js"></script>
</body>
</html>
