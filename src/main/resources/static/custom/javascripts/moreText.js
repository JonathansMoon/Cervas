$(document).ready(function() {
    $('#boxErro').showMore({
    minheight: 80,
    buttontxtmore: '<i class="fa fa-angle-double-down"></i>',
    buttontxtless: '<i class="fa fa-angle-double-up"></i>',
    animationspeed: 250
    });
});
$("#fecharErro").click(function () {
    $(".showmore-button").hide();
 
});