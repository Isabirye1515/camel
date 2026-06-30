import React, { useEffect, useState } from 'react';
import { getFromCamel } from './Utils';
import {
    Box,
    Button,
    Card,
    CardContent,
    Paper,
    Pagination,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    TextField,
    Typography
} from '@mui/material';

const CamelAudit = () => {

    const [page, setPage] = useState(1);
    const [searchQuery, setSearchQuery] = useState("");
    const [searchText, setSearchText] = useState("");

    const [rows, setRows] = useState([]);
    const [totalPages, setTotalPages] = useState(0);
    const [totalItems, setTotalItems] = useState(0);

    useEffect(() => {

        let endpoint = `trails?page=${page}`;

        if (searchQuery.trim() !== "") {
            endpoint += `&tableName=${encodeURIComponent(searchQuery)}`;
        }

        getFromCamel(endpoint, (data) => {
            setRows(data.items);
            setTotalPages(data.totalPages);
            setTotalItems(data.totalItems);
        });

    }, [page, searchQuery]);

    const handleSearch = () => {
        setPage(1);
        setSearchQuery(searchText.trim());
    };

    const clearSearch = () => {
        setSearchText("");
        setSearchQuery("");
        setPage(1);
    };

    return (
        <div>

            <Card sx={{ mb: 2 }}>
                <CardContent>

                    <Typography variant="h5">
                        Camel Audit Trail
                    </Typography>

                    <Typography variant="body2" sx={{ mb: 2 }}>
                        Total Records: {totalItems}
                    </Typography>

                    <Box
                        sx={{
                            display: "flex",
                            gap: 2,
                            alignItems: "center"
                        }}
                    >
                        <TextField
                            label="Search by Table Name"
                            value={searchText}
                            size="small"
                            onChange={(e) => setSearchText(e.target.value)}
                            onKeyDown={(e) => {
                                if (e.key === "Enter") {
                                    handleSearch();
                                }
                            }}
                        />

                        <Button
                            variant="contained"
                            onClick={handleSearch}
                        >
                            Search
                        </Button>

                        <Button
                            variant="outlined"
                            onClick={clearSearch}
                        >
                            Clear
                        </Button>
                    </Box>

                </CardContent>
            </Card>

            <TableContainer component={Paper}>
                <Table>

                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Action Performed</TableCell>
                            <TableCell>Table Name</TableCell>
                            <TableCell>Create Date</TableCell>
                            <TableCell>Last Updated</TableCell>
                        </TableRow>
                    </TableHead>

                    <TableBody>

                        {rows.map((row) => (
                            <TableRow key={row.id} hover>

                                <TableCell>{row.id}</TableCell>
                                <TableCell>{row.actionPerformed}</TableCell>
                                <TableCell>{row.tableName}</TableCell>

                                <TableCell>
                                    {new Date(row.createDate).toLocaleString()}
                                </TableCell>

                                <TableCell>
                                    {new Date(row.lastUpdated).toLocaleString()}
                                </TableCell>

                            </TableRow>
                        ))}

                    </TableBody>

                </Table>
            </TableContainer>

            <Box
                sx={{
                    display: "flex",
                    justifyContent: "center",
                    mt: 3
                }}
            >
                <Pagination
                    page={page}
                    count={totalPages}
                    color="primary"
                    onChange={(event, value) => setPage(value)}
                />
            </Box>

        </div>
    );
};

export default CamelAudit;