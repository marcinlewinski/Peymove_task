import React, {useEffect} from "react";
import {BrowserRouter as Router, Navigate, Route, Routes} from "react-router-dom";
import ErrorPage from "./pages/errorPage/ErrorPage";
import SignIn from "./components/login/SignIn";
import {HomePage} from "./pages/home/HomePage";
import {useAuth} from "./providers/AuthProvider";

const routerConfig = [
    {
        path: "/",
        element: <HomePage/>,
        errorElement: <ErrorPage/>,
    },
    // {
    //     path: "/wall",
    //     element: <WallPage />,
    //     errorElement: <ErrorPage />,
    // },
    // {
    //     path: "/card",
    //     element: <CardPage />,
    //     errorElement: <ErrorPage />,
    // },
];

const App = () => {
    const { isAuthenticated } = useAuth();


    return (
        <Router>
            <Routes>
                {routerConfig.map((route, index) => (
                    <Route
                        key={index}
                        path={route.path}
                        element={isAuthenticated ? route.element : <Navigate to="/login"/>}
                    />
                ))}
                <Route path="/login" element={<SignIn/>}/>
                {/*<Route path="/register" element={<SignUp />} />*/}
            </Routes>
        </Router>
    );
};

export default App;
