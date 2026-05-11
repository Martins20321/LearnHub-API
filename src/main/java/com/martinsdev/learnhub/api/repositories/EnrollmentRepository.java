package com.martinsdev.learnhub.api.repositories;

import com.martinsdev.learnhub.api.model.Enrollment;
import com.martinsdev.learnhub.api.model.pk.EnrollmentPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentPK> {
}
