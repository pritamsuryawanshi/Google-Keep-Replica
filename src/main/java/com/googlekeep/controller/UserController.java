package com.googlekeep.controller;

import com.googlekeep.dto.RegistrationDTO;
import com.googlekeep.dto.ResponseDTO;
import com.googlekeep.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@CrossOrigin(exposedHeaders = "Authorization")
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> userRegistration(@Valid @RequestBody RegistrationDTO registrationDTO, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(bindingResult.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        String message = userService.userRegistration(registrationDTO,request.getHeader("Referer"));
        ResponseDTO responseDTO = new ResponseDTO(message);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}