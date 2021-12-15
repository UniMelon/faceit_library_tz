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

            console.log(formData);

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
    $('#usersTable').dataTable().fnDestroy();

    $.ajax({
        type : "GET",
        url : 'api/v1/readers',
        dataType: 'JSON',
        success: function(data){
            $('#usersTable').DataTable({
                "sAjaxSource": 'api/v1/readers',
                "sAjaxDataProp": "",
                "order": [ [ 0, "desc" ] ],
            	    "aoColumns": [
            		    { "mData": "id" },
            		    { "mData": "username" },
                        { "mData": "book" },
                        { "mData": "createdOn" },
                        {
                            "mData": null,
                            render: function(data) {
                                return '<button type="button" class="btn btn-warning" onclick="updateById('+data.id+');">Update</button>'
                                + '<button type="button" class="btn btn-danger" onclick="deleteById(\''+data.book+'\',\'' + data.username+'\');">Delete</button>'
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
            console.log("removed by book="+ data.book + "from user=" + data.user);
        	getAll();
        }
    });
}
