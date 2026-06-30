import React from 'react';

import {

    Grid
 
} from '@mui/material';
import CamelHeader from './camelHeader';
import CamelAudit from './CamelAudit';

const CamelHome = () => {

    return (
        <Grid>
            <CamelHeader />

            <CamelAudit />

        </Grid>
    );
};

export default CamelHome;