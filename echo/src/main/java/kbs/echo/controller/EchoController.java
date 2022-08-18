package kbs.echo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class EchoController {

    @GetMapping("/**")
    ResponseEntity echoGet() {

        String request = "";

        return ResponseEntity
                .ok()
                .body(request);
    }

    @PostMapping("/**")
    ResponseEntity echoPost() {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/**")
    ResponseEntity echoPut() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/**")
    ResponseEntity echoPatch() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/**")
    ResponseEntity echoDelete() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
