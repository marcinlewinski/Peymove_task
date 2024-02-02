import React from "react";
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from "@mui/material/Typography";
import Grid from "@mui/material/Grid";
import Button from "@mui/material/Button";
import AddIcon from "@mui/icons-material/Add";
import RemoveIcon from "@mui/icons-material/Remove";
import {useCart} from "../../providers/CartProvider";


export const ShoppingCartItem = ({product}) => {
    const {addToCart, removeFromCart, cartItems} = useCart();
    const cartItem = cartItems.find(item => item.id === product.id);
    const totalPriceItem = cartItem ? cartItem.quantity * product.price : 0;
    const amount = cartItem ? cartItem.quantity : 0;

    if (amount === 0) {
        return null;
    }

    return (
        <Card sx={{
            display: "flex",
            marginTop: 5,

        }}>
            <CardMedia sx={{
                maxWidth: 145,
                minWidth: 145,
                display: "flex",
                flexDirection: "column",
                objectFit: 'cover',
                margin: 'auto'
            }}
                       component="img"
                       height="140"
                       image={product.productImage}
            />
            <CardContent sx={{

                flex: "1 0 0 "

            }}>

                <Typography variant="div" component="h2">
                    {product.productName}
                </Typography>
                <Typography variant="subtitle2">
                    <hr/>
                </Typography>
                <Grid container>
                    <Grid item xs={10} sm={10} md={10} lg={10}>
                        <Typography variant="body1" component="div">
                            Quantity
                        </Typography>

                    </Grid>
                    <Grid item xs={2} sm={2} md={2} lg={2}>
                        <Typography variant="h6" component="div" sx={{textAlign: "center"}}>
                            {amount}
                        </Typography>

                    </Grid>

                    <Grid item xs={10} sm={10} md={10} lg={10}>
                        <Button size="small" onClick={() => addToCart(product.id)}>
                            <AddIcon/>
                        </Button>
                    </Grid>
                    <Grid item xs={2} sm={2} md={2} lg={2}>
                        <Button size="small" onClick={() => removeFromCart(product.id)}>
                            <RemoveIcon/>
                        </Button>

                    </Grid>

                    <Grid item xs={9} sm={8} md={9} lg={9}>
                        <Typography
                            variant="body1"
                            component="div"
                            style={{fontWeight: "bold"}}
                        >
                            Price
                        </Typography>
                    </Grid>
                    <Grid item xs={2} sm={2} md={2} lg={1}>
                        <Typography variant="h6" component="div" color="secondary">
                            {totalPriceItem}
                        </Typography>
                    </Grid>
                </Grid>
            </CardContent>
        </Card>
    );
}
