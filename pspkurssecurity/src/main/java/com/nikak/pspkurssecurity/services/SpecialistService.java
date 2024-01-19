package com.nikak.pspkurssecurity.services;

import com.nikak.pspkurssecurity.dto.SpecialistProfileRequest;
import com.nikak.pspkurssecurity.entities.Specialist;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface SpecialistService {
   /* Teacher uploadPic(MultipartFile file, String email) throws IOException;


    Teacher getTeacherById(Long id);*/

 String addCertificate(Long specialistId, MultipartFile file) throws IOException;

 String deleteCertificate(Long specialistId, Long certificateId) throws IOException;

 String createSpecialist(MultipartFile file, String name, String info) throws IOException;
   List<Specialist> findSpecialistsByFavorId(Long favorId);
 List<Specialist> getSpecialistsByFavorId(Long favorId, boolean order);


    byte[] getSpecialistImage(String filename) throws IOException;

    String updateSpecialistImage(Long specialistId, MultipartFile file) throws IOException;

    String deleteSpecialistImage(Long specialistId) throws IOException;

    Specialist updateSpecialistProfile(SpecialistProfileRequest specialistProfileRequest, Long specialistId);

    String deleteSpecialist(Long specialistId) throws IOException;

 Specialist assignFavorToSpecialist(Long specialistId, Long favorId);

 Specialist deleteFavorFromSpecialist(Long specialistId, Long favorId);

 String updateSpecialistName(Long specialistId, String name);

 byte[] getCertificateImage(String filename) throws IOException;

 Optional<Specialist> findById(Long specialistId);

 String updateSpecialistInfo(Long specialistId, String info);

 List<Specialist> findAll();
 String updateSpecialistImageCertificate(Long specialistId, MultipartFile file) throws IOException;

}
