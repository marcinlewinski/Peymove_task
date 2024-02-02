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
    // hide last border
    '&:last-child td, &:last-child th': {
        border: 0,
    },
}));



export const AdminUsers = () => {
    const [userData, setUserData] = useState();
    const {isAuthenticated} = useAuth();

    useEffect(() => {
        const fetchData = async () => {
            try {
                if (isAuthenticated) {
                    const response = await httpClient.get("/user/all");
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
        <TableContainer component={Paper} sx={{display: 'flex', justifyContent: 'center',}}>
            <Table sx={{maxWidth: 'auto'}} aria-label="customized table">
                <TableHead>
                    <TableRow>
                        <StyledTableCell>Nr.</StyledTableCell>
                        <StyledTableCell>User name</StyledTableCell>
                        <StyledTableCell >Roles</StyledTableCell>
                        <StyledTableCell >User email</StyledTableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {userData && userData.map((row,index) => (
                        <StyledTableRow key={row.name}>
                            <StyledTableCell align="left">{index+1}</StyledTableCell>
                            <StyledTableCell align="left" component="th" scope="row">
                                {row.name}
                            </StyledTableCell>
                            <StyledTableCell align="left" >{row.roles}</StyledTableCell>
                            <StyledTableCell align="left" >{row.email}</StyledTableCell>
                        </StyledTableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
}