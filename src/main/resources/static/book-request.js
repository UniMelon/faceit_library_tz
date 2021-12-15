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

            console.log(formData);

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

    $.ajax({
        type : "GET",
        url : 'api/v1/books',
        dataType: 'JSON',
        success: function(data){
            $('#booksTable').DataTable({
                "sAjaxSource": 'api/v1/books',
                "autoWidth": true,
                "sAjaxDataProp": "",
                "order": [ [ 0, "desc" ] ],
            	    "aoColumns": [
            		    { "mData": "id" },
            		    { "mData": "name" },
                        { "mData": "condition" },
                        { "mData": "calendarDate" },
                        {
                            "mData": null,
                            render: function(data) {
                                return '<button type="button" class="btn btn-warning" onclick="updateById('+data.id+')">Update</button>'
                                +'<button type="button" class="btn btn-danger" onclick="deleteById('+data.id+')">Delete</button>'
                            }
                        }
            		]
            });
        },
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
