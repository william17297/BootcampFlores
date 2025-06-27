package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.models.*;

import javax.validation.Valid;
import java.security.Principal;

// convert this class to a REST controller
// only logged-in users should have access to these actions
@RestController
@RequestMapping("/cart")
@CrossOrigin
public class ShoppingCartController {
    // a shopping cart requires
    private ShoppingCartDao shoppingCartDao;
    private UserDao userDao;
    private ProductDao productDao;

    @Autowired
    public ShoppingCartController(ShoppingCartDao shoppingCartDao, UserDao userDao, ProductDao productDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.userDao = userDao;
        this.productDao = productDao;
    }

// each method in this controller requires a Principal object as a parameter

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ShoppingCart getCart(Principal principal) {
        try {
            if (principal == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
            }
            // get the currently logged-in username
            String userName = principal.getName();
            // find database user by userId
            User user = userDao.getByUserName(userName);
            if (user == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + userName);
            }
            // use the shoppingcartDao to get all items in the cart and return the cart
            int userId = user.getId();

            return shoppingCartDao.getByUserId(userId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    // add a POST method to add a product to the cart - the url should be
    // https://localhost:8080/cart/products/15 (15 is the productId to be added
    @PostMapping("/products/{product_id}")
    @PreAuthorize("isAuthenticated()")
    public ShoppingCart addProduct(Principal principal, @PathVariable int product_id) {
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }
        String userName = principal.getName();
        User user = userDao.getByUserName(userName);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + userName);
        }
        int userId = user.getId();
        //ShoppingCart shoppingCart = shoppingCartDao.getByUserId(userId);
        ShoppingCart shoppingCart = getCart(principal);
        if (!shoppingCart.contains(product_id)) {
             shoppingCartDao.create(userId, product_id);

        } else if (shoppingCart.contains(product_id)) {
            shoppingCartDao.update(product_id, shoppingCart.get(product_id), userId, shoppingCart.get(product_id).getQuantity() + 1);

        }
        return shoppingCartDao.getByUserId(userId);
    }


    // add a PUT method to update an existing product in the cart - the url should be
    // https://localhost:8080/cart/products/15 (15 is the productId to be updated)
    // the BODY should be a ShoppingCartItem - quantity is the only value that will be updated
    @PutMapping("/products/{product_id}")
    @PreAuthorize("isAuthenticated()")
    public ShoppingCart updateProduct(Principal principal, @PathVariable int product_id, @Valid @RequestBody ShoppingCartItem shoppingCartItem) {
        // update the category by id
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }
        String userName = principal.getName();
        User user = userDao.getByUserName(userName);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + userName);
        }
        int userId = user.getId();
        if (!getCart(principal).contains(product_id)) {
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean updated = shoppingCartDao.update(product_id, shoppingCartItem, userId, shoppingCartItem.getQuantity());
        if (updated) {
            //return new ResponseEntity<>(HttpStatus.OK);
        }
        return getCart(principal);
        //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    // add a DELETE method to clear all products from the current users cart
    // https://localhost:8080/cart

    @DeleteMapping("")
    @PreAuthorize("isAuthenticated()")
    public ShoppingCart deleteProduct(Principal principal) {
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }
        String userName = principal.getName();
        User user = userDao.getByUserName(userName);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + userName);
        }
        int userId = user.getId();
        try {
            shoppingCartDao.delete(userId);

            return shoppingCartDao.getByUserId(userId);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
}
