package com.maxkors.librebnb.infrastructure;

import com.maxkors.librebnb.domain.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}
