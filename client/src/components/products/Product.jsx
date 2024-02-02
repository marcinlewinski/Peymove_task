import * as React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import AddIcon from '@mui/icons-material/Add';
import RemoveIcon from '@mui/icons-material/Remove';
import {useCart} from "../../providers/CartProvider";
import {calculateCartItemData} from "../utils/calculateCartItemData";

export const Product = ({product}) => {
    const { addToCart, removeFromCart, cartItems } = useCart();
    const cartItem = cartItems.find(item => item.id === product.id);
    const { totalPrice, amount } = calculateCartItemData(cartItem, product);


    return (
        <Card sx={{
            maxWidth: 345,
            minWidth: 345,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            justifyContent: 'center',
        }}>
            <CardMedia
                component="img"
                alt={product.productName}
                height="140"
                image={product.image}
                sx={{objectFit: 'cover'}}
            />
            <CardContent>
                <Typography gutterBottom variant="h5" component="div">
                    {product.productName}
                </Typography>
                <Typography sx={{textAlign: 'center', fontWeight: 'bold'}}>
                    {totalPrice}$
                </Typography>
            </CardContent>
            <CardActions sx={{marginTop: 'auto'}}>
                <Button size="small" onClick={() => addToCart(product.id)}>
                    <AddIcon/>
                </Button>
                <Typography gutterBottom variant="h5" component="div">
                    {amount}
                </Typography>
                <Button size="small" onClick={() => removeFromCart(product.id)}>
                    <RemoveIcon/>
                </Button>
            </CardActions>
        </Card>
    );
}
