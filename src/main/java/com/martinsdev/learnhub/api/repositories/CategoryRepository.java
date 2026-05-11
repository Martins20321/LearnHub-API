package com.martinsdev.learnhub.api.repositories;

import com.martinsdev.learnhub.api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
