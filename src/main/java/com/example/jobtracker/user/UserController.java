package com.example.jobtracker.user;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) throws Exception {userService.createUser(user);}

    @GetMapping("/recover")
    public void recoverAccount(@RequestParam(required = true) String email) throws Exception {userService.recoverAccount(email);}

    @GetMapping("/change-password")
    public void changePassword(
            @RequestParam(required = false) String token,
            @RequestParam(required = false) String currentPassword,
            @RequestParam(required = true) String username,
            @RequestParam(required = true) String newPassword
            ) throws Exception {userService.changePassword(token,currentPassword,username,newPassword);}
}
