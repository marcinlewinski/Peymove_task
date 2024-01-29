import {createContext, useContext, useState} from "react";

const CartContext = createContext();

export const useCart = () => {
    const context = useContext(CartContext);
    if (!context) {
        throw new Error('useCart must be used within a CartProvider');
    }
    return context;
};
export const CartProvider = ({children}) => {
    const [cartItems, setCartItems] = useState([]);

    const addToCart = (itemId, amount = 1) => {
        setCartItems((prev) => ({
            ...prev,
            [itemId]: (prev[itemId] || 0) + 1,
        }));
    };
    console.log(cartItems)
    const removeFromCart = (itemId) => {
        if (cartItems[itemId] === 0) return;

        setCartItems((prev) => ({...prev, [itemId]: prev[itemId] - 1}));
    };

    const updateCartItemCount = (newAmount, itemId) => {
        setCartItems((prev) => ({...prev, [itemId]: newAmount}));
    };

    return (
        <CartContext.Provider value={{addToCart, removeFromCart, cartItems}}>
            {children}
        </CartContext.Provider>
    )

}