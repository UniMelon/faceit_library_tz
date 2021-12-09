$(document).ready( function () {
	 var table = $('#booksTable').DataTable({
			"sAjaxSource": "/api/books",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			    { "mData": "id"},
		        { "mData": "name" }
			]
	 });

	 var table2 = $('#usersTable').DataTable({
     			"sAjaxSource": "/api/users",
     			"sAjaxDataProp": "",
     			"order": [[ 0, "asc" ]],
     			"aoColumns": [
     			    { "mData": "id"},
     		        { "mData": "username" },
     				{ "mData": "role" }
     			]
     	 });
});