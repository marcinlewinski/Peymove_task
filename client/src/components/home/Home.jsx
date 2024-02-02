import {useAuth} from "../../providers/AuthProvider";
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";

export const Home = () => {
    const {user} = useAuth()

    return (
        <Paper elevation={3} sx={{margin: "2rem", padding: "20px", textAlign: "center"}}>
            <Typography variant='h5' gutterBottom>
                Welcome, {user.email}
            </Typography>
            <Typography variant='body1'>
                Thank you for using our app. Enjoy your experience!
            </Typography>
        </Paper>
    )
}