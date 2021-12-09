//$(document).ready(function() {
//    $.ajax({
//    	type : "GET",
//    	url : "api/books",
//    	dataType: 'Json',
//    		success: function(data){
//                $('#getBooksDiv ul').empty();
//                var custList = "";
//
//                $.each(data, function(i, book){
//                    var book_object = "- Book with Id = " + book.id + ", name = " + book.name + ", calendarDate = "
//                        + book.calendarDate + ", condition = " + book.condition + "<br>";
//                    $('#getBooksDiv .list-group').append(book_object);
//                });
//                console.log("Success: ", data);
//            },
//            error : function(e) {
//                $("#getBooksDiv").html("<strong>Error</strong>");
//                console.log("ERROR: ", e);
//            }
//    });
//
//    $.ajax({
//        	type : "GET",
//        	url : "api/users",
//        	dataType: 'Json',
//        		success: function(data){
//                    $('#getResultDiv ul').empty();
//                    var custList = "";
//
//                    $.each(data, function(i, user){
//                        var user_object = "- User with Id = " + user.id + ", username = " + user.username + ", role = "
//                            + user.role + "<br>";
//                        $('#getResultDiv .list-group').append(user_object);
//                    });
//                    console.log("Success: ", data);
//                },
//                error : function(e) {
//                    $("#getResultDiv").html("<strong>Error</strong>");
//                    console.log("ERROR: ", e);
//                }
//        });
//})