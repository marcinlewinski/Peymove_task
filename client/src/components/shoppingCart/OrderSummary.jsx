import React, {useState} from "react";
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from "@mui/material/Typography";
import Grid from "@mui/material/Grid";
import Button from "@mui/material/Button";
import CardActions from "@mui/material/CardActions";
import Decimal from "decimal.js";
import {useAuth} from "../../providers/AuthProvider";
import {httpClient} from "../../hooks/httpClient";
import {useCart} from "../../providers/CartProvider";
import {useNavigate} from "react-router-dom";
import Snackbar from '@mui/material/Snackbar';
import MuiAlert from '@mui/material/Alert';

const Alert = React.forwardRef(function Alert(props, ref) {
    return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

export const OrderSummary = ({productOrder, totalPrice}) => {
    const total = new Decimal(totalPrice).toDecimalPlaces(2).toString();
    const {user} = useAuth();
    const {cleanCart} = useCart();
    const [snackbarOpen, setSnackbarOpen] = useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState("");
    const [snackbarSeverity, setSnackbarSeverity] = useState("success");

    const handleCreateNewOrder = async () => {
        try {
            if (!user) {
                console.error('User email not available');
                return;
            }

            const formattedOrder = productOrder.map(item => ({
                productId: item.id,
                quantity: item.quantity
            }));
            const response = await httpClient.post("/orders/create", formattedOrder, {
                params: {
                    userEmail: user.email
                }
            });

            if (response.status === 200) {
                cleanCart();
                setSnackbarMessage("Order created successfully");
                setSnackbarSeverity("success");
                setSnackbarOpen(true);

            } else {
                setSnackbarMessage("Order creation failed");
                setSnackbarSeverity("error");
                setSnackbarOpen(true);
            }

        } catch (error) {
            console.error('The error ', error);
            setSnackbarMessage("An error occurred");
            setSnackbarSeverity("error");
            setSnackbarOpen(true);
        }
    };

    const handleCloseSnackbar = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }
        setSnackbarOpen(false);
    };

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
                        onClick={handleCreateNewOrder}>
                    BUY NOW
                </Button>
            </CardActions>

            <Snackbar open={snackbarOpen} autoHideDuration={6000} onClose={handleCloseSnackbar}>
                <Alert onClose={handleCloseSnackbar} severity={snackbarSeverity}>
                    {snackbarMessage}
                </Alert>
            </Snackbar>
        </Card>
    );
}
