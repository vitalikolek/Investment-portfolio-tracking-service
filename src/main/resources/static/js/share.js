function getCrypto() {
    const $usersView = $('#share-table tbody');
    let page = GetURLParameter('page');

    $.ajax({
        url: '/getShare/',
        method: 'GET',
        data: { page: page},
        success: function (cryptos) {
            let html = '';
            cryptos.forEach(function (crypto) {
                html += `
                    <tr>
                        <th><a href="/quote/${crypto.symbol}?stock=share">${crypto.symbol}</th>
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
    getCrypto();
});