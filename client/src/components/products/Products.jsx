import * as React from "react";
import {Product} from "./Product";
import Box from "@mui/material/Box";
import {useTheme} from '@mui/material/styles';
import CssBaseline from "@mui/material/CssBaseline";
import {useProduct} from "../../providers/ProductProvider";

export const Products = () => {
    const theme = useTheme();
    const {productList} = useProduct();

    return (
        <React.Fragment>
            <CssBaseline/>
            <Box sx={{
                height: 'auto',
                width: '100%',
                display: 'grid',
                gridTemplateColumns: '1fr 1fr 1fr',
                placeItems: 'center',
                flexDirection: 'column',
                gap: '20px',
                marginTop: '20px',
                [theme.breakpoints.down('sm')]: {
                    gridTemplateColumns: '1fr',
                },
            }}>
                {productList?.map((product) => (
                    <Product key={product.id} product={product}/>
                ))}
            </Box>
        </React.Fragment>
    );
}