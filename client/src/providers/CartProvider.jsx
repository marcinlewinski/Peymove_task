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
        const findItemIndex = cartItems.findIndex(item => item.id === itemId);

        if (findItemIndex !== -1) {
            setCartItems(prev => {
                const updatedCart = [...prev];
                updatedCart[findItemIndex].quantity += amount;
                return updatedCart;
            });
        } else {
            setCartItems(prev => [
                ...prev,
                {
                    id: itemId,
                    quantity: amount
                }
            ]);
        }
    };

    const removeFromCart = (itemId) => {
        const findIndex = cartItems.findIndex(item => item.id === itemId);

        if (findIndex !== -1 && cartItems[findIndex].quantity > 0) {
            setCartItems(prevState => {
                const updatedCart = [...prevState];
                updatedCart[findIndex].quantity--;
                return updatedCart;
            });
        }
    };




    return (
        <CartContext.Provider value={{addToCart, removeFromCart, cartItems}}>
            {children}
        </CartContext.Provider>
    )

}