$(document).ready(function() {
    getAll();

    $("#frmRemoteUser").submit(function(event) {
 	    event.preventDefault();
 		addBookToRemoteUser();
 	});

    function addBookToRemoteUser() {
        if($("#frmRemoteUser").valid()) {
            var formData = {
                book : $("#book-select").val()
            }

            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "api/v1/profile",
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
    $('#userPersonalBooks').dataTable().fnDestroy();

    $('#userPersonalBooks').DataTable({
            "processing": true,
            "serverSide": true,
            "lengthChange" : false,
            "searching": false,
            "ajax": {
                "url": "api/v1/profile",
                "method": "get",
                "dataSrc": function (data) {
                    var data = data;
                    var all = [];
                    for (var i = 0; i < data.length; i++) {
                        var row = {
        //                    rows: response.start + i + 1,
                            id: data[i].id,
                            book: data[i].book,
                            user: data[i].user,
                            createdOn: data[i].createdOn,
                            action: '<button type="button" class="btn btn-warning" onclick="updateById('+data[i].id+');"><span class="bi bi-pencil-square"></span></button>'
                                  + '<button type="button" class="btn btn-danger" onclick="deleteById(\''+data[i].book+'\',\'' + data[i].user+'\');"><span class="bi bi-trash-fill"></span></button>'
                        };
                        all.push(row);
                    }
                    return all;
                }
            },
            "columns": [
                { "data": "id"},
                { "data": "book"},
                { "data": "createdOn"},
                { "data": "action"}
            ]
        });

}

function updateById(id) {
    $.ajax({
        type : "GET",
        url : "api/v1/profile/"+id,
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
        url : "api/v1/profile",
        data : JSON.stringify(formData),
        dataType : 'json',
        success : function(data) {
        	getAll();
        }
    });
}
