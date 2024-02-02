import React from "react";
import {Navigate, Route} from "react-router-dom";

export const generateRoutes = (config, isAuthenticated, user) => {
    return config.map((route, index) => (
        <Route
            key={index}
            path={route.path}
            element={isAuthenticated && (!route.authCondition || route.authCondition(user)) ? route.element :
                <Navigate to="/login"/>}
        />
    ));
};
