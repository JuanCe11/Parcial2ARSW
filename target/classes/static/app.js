var app = (function () {

    function find(el) {
        var name = $(el).find("td:first-child").text();
        var deaths = $(el).find("td:nth-child(2)").text();
        var confirmed = $(el).find("td:nth-child(3)").text();
        var recovered = $(el).find("td:nth-child(4)").text();
        $("#countryname").text(name);
        var tabla = document.getElementById("tabla2");
        tabla.style.display = "block";
        $("#info").empty();
        var row = "<tr> <td> Num Deaths</td> <td>"+deaths+"</td> </tr>";
        $("#info").append(row);
        row = "<tr> <td> Num Infected</td> <td>"+confirmed+"</td> </tr>";
        $("#info").append(row);
        row = "<tr> <td> Num Cured</td> <td>"+recovered+"</td> </tr>";
        $("#info").append(row);
        mapaPais(name);
        getCovidByName(name);
    }

    function mapaPais(name){
        var url = window.location;
        var nueva = url.protocol+"//"+url.host + "/coronavirus/cords/"+name;
        var getPromise = $.get(nueva);
        getPromise.then(
            function(data){
                var info = JSON.parse(data);
                plotMarkers(info[0].latlng);
            },
            function(){
                console.log('error')
            }
        );
        return getPromise;
    }

    function _tableProv(data){
        var tabla = document.getElementById("tabla3");
        tabla.style.display = "block";
        $("#filasProvicias").empty();
        data.map(function(element){
            var markup = "<tr> <td>"+ element.province +"</td> <td>"+element.deaths+"</td> <td>"+ element.confirmed +"</td> <td>"+ element.recovered +"</td> </tr>";
            $("#filasProvicias").append(markup)
        });
    }

    function _table(data){
        $("#filasPaises").empty();
        data.map(function(element){
            var markup = "<tr onclick=app.find(this)> <td>"+ element.country +"</td> <td>"+element.deaths+"</td> <td>"+ element.confirmed +"</td> <td>"+ element.recovered +"</td> </tr>";
            $("#filasPaises").append(markup)
        });
    }

    function getCovidByName(name) {
        var url = window.location;
        var nueva = url.protocol+"//"+url.host + "/coronavirus/"+name;
        var getPromise = $.get(nueva);
        getPromise.then(
            function(data){
                var info = JSON.parse(data);
                _tableProv(info.data.covid19Stats);
            },
            function(){
                console.log('error')
            }
        );
        return getPromise;
    }

    function getAll(callback) {
        var url = window.location;
        var nueva = url.protocol+"//"+url.host + "/coronavirus";
        var getPromise = $.get(nueva);
        getPromise.then(
            function(data){
                callback(JSON.parse(data));
            },
            function(){
                console.log('error')
            }
        );
        return getPromise;
    }

    return {
        init: function () {
            initMap();
        },
        cargarAll:getAll(_table),
        find:find
    }
})();
