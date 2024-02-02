import {createContext, useContext, useEffect, useState} from "react";
import {httpClient} from "../hooks/httpClient";

const AuthContext = createContext();

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error('useAuth must be used within an AuthProvider');
    }
    return context;
};
export const AuthProvider = ({children}) => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);

    useEffect(() => {
        const storedToken = localStorage.getItem("jwt");
        if (storedToken) {
            httpClient.defaults.headers.common["Authorization"] = `Bearer ${storedToken}`;
            setIsAuthenticated(true);
        }
    }, []);
    const login = async (userToken) => {
        sessionStorage.setItem('jwt', userToken);
        httpClient.defaults.headers.common[
            "Authorization"
            ] = `Bearer ${userToken}`;

        setIsAuthenticated(true);
    };


    const logout = () => {
        delete httpClient.defaults.headers.common["Authorization"];
        sessionStorage.removeItem('jwt');
        setIsAuthenticated(false);
    };
    return (
        <AuthContext.Provider value={{isAuthenticated, logout, login}}>
            {children}
        </AuthContext.Provider>
    );
}