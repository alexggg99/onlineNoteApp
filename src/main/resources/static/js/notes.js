$(document).ready(function() {
    var _csrf = $('meta[name="_csrf"]').attr('content');
    var activeNoteId = 0;
    var editMode = false;
    var loadNotes = function() {
        $.ajax({
            url: Config.notes,
            contentType: 'application/json',
            headers: {
                'X-CSRF-TOKEN': _csrf
            },
            success: function(data) {
                var html = '';
                $.each(data, function(i, val){
                    html +=
                        '<div class="container">' +
                        '<div class="col-xs-5 col-sm-3 delete">' +
                        '<button class="btn-lg btn-danger" style="width: 100%">delete</button>' +
                        '</div>' +
                        '<div class="noteheader" data-id="' + data[i].id + '">' +
                        '<div class="text title">' + data[i].note + '</div>' +
                        '<div class="timetext">' +data[i].timestamp + '</div>' +
                        '</div>' +
                        '</div>';
                });
                $('#notes').html(html);
                clickOnNote();
                deleteNode();
            },
            error: function(jqXHR) {
                $('#alert-content').text("Exception on server side!");
                $('#alert').fadeIn();
            }
        })
    };
    loadNotes();
    $('#addNote').click(function(){
        $.ajax({
            url: Config.notes,
            method: 'POST',
            contentType: 'application/json',
            headers: {
                'X-CSRF-TOKEN': _csrf
            },
            success: function(data){
                activeNoteId = data;
                $('#editNoteTextarea').val('');
                $('#editNoteTextarea').data('id', activeNoteId)
                showHide(['#notepad', '#allNotes'], ['#addNote', '#editNote', '#doneNote', '#notes']);
                $('#editNoteTextarea').focus();
            },
            error: function(jqXHR) {
                $('#alert-content').text("Exception on server side!");
                $('#alert').fadeIn();
            }
        });
    });

    $('#allNotes').click(function(){
        loadNotes();
        showHide(['#addNote', '#editNote', '#notes'], ['#notepad', '#allNotes', '#doneNote']);
    })

    $('#editNoteTextarea').keyup(function(target){
        $.ajax({
            url: Config.notes + '/' + $(target.target).data('id'),
            method: 'POST',
            contentType: 'application/json',
            headers: {
                'X-CSRF-TOKEN': _csrf
            },
            data: $(this).val(),
            success: function(ignore){
            },
            error: function(jqXHR) {
                $('#alert-content').text("Exception on server side!");
                $('#alert').fadeIn();
            }
        });
    });

    function clickOnNote() {
        $('.noteheader').click(function(){
            if (!editMode) {
                activeNoteId = $(this).data('id')
                $('#editNoteTextarea').val($(this).find('.text').text());
                $('#editNoteTextarea').data('id', activeNoteId);
                showHide(['#notepad', '#allNotes'], ['#addNote', '#editNote', '#doneNote', '#notes']);
            }
        });
    }

    $('#editNote').click(function(){
        editMode = true;
        $('.noteheader').addClass('col-xs-7 col-sm-9');
        showHide(['#doneNote', '.delete'], [this]);
    });

    function deleteNode() {
        $('.delete').click(function(){
            var deleteButton = this;
            $.ajax({
                url: Config.notes + '/' + $(deleteButton).next().data('id'),
                method: 'DELETE',
                contentType: 'application/json',
                headers: {
                    'X-CSRF-TOKEN': _csrf
                },
                success: function(data){
                    $(deleteButton).next().remove();
                    $(deleteButton).remove();
                },
                error: function(jqXHR) {
                    $('#alert-content').text("Exception on server side!");
                    $('#alert').fadeIn();
                }
            });
        });
    };

    $('#doneNote').click(function(){
        editMode = false;
        loadNotes();
        showHide(['#addNote', '#editNote', '#notes'], ['#notepad', '#allNotes', '#doneNote']);
        $('.noteheader').removeClass('col-xs-1 col-sm-9');
        showHide(['#doneNote', '.delete'], [this]);
    });

    var showHide = function(arr1, arr2) {
        $.each(arr1, function(key, val){
            $(val).show();
        });
        $.each(arr2, function(key, val){
            $(val).hide();
        });
    };

})