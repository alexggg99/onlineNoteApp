$(document).ready(function() {
    $('#signUpForm').submit(function (event) {
        event.preventDefault()
        $('#signupmassage').empty();
        // CSRF Token
        var _csrf = $('meta[name="_csrf"]').attr('content');
        var registerData = $(this).serializeArray();
        var data = registerData.reduce(function(obj,item){
            obj[item.name] = StringUtil.isEmpty(item.value) ? '' : item.value;
            return obj;
        }, {});
        //AJAX call
        $.ajax({
            url: Config.singUpUrl,
            type: 'POST',
            contentType: 'application/json',
            headers: {
                'X-CSRF-TOKEN': _csrf
            },
            data: JSON.stringify(data),
            success: function(data){
                if (data) {
                    $('#signupmassage').html(data);
                }
            },
            error: function(jqXHR) {
                if (jqXHR.status == 409)  {
                    //username is already used
                    $('#signupmassage').html('<div class="alert-danger">Username is already used</div>');
                } else if(jqXHR.status == 400) {
                    var message = '';
                    $.each(jqXHR.responseJSON.errors, function( index, value ) {
                        var tmp = '<p class="bg-danger">' + value.defaultMessage + '</p>'
                        message += tmp;
                    });
                    $('#signupmassage').html(message);
                } else {
                    $('#signupmassage').html('<div class="alert-danger">Upps! Something went wrong.</div>');
                }
            }
        })
    })
})