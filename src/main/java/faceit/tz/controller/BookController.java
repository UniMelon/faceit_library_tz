package faceit.tz.controller;

import faceit.tz.service.BookService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

//    @PostMapping("/api/search")
//    public ResponseEntity<?> getSearchResultViaAjax(
//            @Valid @RequestBody SearchCriteria search, Errors errors) {
//
//        AjaxResponseBody result = new AjaxResponseBody();
//
//        //If error, just return a 400 bad request, along with the error message
//        if (errors.hasErrors()) {
//
//            result.setMsg(errors.getAllErrors()
//                    .stream().map(x -> x.getDefaultMessage())
//                    .collect(Collectors.joining(",")));
//
//            return ResponseEntity.badRequest().body(result);
//
//        }
//
//        List<User> users = userService.findByUserNameOrEmail(search.getUsername());
//        if (users.isEmpty()) {
//            result.setMsg("no user found!");
//        } else {
//            result.setMsg("success");
//        }
//        result.setResult(users);
//
//        return ResponseEntity.ok(result);
//
//    }

}
