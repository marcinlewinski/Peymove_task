import ErrorPage from "./pages/errorPage/ErrorPage";
import {HomePage} from "./pages/home/HomePage";
import {ProductsPage} from "./pages/productsPage/ProductsPage";
import {CartPage} from "./pages/cartPage/CartPage";
import {AdminOrdersPage} from "./pages/adminOrdersPage/AdminOrdersPage";
import {AdminUsersPage} from "./pages/adminUsersPage/AdminUsersPage";



export const publicRoutes = [
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
        element: <ProductsPage/>,
        errorElement: <ErrorPage/>,
    },
    {
        path: "/cart",
        element: <CartPage/>,
        errorElement: <ErrorPage/>,
    },
];
export const protectedRoutes = [
    {
        path: "/orders",
        element: <AdminOrdersPage/>,
        errorElement: <ErrorPage/>,
    },
    {
        path: "/users",
        element: <AdminUsersPage/>,
        errorElement: <ErrorPage/>,
    },

];