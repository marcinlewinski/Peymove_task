import React, {useEffect, useState} from "react";
import CssBaseline from "@mui/material/CssBaseline";
import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid";
import {ShoppingCartItem} from "./ShopingCartItem";
import {MockProducts} from "../products/MockProducts";
import {OrderSummary} from "./OrderSummary";
import {useCart} from "../../providers/CartProvider";
import Box from "@mui/material/Box";
import {useProduct} from "../../providers/ProductProvider";
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";

export const ShoppingCart = () => {
    const { productList } = useProduct();
    const {cartItems} = useCart();
    const [productOrder, setProductOrder] = useState(null);
    const [totalPrice, setTotalPrice] = useState(0);

    useEffect(() => {
        const productsWithQuantity = productList?.map((product) => {
            const cartItem = cartItems.find((item) => item.id === product.id);
            const quantity = cartItem ? cartItem.quantity : 0;
            if (cartItem) {
                return {...product, quantity};
            }
            return null;

        });

        setProductOrder(productsWithQuantity.filter(Boolean));

        const calculatedTotalPrice = productsWithQuantity.reduce((acc, product) => {

            if (product) {
                return acc + product.price * product.quantity;
            }
            return acc;
        }, 0);

        setTotalPrice(calculatedTotalPrice);
    }, [productList, cartItems]);
    return (

            <Container fixed>
                <Grid container spacing={3}>
                    <Grid item xs={12} sm={6} md={7} lg={7}>
                        <Grid container>
                            <Grid item xs>
                                {productOrder && productOrder.length > 0 ? (
                                    productOrder.map((product) => (
                                        <ShoppingCartItem key={product.id} product={product}/>
                                    ))
                                ) : (
                                    <Paper elevation={1} sx={{margin: "2rem", padding: "20px", textAlign: "center"}}>

                                        <Typography variant='body3'>
                                           Your cart is empty!
                                        </Typography>
                                    </Paper>
                                )}
                            </Grid>
                        </Grid>
                    </Grid>
                    <Grid item xs={12} sm={6} md={5} lg={5}>
                        <OrderSummary productOrder={productOrder} totalPrice={totalPrice}/>
                    </Grid>
                </Grid>
            </Container>
    );
};
