package de.awacademy.weblogTilLeif.image;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

	private ImageRepository imageRepository;

	public ImageService(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}

	public String getImage(String id) {
		return Base64.encodeBase64String(imageRepository.findFirstById(id).getPicture());
	}
}
