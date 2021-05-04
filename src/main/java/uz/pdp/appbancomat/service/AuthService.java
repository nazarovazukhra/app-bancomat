package uz.pdp.appbancomat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appbancomat.entity.Card;
import uz.pdp.appbancomat.entity.User;
import uz.pdp.appbancomat.payload.ApiResponse;
import uz.pdp.appbancomat.payload.LoginDto;
import uz.pdp.appbancomat.payload.RegisterDto;
import uz.pdp.appbancomat.repository.CardRepository;
import uz.pdp.appbancomat.repository.RoleRepository;
import uz.pdp.appbancomat.repository.UserRepository;
import uz.pdp.appbancomat.security.JwtProvider;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    CardRepository cardRepository;


    public ApiResponse registerUser(RegisterDto registerDto) {

        boolean existsByEmail = userRepository.existsByEmail(registerDto.getEmail());
        if (existsByEmail)
            return new ApiResponse("Bunday email allaqachon mavjud", false);

        User user = new User();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRoles(registerDto.getRoleName());

        user.setEmailCode(UUID.randomUUID().toString());

        userRepository.save(user);

        // EMAILGA YUBORISH METHOD I
        sendEmail(user.getEmail(), user.getEmailCode());
        return new ApiResponse("Muvaffaqiyatli ro'yxatdan o'tdingiz ,account ingizni aktivlashtirilishi uchun email ingizni tasdqilang", true);

    }

    public Boolean sendEmail(String sendingEmail, String emailCode) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("test@Pdp.com");
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("Account ni Tasdiqlash");
            mailMessage.setText("<a href='http://localhost:8080/api/auth/verifyEmail?emailCode=" + emailCode + "&email=" + sendingEmail + "'>Tasdiqlang</a>");
            javaMailSender.send(mailMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public ApiResponse verifyEmail(String emailCode, String email) {
        Optional<User> optionalUser = userRepository.findAllByEmailAndEmailCode(email, emailCode);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEnabled(true);
            user.setEmailCode(null);
            userRepository.save(user);
            return new ApiResponse("Account tasdiqlandi", true);
        }
        return new ApiResponse("Account allaqachon tasdiqlangan", false);

    }

    public ApiResponse login(String pinCode) {
        try {

            Optional<Card> optionalCard = cardRepository.findCardByPinCode(pinCode);

                if (!optionalCard.isPresent())
                    return new ApiResponse("Wrong pin code", false);
                String cardPinCode = optionalCard.get().getPinCode();
                User user = optionalCard.get().getUser();

//            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
//                    (user1.getUsername(), user1.getPassword()));
//            User user = (User) authentication.getPrincipal();
                String token = jwtProvider.generateToken(user.getUsername(), user.getRoles());

                if (pinCode.equals(cardPinCode)) {
                    return new ApiResponse("Token", true, token);
                } else {
                    return new ApiResponse("PinCode xato", false);
                }


        } catch (BadCredentialsException badCredentialsException) {
            return new ApiResponse("Parol yoki login xato", false);
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        return userRepository.findAllByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username + "topilmadi"));
    }
}
