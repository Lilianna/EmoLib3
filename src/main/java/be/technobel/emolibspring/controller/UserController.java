package be.technobel.emolibspring.controller;

import be.technobel.emolibspring.helper.Response;
import be.technobel.emolibspring.model.entity.UserEntity;
import be.technobel.emolibspring.model.form.auth.LoginForm;
import be.technobel.emolibspring.model.form.auth.UserRegisterForm;
import be.technobel.emolibspring.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginForm loginForm){
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("email", loginForm.getEmail());
        response.put("name", "thangnd");
        response.put("access_token", "access_token");
        return Response.setResponse(true, HttpStatus.OK, response);
    }
    @GetMapping
    public ResponseEntity findAll() {
        return Response.setResponse(true, HttpStatus.OK, userServiceImpl.getAll());
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity findById(@PathVariable("userId") Long userInd) {
        return Response.setResponse(true, HttpStatus.OK, userServiceImpl.findById(userInd));
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody UserRegisterForm userRegisterForm) {
        UserEntity userInfo = new UserEntity(
                userRegisterForm.getName(),
                userRegisterForm.getUsername(),
                userRegisterForm.getPassword(),
                userRegisterForm.getEmail()
        );
        return Response.setResponse(true, HttpStatus.OK, userServiceImpl.create(userInfo));
    }
}
