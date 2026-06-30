import React, { useState } from 'react';
import { postToCamel } from './Utils';
import { TextField, Button, Card, CardContent, CardActions, Box, Typography, Alert } from '@mui/material';
import { useAuth } from '../AuthProvider';
import { useNavigate } from "react-router-dom";

const Login = () => {
    const [error,setError] = useState(null)

    const { user,setUser } = useAuth();
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;

        setUser(prev => ({
            ...prev,
            [name]: value
        }));
    };

const handleSubmit = (e) => {
    e.preventDefault();

    setError("");

    postToCamel(
        "admin/credentials",
        user,
        (data) => {
        if (data != null) {
            setUser(data);
            navigate("/");
        }},
        (err) => {
            setError(err.message);
        }
    );
};

    return (
        <Box
    sx={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        minHeight: "100vh",
        backgroundColor: "#f5f5f5" // optional
    }}
>
    <Card sx={{ width: 350, p: 2 }}>
        <CardContent>
            <Typography
                gutterBottom
                variant="h5"
                align="center"
            >
                Login to your account
            </Typography>
            {error && (
    <Alert severity="error" sx={{ mb: 2 }}>
        {error}
    </Alert>
)}

            <form onSubmit={handleSubmit}>
                <TextField
                    fullWidth
                    margin="normal"
                    name="userName"
                    value={user.userName}
                    onChange={handleChange}
                    placeholder="Username"
                    required
                />

                <TextField
                    fullWidth
                    margin="normal"
                    type="password"
                    name="password"
                    value={user.password}
                    onChange={handleChange}
                    placeholder="Password"
                    required
                />

                <CardActions sx={{ justifyContent: "center", mt: 2 }}>
                    <Button
                        type="submit"
                        variant="contained"
                        fullWidth
                    >
                        Login
                    </Button>
                </CardActions>
            </form>
        </CardContent>
    </Card>
</Box>
    );
};

export default Login;