function getCrypto() {
    const $usersView = $('#crypto-table tbody');
    let page = GetURLParameter('page');

    $.ajax({
        url: '/crypto/getCrypto/',
        method: 'GET',
        data: { page: page},
        success: function (cryptos) {
            let html = '';
            cryptos.forEach(function (crypto) {
                html += `
                    <tr>
                        <th><a href="/quote/${crypto.symbol}">${crypto.symbol}</th>
                        <td>${crypto.name}</td>
                        <td>${crypto.price}</td>
                        <td>${crypto.change}</td>
                        <td>${crypto.changeInPercent}</td>
                        <td>${crypto.marketCap}</td>
                        <td>${crypto.volume}</td>
                    </tr>
                    `;
            });
            $usersView.html(html);
        }
    });
}

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

$(function() {
    $("#symbol").autocomplete({
        source: function(request, response) {
            $.ajax({
                url : "/crypto/getCryptoSymbols",
                dataType : "json",
                success: function(data){
                    let results = $.ui.autocomplete.filter(data, request.term);
                    response(results.slice(0, 5));
                }
            });
        }
    });
});

$('#search').submit(function(e) {
    e.preventDefault();
    let symbol = $('#symbol').val();
    let link = `/quote/${symbol}?stock=cryptocurrency`;
    $('#link').attr('href', link).text(link);
    window.location.href = link;
});

$(function() {
    getCrypto();
});