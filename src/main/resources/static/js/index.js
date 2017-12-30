$(function() {
    $("nav a[href='/noteapp/" + location.pathname.split("/")[2] + "']").parent().addClass('active');
});
