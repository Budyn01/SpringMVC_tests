$('p').click(function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/resource",
        data: {},
        success: function (json) {
            jQuery.each(json, function(i, ob) {
                console.log(i, ob);
            });
        },
        complete: function () {

        },
        error: function (jqXHR, errorText, errorThrown) {
            console.log( "Wystąpił błąd w połączniu :(");
        }
    });
});