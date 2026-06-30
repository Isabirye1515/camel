import React from "react";
import { Routes, Route } from "react-router-dom";
import Login from "./components/login";
import ProtectedRoute from "./components/protectedRoute";
import CamelHome from "./components/cameHome";

const App = () => {
    return (
        <Routes>
            <Route path="/" element={<CamelHome />} />

            <Route element={<ProtectedRoute />}>
                <Route path="/product" element={<Login />} />
            </Route>
        </Routes>
    );
};

export default App;