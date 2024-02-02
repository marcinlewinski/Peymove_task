import React from "react";
import CssBaseline from "@mui/material/CssBaseline";
import {AdminUsers} from "../../components/adminComponents/AdminUsers";
import {AdminOrders} from "../../components/adminComponents/AdminOrders";

export const AdminOrdersPage = () => {
  return(
      <React.Fragment>
          <CssBaseline/>
          <AdminOrders></AdminOrders>
      </React.Fragment>
  )
}