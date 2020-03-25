function getCountry(name,callback) {
    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "https://restcountries-v1.p.rapidapi.com/name/"+name,
        "method": "GET",
        "headers": {
            "x-rapidapi-host": "restcountries-v1.p.rapidapi.com",
            "x-rapidapi-key": "84479b851cmsh5442c7b5ae08871p19b505jsn229bda6e32bd"
        }
    }

    $.ajax(settings).done(function (response) {
        console.log(response);
    });

}