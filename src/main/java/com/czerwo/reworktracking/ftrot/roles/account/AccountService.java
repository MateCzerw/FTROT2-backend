package com.czerwo.reworktracking.ftrot.roles.account;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUser;
import com.czerwo.reworktracking.ftrot.auth.ApplicationUserRepository;
import com.czerwo.reworktracking.ftrot.cloudinary.CloudinaryService;
import com.czerwo.reworktracking.ftrot.models.exceptions.User.UserNotFoundException;
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
