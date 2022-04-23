package uz.gullbozor.gullbozor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.dto.RegisterDto;
import uz.gullbozor.gullbozor.entity.UserEntity;
import uz.gullbozor.gullbozor.repository.RoleRepo;
import uz.gullbozor.gullbozor.repository.UserRepo;


import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    public ApiResponse registerUser(RegisterDto registerDto) {

        if (!userRepo.existsByEmail(registerDto.getEmail())) {
            return new ApiResponse("This email already exists",false);
        }

        UserEntity user = new UserEntity();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setUserName(registerDto.getUserName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));


        user.setEmailCode(UUID.randomUUID().toString());//?

        userRepo.save(user);

        //Emailga habar yuborish methodini chaqiramiz.

        sendEmail(user.getEmail(), user.getEmailCode());
        return new ApiResponse("Muvaffaqiyatli ro'yxatdan o'tdingiz. Email habarlarga kirib tasdiqlang!!!");



    }

    public Boolean sendEmail(String sendingEmail, String emailCode) {

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("GullBozor.uz");
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("Akkountni tasdiqlash");
            mailMessage.setText("<a href='http:/localhost:8080/auth/verifyEmail?emailCode=" + emailCode + "+&email=" + sendingEmail + "'> Tasdiqlang</a>");
            return true;
        }catch (Exception e) {
            return false;
        }
    }


}
