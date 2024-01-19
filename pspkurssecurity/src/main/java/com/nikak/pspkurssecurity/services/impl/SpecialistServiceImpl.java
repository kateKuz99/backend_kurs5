package com.nikak.pspkurssecurity.services.impl;

import com.nikak.pspkurssecurity.dto.SpecialistProfileRequest;
import com.nikak.pspkurssecurity.entities.Certificate;
import com.nikak.pspkurssecurity.entities.Favor;
import com.nikak.pspkurssecurity.entities.Specialist;
import com.nikak.pspkurssecurity.entities.User;
import com.nikak.pspkurssecurity.repositories.CertificateRepository;
import com.nikak.pspkurssecurity.repositories.FavorRepository;
import com.nikak.pspkurssecurity.repositories.SpecialistRepository;
import com.nikak.pspkurssecurity.repositories.UserRepository;
import com.nikak.pspkurssecurity.services.SpecialistService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SpecialistServiceImpl implements SpecialistService {
    private final String FOLDER_PATH = "E:/курсач5сем/images";
    private final SpecialistRepository specialistRepository;
    private final UserRepository userRepository;
    private final CertificateRepository certificateRepository;

    private final FavorRepository favorRepository;

    public String updateSpecialistImage(Long specialistId, MultipartFile file) throws IOException {

        Specialist specialist = specialistRepository.findById(specialistId)
                .orElseThrow(() -> new IllegalStateException("no such specialist with id "));

        if (file != null) {
            if (specialist.getFilename() != null) {

                String filename = specialist.getFilename();
                Path saveTO = Paths.get(FOLDER_PATH + filename);
                Files.delete(saveTO);
                Files.copy(file.getInputStream(), saveTO);
            }
            if (specialist.getFilename() == null) {
                String filename = UUID.randomUUID().toString() + ".jpg";
                Path saveTO = Paths.get(FOLDER_PATH + filename);

                specialist.setFilename(filename);
                // savedData =  subjectRepository.save(subjectToSave);
                Files.copy(file.getInputStream(), saveTO);
            }
        }

        Specialist savedSpecialist = specialistRepository.save(specialist);
        if (savedSpecialist != null) return "specialist pic : " + savedSpecialist.getFilename() + " updated successfully";
        return null;

    }

    public String updateSpecialistImageCertificate(Long specialistId, MultipartFile file) throws IOException {
        Specialist specialist = specialistRepository.findById(specialistId)
                .orElseThrow(() -> new IllegalStateException("No such specialist with id " + specialistId));

        Certificate certificate = specialist.getCertificate();
        if (certificate == null) {
            certificate = new Certificate();
            specialist.setCertificate(certificate);
        }


        if (file != null) {
            if (certificate.getFileName() != null) {
                String filename = certificate.getFileName();
                Path saveTo = Paths.get(FOLDER_PATH + filename);
                Files.delete(saveTo);
                Files.copy(file.getInputStream(), saveTo);
            } else {
                String filename = UUID.randomUUID().toString() + ".jpg";
                Path saveTo = Paths.get(FOLDER_PATH + filename);
                certificate.setFileName(filename);
                Files.copy(file.getInputStream(), saveTo);
            }
        }
        System.out.println(file);
        Specialist savedSpecialist = specialistRepository.save(specialist);
        if (savedSpecialist != null) {
            return "Specialist certificate pic2: " + savedSpecialist.getCertificate().getFileName() + " updated successfully";
        }
        return null;
    }

    @Transactional
    public Specialist updateSpecialistProfile(SpecialistProfileRequest specialistProfileRequest, Long specialistId) {
        Specialist specialist = specialistRepository.findById(specialistId)
                .orElseThrow(() -> new IllegalStateException("no such specialist with id"));
        System.out.println("specialist found");
        if (specialistProfileRequest.getName() != null) {
            specialist.setName(specialistProfileRequest.getName());
        }
        if (specialistProfileRequest.getInfo() != null) {
            specialist.setInfo(specialistProfileRequest.getInfo());
        }
        if (specialistProfileRequest.getFavorsIds() != null) {
            Set<Favor> favors = new HashSet<>();
            for (Long id : specialistProfileRequest.getFavorsIds()) {
                System.out.println("favor");
                Optional<Favor> favor = favorRepository.findById(id);
                if(favor.isPresent()) {
                    favors.add(favor.get());
                }
            }
            specialist.setSpecialistFavors(favors);
        }
        return specialistRepository.save(specialist);

    }

    public String deleteSpecialistImage(Long specialistId) throws IOException {
        Specialist specialist = specialistRepository.findById(specialistId)
                .orElseThrow(() -> new IllegalStateException("no such specialist with id"));

        if (specialist.getFilename() != null) {
            Path saveTO = Paths.get(FOLDER_PATH + specialist.getFilename());
            Files.delete(saveTO);
            specialist.setFilename(null);
        }

        Specialist savedSpecialist = specialistRepository.save(specialist);
        if (savedSpecialist != null) return "specialist pic deleted successfully";
        return null;
    }
    /*public Teacher uploadPic(MultipartFile file, String email) throws IOException{

        User user  = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("no such user"));
        System.out.println(user);
        Teacher teacher = teacherRepository.findByUserId(user.getId())
                .orElseThrow(() -> new IllegalStateException("no such teacher with userid "+ user.getId()));
        System.out.println(teacher);
        if(file != null){
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            teacher.setFilename(filename);
            teacher.setType(file.getContentType());
            teacher.setData(file.getBytes());
        }

        return teacherRepository.save(teacher);
    }*/

    public  String addCertificate(Long specialistId, MultipartFile file) throws IOException {
        Specialist specialist = specialistRepository.findById(specialistId)
                .orElseThrow(() -> new IllegalStateException("no such specialist"));

        if (file != null) {
            String filename = UUID.randomUUID().toString() + ".jpg";
            Path saveTO = Paths.get(FOLDER_PATH + filename);
            Files.copy(file.getInputStream(), saveTO);
            certificateRepository.save(
                    new Certificate(filename, specialist)
            );

            return "certificate added successfully";
        }
        return null;
    }

    public String deleteCertificate(Long specialistId, Long certificateId) throws IOException {
        Specialist specialist = specialistRepository.findById(specialistId)
                .orElseThrow(() -> new IllegalStateException("no such specialist"));
        Certificate certificate = certificateRepository.findByIdAndSpecialist(certificateId, specialistId)
                .orElseThrow(() -> new IllegalStateException("no such certificate with id " + certificateId));
        Path saveTO = Paths.get(FOLDER_PATH + certificate.getFileName());
        Files.delete(saveTO);
        certificateRepository.deleteById(certificateId);

        return "certificate deleted successfully";

    }
    public List<Specialist> findSpecialistsByFavorId(Long favorId) {
        return specialistRepository.findByFavorId(favorId);
    }
    public byte[] getCertificateImage(String filename) throws IOException {
        Optional<Certificate> im = certificateRepository.findCertificateByFileName(filename);
        return Files.readAllBytes(
                new File(FOLDER_PATH + im.get().getSpecialist().getCertificate().getFileName()).toPath());

    }
    public byte[] getSpecialistImage(String filename) throws IOException {
        Optional<Specialist> im = specialistRepository.findSpecialistByFileName(filename);
        return Files.readAllBytes(
                new File(FOLDER_PATH + im.get().getFilename()).toPath());

    }

    public Optional<Specialist> findSpecialistById(Long id) {
        return specialistRepository.findById(id);
    }

    public String deleteSpecialist(Long specialistId) throws IOException {
        Specialist specialist = specialistRepository.findById(specialistId)
                .orElseThrow(()->
                        new IllegalStateException("specialist does not exist")
                );
       /* Path deletePath = Paths.get(FOLDER_PATH + specialist.getFilename());
        Files.delete(deletePath);*/
        specialistRepository.deleteById(specialistId);
        return "specialist "+ specialist.getName()+ " deleted successfully";
    }

    public String createSpecialist(MultipartFile file, String name, String info) throws IOException{
        Specialist specialistToSave = new Specialist();
        specialistToSave.setName(name);
        specialistToSave.setInfo(info);
        Specialist savedData;
        if(file != null){
            String filename = UUID.randomUUID().toString() + ".jpg";
            Path saveTO = Paths.get(FOLDER_PATH + filename);

            specialistToSave.setFilename(filename);
            savedData =  specialistRepository.save(specialistToSave);
            Files.copy(file.getInputStream(), saveTO);
        } else savedData =  specialistRepository.save(specialistToSave);

        if(savedData!=null) return "specialist : "+name+" added successfully";
        return null;
    }

    public Specialist assignFavorToSpecialist(Long specialistId, Long favorId) {

        Specialist specialist = specialistRepository.findById(specialistId)
                .orElseThrow(() -> new IllegalStateException(
                        "specialist with id " + specialistId + " does not exist"
                ));
        System.out.println(specialist.getSpecialistFavors());
        Favor favor = favorRepository.findById(favorId)
                .orElseThrow(() -> new IllegalStateException(
                        "favor with id " + favorId + " does not exist"
                ));
        Set<Favor> favorSet = specialist.getSpecialistFavors();
        System.out.println("favorset"+favorSet);
        favorSet.add(favor);
        specialist.setSpecialistFavors(favorSet);
        System.out.println(specialist.getSpecialistFavors());
        specialistRepository.save(specialist);
        return  specialist;
    }

    public Specialist deleteFavorFromSpecialist(Long specialistId, Long favorId) {
        Set<Favor> favorSet = null;
        Specialist specialist = specialistRepository.findById(specialistId)
                .orElseThrow(() -> new IllegalStateException(
                        "specialist with id " + specialistId + " does not exist"
                ));
        Favor favor = favorRepository.findById(favorId)
                .orElseThrow(() -> new IllegalStateException(
                        "favor with id " + favorId + " does not exist"
                ));
        favorSet = specialist.getSpecialistFavors();
        favorSet.remove(favor);
        specialist.setSpecialistFavors(favorSet);
        return specialistRepository.save(specialist);
    }

    public  String updateSpecialistName(Long specialistId, String name){
        Specialist specialist = specialistRepository.findById(specialistId)
                .orElseThrow(()->
                        new IllegalStateException("specialist does not exist")
                );
        if(name!=null){
            specialist.setName(name);
        }
       Specialist saved = specialistRepository.save(specialist);
        if(saved!=null) return "successfully updated specialist: "+ name;
        return "error";
    }

    public  String updateSpecialistInfo(Long specialistId, String info){
        Specialist specialist = specialistRepository.findById(specialistId)
                .orElseThrow(()->
                        new IllegalStateException("specialist does not exist")
                );
        if(info!=null){
            specialist.setInfo(info);
        }
        Specialist saved = specialistRepository.save(specialist);
        if(saved!=null) return "successfully updated specialist: "+ info;
        return "error";
    }

    public List<Specialist> findAll(){
        return specialistRepository.findAll();
    }
    public List<Specialist> getSpecialistsByFavorId(Long favorId, boolean order) {
        Comparator<Specialist> finalRatingComp1 = Comparator.comparingDouble(Specialist::getFinalRating);
        Comparator<Specialist> finalRatingComp2 = (o1, o2) -> Double.compare(o2.getFinalRating(), o1.getFinalRating());


                List<Specialist> list = specialistRepository.findByFavorId(favorId);
                if (order) {
                    list.sort(finalRatingComp2);
                } else {
                    list.sort(finalRatingComp1);
                }
                return list;
    }

    public Optional<Specialist> findById(Long specialistId){
        return specialistRepository.findById(specialistId);
    }
}
