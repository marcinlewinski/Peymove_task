import React from "react";
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from "@mui/material/Typography";
import Grid from "@mui/material/Grid";
import Button from "@mui/material/Button";
import CardActions from "@mui/material/CardActions";
import Decimal from "decimal.js";

export const OrderSummary = ({productOrder, totalPrice}) => {
    const total = new Decimal(totalPrice).toDecimalPlaces(2).toString();

    return (
        <Card elevation={15} sx={{
            position: "sticky",
            top: "1rem",
            minWidth: "275",
            marginTop: "10px"
        }}>
            <CardContent>
                <Typography
                    color="textSecondary"
                    gutterBottom
                    sx={{
                        fontSize: 14
                    }}
                >
                    Shopping Cart
                </Typography>
                <Typography variant="div" component="h1">
                    {" "}
                    Order Summary
                </Typography>
                <Typography variant="subtitle2">
                    <hr/>
                </Typography>
                <Grid container>
                    <Grid item xs={10} sm={9} md={9} lg={9}>
                        <Typography variant="body1" component="div">
                            Total
                        </Typography>
                    </Grid>
                    <Grid item xs={1} sm={1} md={1} lg={1}>
                        <Typography variant="h6" component="div">
                            ${total}
                        </Typography>
                    </Grid>
                </Grid>
            </CardContent>

            <CardActions>
                <Button disabled={!productOrder?.length > 0} size="large" color="secondary"
                        onClick={() => console.log(productOrder)}>
                    BUY NOW
                </Button>
            </CardActions>
        </Card>
    );
}
