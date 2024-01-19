package com.nikak.pspkurssecurity.controllers;


import com.nikak.pspkurssecurity.entities.Favor;
import com.nikak.pspkurssecurity.entities.Session;
import com.nikak.pspkurssecurity.entities.Specialist;
import com.nikak.pspkurssecurity.services.FavorService;
import com.nikak.pspkurssecurity.services.SessionService;
import com.nikak.pspkurssecurity.services.SpecialistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/public/info")
@RequiredArgsConstructor
public class PublicInfoController {

    private final FavorService favorService;
    private final SessionService sessionService;
    private final SpecialistService specialistService;

    @GetMapping("/specialists/certificate/{fileName}")
    public ResponseEntity<?> getSpecialistCertificate(
            @PathVariable String fileName
    ) throws IOException {
        byte[] im = specialistService.getCertificateImage(fileName);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(im);
    }

    @GetMapping("/specialist/{specialistId}")//работает
    public ResponseEntity<List<Favor>> getListFavors(
            @PathVariable Long specialistId
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                //.header("Access-Control-Allow-Origin", "*")
                .body(favorService.findFavorsBySpecialistId(specialistId));
    }

    @GetMapping("/specialists1/{favorId}")
    public ResponseEntity<List<Specialist>> getListFavorsByRating(
            @PathVariable Long favorId,
            @RequestParam boolean order
    ) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(specialistService.getSpecialistsByFavorId(favorId,order));
    }

    @GetMapping("/favors")//хз
    public ResponseEntity<?> getListFavors(
            @RequestParam String name
    ) {

        return ResponseEntity
                .status(HttpStatus.OK)
                //.header("Access-Control-Allow-Origin", "*")
                .body(favorService.findFavorsWithCounts(name));
    }

    @GetMapping("/favor")//да
    public ResponseEntity<?> getListFavors(
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                //.header("Access-Control-Allow-Origin", "*")
                .body(favorService.findAllFavors());
    }

    @GetMapping("/favors/pic/{filename}")
    public ResponseEntity<?> getFavorImage(
            @PathVariable String filename
    ) throws IOException {
        byte[] im = favorService.getFavorImage(filename);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(im);
    }

    @GetMapping("/specialists/pic/{filename}")
    public ResponseEntity<?> getSpecialistImage(
            @PathVariable String filename
    ) throws IOException {
        byte[] im = specialistService.getSpecialistImage(filename);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(im);
    }



   /* @GetMapping("subject/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Subject subject = subjectService.getSubjectById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .header("Access-Control-Allow-Origin", "*")
                .contentType(MediaType.valueOf(subject.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + subject.getFilename() + "\"")
                .body(subject.getData());
    }*/

    @GetMapping("/specialists/{favorId}")//работает
    public ResponseEntity<List<Specialist>> getListSpecialists(
            @PathVariable Long favorId
    ) {

        return ResponseEntity
                .status(HttpStatus.OK)
                //.header("Access-Control-Allow-Origin", "*")
                .body(specialistService.findSpecialistsByFavorId(favorId));
    }




   @GetMapping(path = "/one/{id}")//работает
   public ResponseEntity<?> getSessions(@PathVariable("id") Long id){
       return ResponseEntity.status(HttpStatus.OK)
               //.header("Access-Control-Allow-Origin", "*")
               .contentType(MediaType.APPLICATION_JSON)
               .body(sessionService.findSessionById(id));
   }

    @GetMapping(path = "/{specialistId}")//работфет
    public ResponseEntity<?> getSessionsBySpecialistId(@PathVariable("specialistId") Long specialistId){
        return ResponseEntity.status(HttpStatus.OK)
                //.header("Access-Control-Allow-Origin", "*")
                .contentType(MediaType.APPLICATION_JSON)
                .body(sessionService.findSessionsBySpecialistId(specialistId));
    }

    @GetMapping(path = "freeTime/{specialistId}")//работает
    public ResponseEntity<?> getFreeTime(@PathVariable("specialistId") Long specialistId,
                                         @RequestParam String date){
        Date sqlDate = Date.valueOf(date);
        System.out.println(sqlDate);
        return ResponseEntity.status(HttpStatus.OK)
                //.header("Access-Control-Allow-Origin", "*")
                .contentType(MediaType.APPLICATION_JSON)
                .body(sessionService.findFreeTime(sqlDate,specialistId));
    }

    @GetMapping(path = "popular")
    public ResponseEntity<?> getMostPopularFavors(){
        //return subjectService.getAll();

        return ResponseEntity.status(HttpStatus.OK)
                //.header("Access-Control-Allow-Origin", "*")
                .contentType(MediaType.APPLICATION_JSON)
                .body(favorService.getMostPopularFavors(PageRequest.of(0, 3)));
    }

    @GetMapping("/specialists/one/{specialistId}")
    public ResponseEntity<Optional<Specialist>> getSpecialistById(
            @PathVariable Long specialistId
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                //.header("Access-Control-Allow-Origin", "*")
                .body(specialistService.findById(specialistId));
    }

    @GetMapping("/sessions")
    public ResponseEntity<List<Session>> getSessions(
    ){
        return ResponseEntity.status(HttpStatus.OK).body(
                sessionService.getSessions()
        );
    }
}

