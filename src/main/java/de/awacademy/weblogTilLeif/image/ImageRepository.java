package de.awacademy.weblogTilLeif.image;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, String> {
	Image findFirstById(String id);
}
