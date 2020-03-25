var app = (function () {

    function find(nombre) {
        initMap();
        getAirportsByName(nombre,_table);
    }
    function getAll() {
        var url = window.location;
        var nueva = url.protocol+"//"+url.host + "/coronavirus";
        alert(nueva);
        var getPromise = $.get(nueva);
        getPromise.then(
            function(data){
                console.log(JSON.parse(data));

                _table(JSON.parse(data));
            },
            function(){
                console.log('error')
            }
        );
        return getPromise;
    }

    function _table(data){
        $("#filasPaises").empty();
        data.map(function(element){
            var markup = "<tr> <td>"+ element.country +"</td> <td>"+element.deaths+"</td> <td>"+ element.confirmed +"</td> </tr>";
            $("#filasPaises").append(markup)
        });
    }

    return {
        init: function () {
            initMap();
        },
        cargarAll:getAll,
        find:find
    }
})();