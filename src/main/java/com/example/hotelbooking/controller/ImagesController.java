package com.example.hotelbooking.controller;

import com.example.hotelbooking.entity.Image;
import com.example.hotelbooking.service.ImagesService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/images")
public class ImagesController {

    private final ImagesService imagesService;

    public ImagesController(ImagesService imagesService) {
        this.imagesService = imagesService;
    }

    @GetMapping("/{image_id}")
    public void getImage(@PathVariable Long image_id, HttpServletResponse response) throws IOException {
        Image image = imagesService.get(image_id);
        String imageExtension = image.getName().substring(image.getName().lastIndexOf("."));
        response.setContentType("image/" + (imageExtension.isEmpty() ? "jpeg" : imageExtension));
        InputStream is = new ByteArrayInputStream(image.getPictureBytes());
        IOUtils.copy(is, response.getOutputStream());
    }

    @PostMapping("/add")
    public void uploadImage(@RequestParam(value = "file") MultipartFile file) {

        imagesService.save(file);
    }

    @PostMapping("/delete/{image_id}")
    public void deleteImage(@PathVariable Long image_id) {
        imagesService.remove(image_id);
    }
}
