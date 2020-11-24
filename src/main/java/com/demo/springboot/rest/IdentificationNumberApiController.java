package com.demo.springboot.rest;

import com.demo.springboot.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class IdentificationNumberApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdentificationNumberApiController.class);

    @CrossOrigin
    @GetMapping(value = "/check-identification-number")
    public ResponseEntity<Boolean> checkIdentificationNumber(@RequestParam(defaultValue = "") String id) {
        LOGGER.info("--- check identification number: {}", id);

        Validator v = new Validator(LOGGER);

        // TODO: Bardzo prosze dokonczyc implementacje walidacji numeru PESEL

        // TODO: Jesli pesel jest poprawny usluga powinna zwracac kod bledu 200 (OK)
        // TODO: W przeciwnym wypadku usluga powinna zwracac kod bledu 400 (BAD REQUEST)
        // TODO: Bardzo prosze unikac implementacji logiki w kontrolerze!

        if(v.isValid(id)){
            return new ResponseEntity<>(v.isValid(id),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



}
