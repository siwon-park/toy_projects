import React from "react";
import "./index.css";
import App from "./App";
import Login from "./components/Login/Login";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import {Box} from "@mui/material";
import {Typography} from "@mui/material";
import SignUp from "./components/SignUp/SignUp";

const Copyright = () => {
  return (
    <Typography
    variant="body2"
    color="textSecondary"
    align="center"
    >
      {"Copyright ⓒ "}
      JouniorWebDeveloper, {new Date().getFullYear()}
      {"."}
    </Typography>
  )
}


const AppRouter = () => {
  return (
    <div>
      <Router>
        <div>
          <Routes>
            <Route path="/login" element={<Login/>}>
            </Route>
            <Route path="/signup" element={<SignUp/>}>
            </Route>
            <Route path="/" element={<App/>}>
            </Route>
          </Routes>
        </div>
        <Box mt={5}>
          <Copyright></Copyright>
        </Box>
      </Router>
    </div>
  )
}

export default AppRouter;