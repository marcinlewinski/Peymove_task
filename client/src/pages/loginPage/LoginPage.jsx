import SignIn from "../../components/login/SignIn";
import React from "react";
import CssBaseline from "@mui/material/CssBaseline";

export const LoginPage = () => {
    return (
        <React.Fragment>
            <CssBaseline/>
            <SignIn></SignIn>
        </React.Fragment>
    )
}