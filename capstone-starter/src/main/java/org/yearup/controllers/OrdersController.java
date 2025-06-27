package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProfileDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.data.mysql.MySqlOrderDao;
import org.yearup.models.Profile;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;

import java.security.Principal;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrdersController {
    UserDao userDao;
    ShoppingCartDao shoppingCartDao;
    ProfileDao profileDao;
    MySqlOrderDao mySqlOrderDao;

    @Autowired
    public OrdersController(UserDao userDao, ShoppingCartDao shoppingCartDao, ProfileDao profileDao, MySqlOrderDao mySqlOrderDao) {
        this.userDao = userDao;
        this.shoppingCartDao = shoppingCartDao;
        this.profileDao = profileDao;
        this.mySqlOrderDao = mySqlOrderDao;
    }

    @PostMapping("")
    @PreAuthorize("isAuthenticated()")
    public void makeOrder(Principal principal) {
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }
        String userName = principal.getName();
        User user = userDao.getByUserName(userName);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + userName);
        }
        int userId = user.getId();
        ShoppingCart shoppingCart = shoppingCartDao.getByUserId(userId);
        mySqlOrderDao.create(userId , profileDao);


        for (ShoppingCartItem item : shoppingCart.getItems().values()) {
            System.out.println("Item: " + item.getProductId());

        }

    }
}
