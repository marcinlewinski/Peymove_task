import * as React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import AddIcon from '@mui/icons-material/Add';
import RemoveIcon from '@mui/icons-material/Remove';

export const Product = ({product}) => {
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
                image={product.productImage}
                sx={{objectFit: 'cover'}}
            />
            <CardContent>
                <Typography gutterBottom variant="h5" component="div">
                    {product.productName}
                </Typography>
            </CardContent>
            <CardActions sx={{marginTop: 'auto'}}>
                <Button size="small" onClick={() => {/* Handle add to cart */
                }}>
                    <AddIcon/>
                </Button>
                <Typography gutterBottom variant="h5" component="div">
                    0
                </Typography>

                <Button size="small" onClick={() => {/* Handle remove from cart */
                }}>
                    <RemoveIcon/>
                </Button>
            </CardActions>
        </Card>
    );
}
