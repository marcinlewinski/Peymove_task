import React from "react";
import Decimal from 'decimal.js';

export const calculateCartItemData = (cartItem, product) => {
    const cartItemPrice = cartItem ? new Decimal(cartItem.quantity).times(product.price) : new Decimal(0);
    const totalPrice = cartItem && cartItem.quantity > 0 ? cartItemPrice.toString() : product.price.toString();
    const amount = cartItem ? cartItem.quantity : 0;

    return {
        cartItemPrice,
        totalPrice,
        amount,
    };
};