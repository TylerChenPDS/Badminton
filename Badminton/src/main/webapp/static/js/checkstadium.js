$(function () {
    changeBgImg();
    $(window).on("resize", function () {
        changeBgImg();
    });
    $("#checkStadium").on("hidden.bs.modal", function() {
        $(this).removeData("bs.modal");
	})
	$("#checkStadium").on("shown.bs.modal", function() {
        $("#checkStadium .selectpicker").selectpicker();
	})
});

function changeBgImg() {
    var windowWidth = $(window).width();
    $("#carousel-qihang>.carousel-inner>.item").each(function (index, el) {
        var div = $(el);
        var bgimage = div.data(windowWidth <= 768 ? "xs-img" : "lg-img");
        if (windowWidth <= 768) {
            div.html('<img src="' + bgimage + '" alt="">');
        } else {
            div.html("");
            div.css("background", "url('" + bgimage + "') no-repeat");
        }
    });
}