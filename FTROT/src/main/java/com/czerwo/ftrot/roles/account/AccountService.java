package com.czerwo.ftrot.roles.account;

import com.czerwo.ftrot.auth.ApplicationUser;
import com.czerwo.ftrot.auth.ApplicationUserRepository;
import com.czerwo.ftrot.models.exceptions.User.UserNotFoundException;
import com.czerwo.ftrot.cloudinary.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class AccountService {


    private final CloudinaryService cloudinaryService;
    private final ApplicationUserRepository applicationUserRepository;

    public AccountService(CloudinaryService cloudinaryService, ApplicationUserRepository applicationUserRepository) {
        this.cloudinaryService = cloudinaryService;
        this.applicationUserRepository = applicationUserRepository;
    }


    public String uploadFile(MultipartFile image, String username) {

        Optional<ApplicationUser> userWithInfoByUsername = applicationUserRepository.findByUsernameWithUserInfo(username);
        String pictureUrl = cloudinaryService.uploadFile(image);
        userWithInfoByUsername
                .map(ApplicationUser::getUserInfo)
                .ifPresent((info) -> info.setPictureUrl(pictureUrl));
        applicationUserRepository.save(userWithInfoByUsername.orElseThrow(() -> new UserNotFoundException(username)));
        return pictureUrl;

    }
}
