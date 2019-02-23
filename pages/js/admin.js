$(function () {
    $('.collapse_all').on('shown.bs.collapse', function () {
        var a = $(this).prev();
        var span = a.children("");
        span.removeClass("icon-jiahao");
        span.addClass("icon-hengxian1");

    })
    $('.collapse_all').on('hidden.bs.collapse', function () {
        var a = $(this).prev();
        var span = a.children("");
        span.addClass("icon-jiahao");
        span.removeClass("icon-hengxian1");
    })

    $(".collapse_all > li > a").click(function(el){
        el.preventDefault();
        $(".collapse_all > li > a").removeClass("navactive");
        $(this).addClass("navactive");
        $("#iframe-content").attr("src",$(this).data("iframe"));
        
        var twotext = $(this).parent().parent().prev().text();
        $(".breadcrumb > li:nth-child(2) > a").html(twotext);
        $(".breadcrumb > li:nth-child(3)").html($(this).text());
    })
});
