import * as React from 'react';
import {useEffect, useState} from 'react';
import {styled} from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, {tableCellClasses} from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import {httpClient} from "../../hooks/httpClient";
import {useAuth} from "../../providers/AuthProvider";

const StyledTableCell = styled(TableCell)(({theme}) => ({
    [`&.${tableCellClasses.head}`]: {
        backgroundColor: theme.palette.common.black,
        color: theme.palette.common.white,
    },
    [`&.${tableCellClasses.body}`]: {
        fontSize: 14,
    },
}));

const StyledTableRow = styled(TableRow)(({theme}) => ({
    '&:nth-of-type(odd)': {
        backgroundColor: theme.palette.action.hover,
    },
    '&:last-child td, &:last-child th': {
        border: 0,
    },
}));


export const AdminOrders = () => {
    const [userData, setUserData] = useState();
    const {isAuthenticated} = useAuth();

    useEffect(() => {
        const fetchData = async () => {
            try {
                if (isAuthenticated) {
                    const response = await httpClient.get("/orders/all");
                    const data = await response.data;
                    setUserData(data);
                }
            } catch (error) {
                console.error("Error fetching product data:", error);
            }
        };
        fetchData();
    }, [isAuthenticated]);

    return (
        <TableContainer component={Paper} sx={{display: 'flex', justifyContent: 'center'}}>
            <Table sx={{maxWidth: 'auto'}} aria-label="customized table">
                <TableHead>
                    <TableRow>
                        <StyledTableCell align="center">Nr.</StyledTableCell>
                        <StyledTableCell align="center">Order id</StyledTableCell>
                        <StyledTableCell align="center">Product Name</StyledTableCell>
                        <StyledTableCell align="center">Price</StyledTableCell>
                        <StyledTableCell align="center">Quantity</StyledTableCell>
                        <StyledTableCell align="center">User email</StyledTableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {userData &&
                        userData.map((row, index) => (
                            <React.Fragment key={row.orderId}>
                                {row.orderItems.map((item, itemIndex) => (
                                    <StyledTableRow key={item.product.id}>
                                        {(itemIndex === 0) && <StyledTableCell align="center"
                                                                               rowSpan={row.orderItems.length}>{index + 1}</StyledTableCell>}
                                        {(itemIndex === 0) &&
                                            <StyledTableCell align="left" rowSpan={row.orderItems.length} component="th"
                                                             scope="row">{row.orderId}</StyledTableCell>}
                                        <StyledTableCell align="left">{item.product.productName}</StyledTableCell>
                                        <StyledTableCell align="center">{item.product.price}</StyledTableCell>
                                        <StyledTableCell align="center">{item.quantity}</StyledTableCell>
                                        {(itemIndex === 0) && <StyledTableCell align="left"
                                                                               rowSpan={row.orderItems.length}>{row.userEmail}</StyledTableCell>}
                                    </StyledTableRow>
                                ))}
                            </React.Fragment>
                        ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
};