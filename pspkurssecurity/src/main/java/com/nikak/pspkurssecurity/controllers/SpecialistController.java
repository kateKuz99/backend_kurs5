package com.nikak.pspkurssecurity.controllers;

import com.nikak.pspkurssecurity.dto.ResponseMessage;
import com.nikak.pspkurssecurity.dto.SpecialistProfileRequest;
import com.nikak.pspkurssecurity.entities.Specialist;
import com.nikak.pspkurssecurity.services.JWTService;
import com.nikak.pspkurssecurity.services.SpecialistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/specialist")
@RequiredArgsConstructor
public class SpecialistController {

    private final JWTService jwtService;
    private final SpecialistService specialistService;


    @PutMapping("/pic/{specialistId}")
    public ResponseEntity<ResponseMessage> updateSpecialistPic(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam Long specialistId
    ) {
        String message = "";
        try {
            message = specialistService.updateSpecialistImage(specialistId, file);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PutMapping("/profile/{specialistId}")
    public ResponseEntity<Specialist> updateTeacherPic(
            @RequestBody SpecialistProfileRequest specialistProfileRequest,
            @RequestParam Long specialistId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                specialistService.updateSpecialistProfile(specialistProfileRequest, specialistId)
        );
    }

    @DeleteMapping("/pic/{specialistId}")
    public ResponseEntity<ResponseMessage> updateTeacherPic(
            @RequestParam Long specialistId
    ) {
        String message = "";
        try {
            message = specialistService.deleteSpecialistImage(specialistId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not delete pic !";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @GetMapping("")
    public ResponseEntity<String> greeting(
            @RequestHeader("Authorization") String bearerToken) {
        // code that uses the language variable
        String username = jwtService.extractUserName(bearerToken.substring(7));
        return new ResponseEntity<String>(username, HttpStatus.OK);
    }


}

