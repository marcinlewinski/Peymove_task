import React from "react";
import { Navigate, Route } from "react-router-dom";
import { useAuth } from "../../providers/AuthProvider";

const ProtectedRoute = ({ element, requiredRole, ...rest }) => {
    const { isAuthenticated, user } = useAuth();

    const hasRequiredRole = user && user.authorities === requiredRole;

    return isAuthenticated && hasRequiredRole ? (
        <Route {...rest} element={element} />
    ) : (
        <Navigate to="/login" replace />
    );
};

export default ProtectedRoute;
