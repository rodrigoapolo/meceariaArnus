package com.arnus.merceariaarnus.repository;

import com.arnus.merceariaarnus.model.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailModel, Long> {
}