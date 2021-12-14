package faceit.tz.controller.rest;

import faceit.tz.model.dto.ReaderDto;
import faceit.tz.model.mapper.ReaderMapper;
import faceit.tz.service.ReaderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/readers")
public class ReaderRestController {

    private final ReaderService readerService;

    public ReaderRestController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping
    public List<ReaderDto> get_allReaders(@RequestParam Optional<Integer> pageNo,
                                          @RequestParam Optional<Integer> pageSize) {

        return readerService.findAll(pageNo, pageSize)
                .stream()
                .map(ReaderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

//    @GetMapping("/profile")
//    public User viewProfile(HttpServletRequest request, Model model) {
//        User user = userService.findByUsername(request.getRemoteUser());
//        model.addAttribute("id", user.getId());
//        return user;
//    }
}
