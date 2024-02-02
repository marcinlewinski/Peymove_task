import {createContext, useContext, useEffect, useState} from "react";
import {httpClient} from "../hooks/httpClient";
import {jwtDecode as jwt_decode} from "jwt-decode";

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
    const [user, setUser] = useState(null);

    useEffect(() => {
        const storedToken = localStorage.getItem("jwt");
        if (storedToken) {
            httpClient.defaults.headers.common["Authorization"] = `Bearer ${storedToken}`;
            const decodedUser = jwt_decode(storedToken);
            setUser(decodedUser);
            setIsAuthenticated(true);
        }
    }, []);
    const login = async (userToken) => {
        sessionStorage.setItem('jwt', userToken);
        httpClient.defaults.headers.common[
            "Authorization"
            ] = `Bearer ${userToken}`;
        const decodedUser = jwt_decode(userToken);
        setUser(decodedUser);
        setIsAuthenticated(true);
    };


    const logout = () => {
        delete httpClient.defaults.headers.common["Authorization"];
        sessionStorage.removeItem('jwt');
        setUser(null);
        setIsAuthenticated(false);
    };
    return (
        <AuthContext.Provider value={{isAuthenticated, user, logout, login}}>
            {children}
        </AuthContext.Provider>
    );
}