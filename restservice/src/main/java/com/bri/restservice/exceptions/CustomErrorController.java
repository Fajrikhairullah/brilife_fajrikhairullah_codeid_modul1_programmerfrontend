//package com.bri.restservice.exceptions;
//
//import com.bri.restservice.exceptions.PathNotFoundException;
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.security.authentication.InsufficientAuthenticationException;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class CustomErrorController implements ErrorController {
//
//    @GetMapping("/error")
//    public void handleError(HttpServletRequest request) {
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        if (statusCode == 401) {
//            throw new InsufficientAuthenticationException("Unauthorized.");
//        }else {
//        throw new PathNotFoundException();
//    }
//    }
//    
//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }
//
//}
