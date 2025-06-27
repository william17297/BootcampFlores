package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

// add the annotations to make this a REST controller
// add the annotation to make this controller the endpoint for the following url
    // http://localhost:8080/categories
// add annotation to allow cross site origin requests
@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoriesController {
    private CategoryDao categoryDao;
    private ProductDao productDao;


    // create an Autowired controller to inject the categoryDao and ProductDao
    @Autowired
    public CategoriesController(CategoryDao categoryDao , ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }


    // add the appropriate annotation for a get action
    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        var categories = categoryDao.getAllCategories();
        // find and return all categories
        return new ResponseEntity<>(categories , HttpStatus.OK);
    }


    // add the appropriate annotation for a get action
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable int id) {
        Category category = categoryDao.getById(id);
        // get the category by id
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
    }

    // the url to return all products in category 1 would look like this
    // http://localhost:8080/categories/1/products
    @GetMapping("{category_id}/products")
    public List<Product> getProductsById(@PathVariable int category_id)
    {
        List<Product> product = productDao.listByCategoryId(category_id);
        // get a list of product by categoryId
        if(product == null || product.isEmpty()){
            return new ArrayList<>();
        }
        return product;
    }


    // add annotation to call this method for a POST action
    // add annotation to ensure that only an ADMIN can call this function
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category)
    {
        boolean created = categoryDao.create(category);
        // insert the category


        if(created){
            return new ResponseEntity<>(category , HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // add annotation to call this method for a PUT (update) action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    @PutMapping("/{category_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateCategory(@PathVariable int category_id, @RequestBody Category category)
    {
        // update the category by id
        Category existing = categoryDao.getById(category_id);
        if(existing == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        category.setCategoryId(category_id);
        boolean updated = categoryDao.update(category_id , category);

        if(updated){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // add annotation to call this method for a DELETE action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    @DeleteMapping("/{category_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable int category_id)
    {
        Category existing = categoryDao.getById(category_id);
        if(existing == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        boolean successfullyDeleted = categoryDao.delete(category_id);
        if(successfullyDeleted){
            //204 NO CONTENT
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        // delete the category by id
    }
}
