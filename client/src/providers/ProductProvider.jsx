import React, {createContext, useContext, useEffect, useState} from "react";
import {httpClient} from "../hooks/httpClient";
import {useAuth} from "./AuthProvider"; // Import your AuthProvider

const ProductContext = createContext();

export const useProduct = () => {
    const context = useContext(ProductContext);
    if (!context) {
        throw new Error('useProduct must be used within a ProductProvider');
    }
    return context;
};

export const ProductProvider = ({children}) => {
    const {isAuthenticated} = useAuth(); // Access isAuthenticated from AuthProvider
    const [productList, setProductList] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const fetchData = async () => {
            try {
                if (isAuthenticated) {
                    const response = await httpClient.get("/product/get");
                    const data = await response.data;
                    setProductList(data);
                }
            } catch (error) {
                console.error("Error fetching product data:", error);
            } finally {
                setIsLoading(false);
            }
        };
        fetchData();
    }, [isAuthenticated]);


    return (
        <ProductContext.Provider value={{productList, isLoading}}>
            {children}
        </ProductContext.Provider>
    );
};
