$(document).ready(function() {
    getAll();

    $("#frmBook").submit(function(event) {
 	    event.preventDefault();
 		addBook();
 	});

     function addBook() {
        if($("#frmBook").valid()) {
            var formData = {
                name : $("#name-input").val(),
                condition : $("#condition-select").val(),
                calendarDate : $("#date-input").val()
            }

            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "api/v1/admin/books",
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
    $('#booksTable').dataTable().fnDestroy();

    var showAdminColumns =  document.getElementById("isAdmin") ? true:false;

    $('#booksTable').DataTable({
        "processing": true,
        "serverSide": true,
        "lengthChange" : false,
        "searching": false,
        "ajax": {
            "url": "api/v1/books",
            "method":"get",
            "dataSrc": function (data) {
                var data = data.content;
                var all = [];
                for (var i = 0; i < data.length; i++) {

                    var row = {
    //                    rows: response.start + i + 1,
                        id: data[i].id,
                        name: data[i].name,
                        condition: data[i].condition,
                        calendarDate: data[i].calendarDate,
                        action: '<button type="button" class="btn btn-warning" onclick="updateById('+data[i].id+')"><span class="bi bi-pencil-square"></span></button>'
                              +'<button type="button" class="btn btn-danger" onclick="deleteById('+data[i].id+')"><span class="bi bi-trash-fill"></span></button>'
                    };
                    all.push(row);
                }
                return all;
            }

        },
        "columns": [
            { "data": "id"},
            { "data": "name"},
            { "data": "condition"},
            { "data": "calendarDate"},
            { "data": "action", visible: showAdminColumns}
        ]
    });

}

function updateById(id) {
    $.ajax({
        type : "GET",
        url : "api/v1/admin/books/"+id,
        dataType : 'json',
        success : function(data) {
            $("#name-input").val(data.name);
            $("#condition-select").val(data.condition).change();
            $("#date-input").val(data.calendarDate);
            console.log(data);
        	getAll();
        }
    });
}

function deleteById(id) {
    $.ajax({
        type : "DELETE",
        url : "api/v1/admin/books/"+id,
        dataType : 'json',
        success : function(data) {
            console.log("removed by id=" + id);
        	getAll();
        }
    });
}
