import * as React from "react";
import {Product} from "./Product";
import {MockProducts} from "./MockProducts";
import Box from "@mui/material/Box";
import { useTheme } from '@mui/material/styles';
import CssBaseline from "@mui/material/CssBaseline";

export const Products = () => {
    const theme = useTheme();
    // download products from db and map by single product component
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
            {MockProducts().map((product) => (
                <Product key={product.id} product={product}/>
            ))}
        </Box>
        </React.Fragment>
    );
}