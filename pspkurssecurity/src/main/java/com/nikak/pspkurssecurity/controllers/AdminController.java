package com.nikak.pspkurssecurity.controllers;

import com.nikak.pspkurssecurity.dto.ResponseMessage;
import com.nikak.pspkurssecurity.dto.SessionRequest;
import com.nikak.pspkurssecurity.entities.Favor;
import com.nikak.pspkurssecurity.entities.Session;
import com.nikak.pspkurssecurity.entities.Specialist;
import com.nikak.pspkurssecurity.services.FavorService;
import com.nikak.pspkurssecurity.services.SessionService;
import com.nikak.pspkurssecurity.services.SpecialistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final FavorService favorService;

    private final SessionService sessionService;

    private final SpecialistService specialistService;



    @GetMapping
    public ResponseEntity<String> helloAdmin(){
        return ResponseEntity.ok("hello admin");
    }

    @PostMapping("/favor")
    public ResponseEntity<ResponseMessage> addFavor(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("price") Double price)
    {
        String message = "";
        try {
            message =  favorService.createFavor(file, name,price);
            System.out.println(message);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PostMapping("/specialist")
    public ResponseEntity<ResponseMessage> addSpecialist(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("info") String info){
        String message = "";
        try {
            message =  specialistService.createSpecialist(file,name,info);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @PutMapping("/favor/pic/{favorId}")
    public ResponseEntity<ResponseMessage> updateFavorPic(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @PathVariable Long favorId
    ){
        String message = "";
        try {
            message =  favorService.updateFavorImage(favorId,file);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PutMapping("/favor/name/{favorId}")
    public ResponseEntity<ResponseMessage> updateFavorName(
            @RequestParam(value = "name", required = false) String name,
            @PathVariable Long favorId
    ){
        String message = "";
        try {
            message =  favorService.updateFavorName(favorId, name);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not update favor name!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PutMapping("/favor/price/{favorId}")
    public ResponseEntity<ResponseMessage> updateFavorPrice(
            @RequestParam(value = "price", required = false) double price,
            @PathVariable Long favorId
    ){
        String message = "";
        System.out.println("wyte");
        try {
            message =  favorService.updateFavorPrice(favorId, price);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not update favor price!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @DeleteMapping("/favor/{favorId}")
    public ResponseEntity<ResponseMessage> deleteFavor(
            @PathVariable Long favorId
    ){
        String message = "";
        try {
            message =  favorService.deleteFavor(favorId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not update favor name!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

   /* @PostMapping("/specialist")
    public ResponseEntity<ResponseMessage> uploadSpecialistFile(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("name") String name) {
        String message = "";
        try {
            message =  specialistService.addSpecialist(file,name);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }*/

    @PutMapping("/specialist/pic/{specialistId}")
    public ResponseEntity<ResponseMessage> updateSpecialistImage(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @PathVariable Long specialistId
    ){
        String message = "";
        try {
            message =  specialistService.updateSpecialistImage(specialistId,file);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PutMapping("/specialist/certificate/pic/{specialistId}")
    public ResponseEntity<ResponseMessage> updateSpecialistImageCertificate(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @PathVariable Long specialistId
    ){
        String message = "";
        try {
            System.out.println(file+"qewr");
            message =  specialistService.updateSpecialistImageCertificate(specialistId,file);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {

            message = "Could not upload the file !";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PutMapping("/specialist/name/{specialistId}")
    public ResponseEntity<ResponseMessage> updateSpecialistName(
            @RequestParam(value = "name", required = false) String name,
            @PathVariable Long specialistId
    ){
        String message = "";
        try {
            message =  specialistService.updateSpecialistName(specialistId, name);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not update specialist name!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PutMapping("/specialist/info/{specialistId}")
    public ResponseEntity<ResponseMessage> updateSpecialistInfo(
            @RequestParam(value = "info", required = false) String info,
            @PathVariable Long specialistId
    ){
        String message = "";
        try {
            message =  specialistService.updateSpecialistInfo(specialistId, info);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not update specialist name!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @DeleteMapping("/specialist/{specialistId}")
    public ResponseEntity<ResponseMessage> deleteSpecialist(
            @PathVariable Long specialistId
    ){
        String message = "";
        try {
            message =  specialistService.deleteSpecialist(specialistId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not delete specialist!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PutMapping("assign/{specialistId}/{favorId}")
    public ResponseEntity<Specialist> assignFavorToSpecialist(
            @PathVariable Long specialistId,
            @PathVariable Long favorId
    ){
        return  ResponseEntity.status(HttpStatus.OK).body(
                specialistService.assignFavorToSpecialist(specialistId, favorId));
    }

    @DeleteMapping("assign/{specialistId}/{favorId}")
    public ResponseEntity<Specialist> deleteFavorFromSpecialist(
            @PathVariable Long specialistId,
            @PathVariable Long favorId
    ){
        return  ResponseEntity.status(HttpStatus.OK).body(
                specialistService.deleteFavorFromSpecialist(specialistId, favorId));
    }



    /*@GetMapping(path = "one/{id}")
    public ResponseEntity<ResponseMessage> getSessions(@PathVariable("id") Long id)
            throws IOException {
        String message = "";
        try {
            message =  sessionService.findSessionById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not find session";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }*/

    /*@GetMapping(path = "/session/{specialist_id}")
    public ResponseEntity<ResponseMessage> getSessionsBySpecialistId(@PathVariable("specialist_id") Long specialistId)  throws IOException {
        String message = "";
        try {
            message = sessionService.findSessionsBySpecialistId(specialistId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not find session by specialist id";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }*/

    @DeleteMapping("/session/{sessionId}")
    public ResponseEntity<ResponseMessage> deleteSession(
            @PathVariable Long sessionId
    ){
        String message = "";
        try {
            message =  sessionService.deleteSession(sessionId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not delete session!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }




   /* @GetMapping(path = "/session/freeTime/{specialistId}")
    public ResponseEntity<ResponseMessage> getFreeTime(
            @PathVariable("specialist_id") Long id,
            @RequestParam String date){
        String message = "";
        try {
            message =  sessionService.findFreeTime(date,id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not find dates!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }*/
   @PostMapping("/certificate/{specialistId}")
   public ResponseEntity<String> addCertificate(
           @PathVariable Long specialistId,
           @RequestParam(value = "file", required = false) MultipartFile file
   ){
       String message = "";
       try {
           message = specialistService.addCertificate(specialistId, file);
           return ResponseEntity.status(HttpStatus.OK).body(message);
       } catch (Exception e) {
           message = "Could not upload the file: " + file.getOriginalFilename() + "!";
           return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
       }
   }

    @DeleteMapping("/certificate/{specialistId}")
    public ResponseEntity<String> deleteCertificate(
            @PathVariable Long specialistId,
            @RequestParam(value = "certificateId", required = false) Long certificateId
    ){
        String message = "";
        try {
            message = specialistService.deleteCertificate(specialistId, certificateId);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }
    }
    @GetMapping("/favors")
    public ResponseEntity<List<Favor>> getAllFavors(){
        return ResponseEntity.ok(favorService.findAll());
    }

    @GetMapping("/specialists")
    public ResponseEntity<List<Specialist>> getAllSpecialists(){
        return ResponseEntity.ok(specialistService.findAll());
    }
}
