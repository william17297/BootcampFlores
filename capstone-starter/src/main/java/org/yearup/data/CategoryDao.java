package org.yearup.data;

import org.yearup.models.Category;

import java.util.List;

public interface CategoryDao
{
    List<Category> getAllCategories();
    Category getById(int categoryId);
    boolean create(Category category);
    boolean update(int categoryId, Category category);
    boolean delete(int categoryId);
}
