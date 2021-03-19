package com.czerwo.reworktracking.ftrot.roles.account;

import com.czerwo.reworktracking.ftrot.cloudinary.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AccountService {


    private final CloudinaryService cloudinaryService;

    public AccountService(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }


    public String uploadFile(MultipartFile image) {

        return cloudinaryService.uploadFile(image);

    }
}
