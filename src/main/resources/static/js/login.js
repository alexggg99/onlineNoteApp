$(document).ready(function(){
    var container = $(".loginWrapper");
    $('#login').click(function(event) {
        event.preventDefault()
        container.fadeToggle();
    })
    $(document).mouseup(function (e) {

        if (!container.is(e.target) // if the target of the click isn't the container...
            && container.has(e.target).length === 0) // ... nor a descendant of the container
        {
            container.fadeOut();
        }
    });

    $('#loginBtn').click(function(event){
        event.preventDefault();
        $('#loginError').empty();
        // CSRF Token
        var _csrf = $('meta[name="_csrf"]').attr('content');

        var username = $('#loginInput').val();
        var password = $('#passwordInput').val();

        // Validator Username, password.
        // Ex: if (username < 3 || password < 6 || ....)
        // return;

        $.ajax({
            url: Config.loginUrl,
            type: 'POST',
            contentType: 'application/json',
            headers: {
                'X-CSRF-TOKEN': _csrf
            },
            data: JSON.stringify({"username":username, "password":password}),
            success: function(data, textStatus, jqXHR) {
                setTimeout(function(){// wait for 5 secs(2)
                    location.reload(); // then reload the page.(3)
                }, 600);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                if(jqXHR.status == 400) {
                    var message = '';
                    $.each(jqXHR.responseJSON.errors, function( index, value ) {
                        var tmp = '<p class="bg-danger">' + value.defaultMessage + '</p>'
                        message += tmp;
                    });
                    $('#loginError').html(message);
                }
                if(jqXHR.status == 401) {
                    $('#loginError').html('<p class="bg-danger">Wrong password</p>');
                }
                if(jqXHR.status == 404) {
                    //bad not found or Unauthorized
                    $('#loginError').html('<p class="bg-danger">User not found</p>');
                }
                if(jqXHR.status == 423) {
                    //bad request
                    $('#loginError').html('<p class="bg-danger">User blocked</p>');
                }
            }
        })
    });

    $('#logout').click(function(event) {
        event.preventDefault()
        $.get(Config.logoutUrl, function(data, status){
            setTimeout(function(){// wait for 5 secs(2)
                location.reload(); // then reload the page.(3)
            }, 600);
        });
    })

});