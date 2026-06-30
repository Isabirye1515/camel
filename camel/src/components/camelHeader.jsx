import React, { useState } from 'react';
import {
    Header,
    HeaderMenuButton,
    HeaderName,
    SideNav,
    SideNavItems,
    SideNavItem,
    SideNavMenu,
    SideNavMenuItem,

} from '@carbon/react';
import { Button, Typography } from '@mui/material';
import {Menu} from '@mui/icons-material'
import { Link } from 'react-router-dom';

const CamelHeader = () => {

    const [sideNavOpen, setSideNavOpen] = useState(false);

    const handleSideNav = () => {
        setSideNavOpen(prev => !prev);
    };

    return (
        <>
            <Header aria-label="Camel">
                <Button  onClick={handleSideNav} >
                    <Menu />
                </Button>

                <HeaderName prefix="">
                    Camel
                </HeaderName>

            </Header>

            <SideNav
                aria-label="Side navigation"
                expanded={sideNavOpen}
                isFixedNav
            >
                <SideNavItems>
<SideNavItem  >
  <Button fullWidth sx={{alignItems:'flex-start'}} >Dash Board</Button>
   </SideNavItem>
<SideNavItem  >
    <SideNavMenu title='Personal Informational' >
        
        <SideNavMenuItem>
     <Button fullWidth sx={{alignItems:'flex-start'}} >Calculator</Button>
        </SideNavMenuItem>
        <SideNavMenuItem>
     <Button fullWidth sx={{alignItems:'flex-start'}} >products</Button>
        </SideNavMenuItem>
                <SideNavMenuItem>
     <Button fullWidth sx={{alignItems:'flex-start'}} >people</Button>
        </SideNavMenuItem>
                <SideNavMenuItem>
     <Button fullWidth sx={{alignItems:'flex-start'}} >Income</Button>
        </SideNavMenuItem>
                <SideNavMenuItem>
     <Button fullWidth sx={{alignItems:'flex-start'}} >Expense</Button>
        </SideNavMenuItem>
    </SideNavMenu>
   </SideNavItem>
   <SideNavItem  >
  <Button fullWidth sx={{alignContent:'flex-start'}} >Audit Trails</Button>
   </SideNavItem>
                </SideNavItems>
            </SideNav>
        </>
    );
};

export default CamelHeader;