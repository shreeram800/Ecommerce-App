package com.example.ecommerceapp.Repository;

import com.example.ecommerceapp.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepo extends JpaRepository<Category,Long> {
    @Query("select c from Category c where c.name=:name And c.parentCategory.name=:parentCategory")
    Category findByNameAndParentCategory(@Param("name") String name, @Param("parentCategoryName") String parent);

    Category findByName(String name);
}
