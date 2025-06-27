package org.yearup.data;

import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);
    //boolean create(int userId ,int product_id);
    ShoppingCart create(int userId ,int product_id);
    boolean update(int productId , int userId , int quantity);
    ShoppingCart delete(int userId);
    // add additional method signatures here
}
