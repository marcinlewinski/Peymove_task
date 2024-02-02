import React from "react";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import {useAuth} from "./providers/AuthProvider";
import {ProductProvider} from "./providers/ProductProvider";
import {CartProvider} from "./providers/CartProvider";
import {generateRoutes} from "./generateRoutes";
import {protectedRoutes, publicRoutes} from "./routesConfig";
import {LoginPage} from "./pages/loginPage/LoginPage";
import {RegisterPage} from "./pages/registerPage/RegisterPage";
import {Navbar} from "./components/navbar/Navbar";


const App = () => {
    const { isAuthenticated, user } = useAuth();

    return (
        <Router>
            {isAuthenticated ? <Navbar></Navbar> : null}
            <CartProvider>
                <ProductProvider>
                    <Routes>
                        {generateRoutes(publicRoutes, isAuthenticated, user)}
                        {generateRoutes(protectedRoutes, isAuthenticated, user)}
                        <Route path="/login" element={<LoginPage />} />
                        <Route path="/register" element={<RegisterPage />} />
                    </Routes>
                </ProductProvider>
            </CartProvider>
        </Router>
    );
};

export default App;
