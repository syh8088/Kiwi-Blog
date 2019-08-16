package kiwi.blog.image.service;

import kiwi.blog.image.controller.ImageController;
import kiwi.blog.image.model.entity.Image;
import kiwi.blog.image.repository.ImageRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class ImageService {

  @Value("${environments.server-url}")
  private String serverUrl;

  @Value("${server.port}")
  private String serverPort;

  private ImageRepository imageRepository;

  @Autowired
  public ImageService(ImageRepository imageRepository) {
    this.imageRepository = imageRepository;
  }

  public Image getImage(long imageNo) {
    return imageRepository.findById(imageNo).get();
  }

  @Transactional
  public String saveImage(MultipartFile multipartFile) {

    Image image;

    try {
      image = new Image(Objects.requireNonNull(multipartFile.getOriginalFilename()), multipartFile.getContentType(), multipartFile.getBytes());

      return serverUrl + ":" + serverPort + ImageController.IMAGE_API_PREFIX + "/" + imageRepository.save(image).getImageNo();
    } catch (IOException ignored) {

    }
    return Strings.EMPTY;
  }

}
