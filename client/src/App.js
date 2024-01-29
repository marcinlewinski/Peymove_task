import React from "react";
import {BrowserRouter as Router, Navigate, Route, Routes} from "react-router-dom";
import ErrorPage from "./pages/errorPage/ErrorPage";
import {HomePage} from "./pages/home/HomePage";
import {useAuth} from "./providers/AuthProvider";
import {LoginPage} from "./pages/loginPage/LoginPage";
import {RegisterPage} from "./pages/registerPage/RegisterPage";
import Navbar from "./components/navbar/Navbar";
import {CartPage} from "./pages/cartPage/CartPage";
import {ProductsPage} from "./pages/productsPage/ProductsPage";

const routerConfig = [
    {
        path: "/",
        element: <HomePage/>,
        errorElement: <ErrorPage/>,
    },
    {
        path: "/home",
        element: <HomePage/>,
        errorElement: <ErrorPage/>,
    },
    {
        path: "/products",
        element: <ProductsPage />,
        errorElement: <ErrorPage />,
    },
    {
        path: "/cart",
        element: <CartPage/>,
        errorElement: <ErrorPage/>,
    },
];

const App = () => {
    const {isAuthenticated} = useAuth();


    return (
        <Router>
            {isAuthenticated ? <Navbar/> : null}
            <Routes>
                {routerConfig.map((route, index) => (
                    <Route
                        key={index}
                        path={route.path}
                        element={isAuthenticated ? route.element : <Navigate to="/login"/>}
                    />
                ))}

                <Route path="/login" element={<LoginPage/>}/>
                <Route path="/register" element={<RegisterPage/>}/>
            </Routes>
        </Router>
    );
};

export default App;
