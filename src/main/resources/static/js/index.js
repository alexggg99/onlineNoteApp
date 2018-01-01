$(function() {
    if (location.pathname.split("/")[2] != 'login') {
     $("nav a[href='/noteapp/" + location.pathname.split("/")[2] + "']").parent().addClass('active');
    }
    $('#logout').click(function(event) {
        event.preventDefault()
        $.get(Config.singOutUrl, function(data, status){
            setTimeout(function(){// wait for 5 secs(2)
                location.reload(); // then reload the page.(3)
            }, 600);
        });
    })

});
