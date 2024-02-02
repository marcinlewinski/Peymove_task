import * as React from 'react';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';

const ErrorPage = ({ error }) => {
    return (
        <Container id="error-page" sx={{ textAlign: 'center', mt: 4 }}>
            <Typography variant="h1" component="h1">
                Oops!
            </Typography>
            <Typography variant="body1" paragraph>
                Sorry, an unexpected error has occurred.
            </Typography>
            <Typography variant="body1" paragraph>
                <i>{error.statusText || error.message}</i>
            </Typography>
        </Container>
    );
};

export default ErrorPage;
