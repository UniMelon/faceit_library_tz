$(document).ready(function() {
    getAll();

    $("#frmReader").submit(function(event) {
 	    event.preventDefault();
 		addReader();
 	});

    function addReader() {
        if($("#frmReader").valid()) {
            var formData = {
                username : $("#user-select").val(),
                book : $("#book-select").val()
            }

            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "api/v1/admin/readers",
                data : JSON.stringify(formData),
                dataType : 'json',
                success : function(data) {
                    console.log(data);
                    getAll();
                }
            });
        }
     }
 })

function getAll() {
    $('#readersTable').dataTable().fnDestroy();

    var showAdminColumns =  document.getElementById("isAdmin") ? true:false;

    $('#readersTable').DataTable({
            "processing": true,
            "serverSide": true,
            "lengthChange" : false,
            "searching": false,
            "ajax": {
                "url": "api/v1/readers",
                "method":"get",
                "dataSrc": function (data) {
                    var data = data.content;
                    var all = [];
                    for (var i = 0; i < data.length; i++) {
                        var row = {
        //                    rows: response.start + i + 1,
                            id: data[i].id,
                            username: data[i].username,
                            book: data[i].book,
                            createdOn: data[i].createdOn,
                            action: '<button type="button" class="btn btn-warning" onclick="updateById('+data[i].id+');"><span class="bi bi-pencil-square"></span></button>'
                                  + '<button type="button" class="btn btn-danger" onclick="deleteById(\''+data[i].book+'\',\'' + data[i].username+'\');"><span class="bi bi-trash-fill"></span></button>'
                        };
                        all.push(row);
                    }
                    console.log(all);
                    return all;
                }

            },
            "columns": [
                { "data": "id"},
                { "data": "username"},
                { "data": "book"},
                { "data": "createdOn"},
                { "data": "action", visible: showAdminColumns}
            ]
        });

}

function updateById(id) {
    $.ajax({
        type : "GET",
        url : "api/v1/admin/readers/"+id,
        dataType : 'json',
        success : function(data) {
            $("#user-select").val(data.username).change();
            $("#book-select").val(data.book).change();
            console.log(data);
        	getAll();
        }
    });
}

function deleteById(book, user) {
    var formData = {
            username : user,
            book : book
    }

    console.log(formData);

    $.ajax({
        type : "DELETE",
        contentType : "application/json",
        url : "api/v1/admin/readers",
        data : JSON.stringify(formData),
        dataType : 'json',
        success : function(data) {
        	getAll();
        }
    });
}
