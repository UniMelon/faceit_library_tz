package faceit.tz.controller;

import faceit.tz.controller.exception.UserAlreadyExistException;
import faceit.tz.model.dto.UserDto;
import faceit.tz.model.mapper.UserMapper;
import faceit.tz.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String register(@ModelAttribute("user") UserDto userDto){
        return "register";
    }

    @PostMapping
    public String userRegistration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "register";

        try {
            userService.register(UserMapper.INSTANCE.toEntity(userDto));

        }catch (UserAlreadyExistException e){
            model.addAttribute("registrationForm", "An account already exists.");
            return "register";
        }
        model.addAttribute("registrationMsg", "Successfully! Check you email");
        return "register";
    }

    @GetMapping("/{token}")
    public String verifyCustomer(@PathVariable String token, RedirectAttributes redirAttr){
        userService.verifyUser(token);

        redirAttr.addFlashAttribute("verifiedAccountMsg", "Account verified.");
        return "redirect:/login";
    }
}
