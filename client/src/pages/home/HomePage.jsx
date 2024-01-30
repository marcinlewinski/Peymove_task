import * as React from "react";
import Box from "@mui/material/Box";
import {httpClient} from "../../hooks/httpClient";
import CssBaseline from "@mui/material/CssBaseline";

export const HomePage = () => {
    // httpClient.get("/user").then((res) => {
    //     console.log(res);
    // })
    return (
        <>
            <React.Fragment>
                <CssBaseline/>
                <Box>Hi from home</Box>
            </React.Fragment>
        </>

    )
}