$(document).ready(function() {
    const path = window.location.pathname;
    const symbol = path.split('/')[2];

    $.ajax({
        url: "/getQuoteChart",
        method: 'GET',
        data: {symbol: symbol},
        success: function(data) {
            let labels = [];
            let values = [];
            for(let i=0; i<data.length; i++) {
                let date = new Date(data[i].date);
                labels.push(date.toLocaleDateString());
                values.push(data[i].value);
            }
            let ctx = document.getElementById('stock-chart').getContext('2d');
            let chart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Chart',
                        data: values,
                        borderColor: 'rgb(75, 192, 192)',
                        borderWidth: 1,
                        fill: false
                    }]
                },
                options: {
                    maintainAspectRatio: false,
                    responsive: false
                }
            });
        }
    });
});

$(function() {
    $("#addStockForm").submit(function(event) {
        event.preventDefault();

        const path = window.location.pathname;
        const symbol = path.split('/')[2];
        const type = GetURLParameter('stock');

        let stock = {
            symbol: symbol,
            type: type,
            amount: $("#amount").val()
        };

        $.ajax({
            type: "POST",
            url: "/addStock",
            data: JSON.stringify(stock),
            contentType: "application/json",
            success: function() {
                $('#amount').val(0);
                alert("Stock was added");
            },
            error: function() {
                alert("Error adding stock");
            }
        });
    });
});

function GetURLParameter(sParam) {
    let sPageURL = window.location.search.substring(1);
    let sURLVariables = sPageURL.split('&');
    for (let i = 0; i < sURLVariables.length; i++) {
        let sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] === sParam) {
            return sParameterName[1];
        }
    }
}