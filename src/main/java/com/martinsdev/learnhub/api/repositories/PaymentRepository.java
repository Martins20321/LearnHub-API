package com.martinsdev.learnhub.api.repositories;

import com.martinsdev.learnhub.api.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
