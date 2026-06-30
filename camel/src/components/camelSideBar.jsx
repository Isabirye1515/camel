import { SideNav, SideNavItem } from '@carbon/react';
import { Typography } from '@mui/material';
import React from 'react';

const CamelSideBar = () => {
    return (
        <>
        <SideNav  style={{border:'solid 1px blue',backgroundColor:'#0e067a',
            position: 'absolute', width:400,height:'100%'}} >
            <SideNavItem>
                <Typography variant='div' sx={{border:'1px solid blue', borderRadius:'5px'}} >Trails</Typography>
            </SideNavItem>
        </SideNav>
            
        </>
    );
}

export default CamelSideBar;
