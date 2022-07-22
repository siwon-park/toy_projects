import { AppBar, Button, Grid, Toolbar, Typography } from "@mui/material";
import React from "react";
import { signout } from "../../service/ApiService";

const Navbar = () => {
  return (
    <AppBar position="static">
      <Toolbar>
        <Grid justify="space-between" container>
          <Grid item>
            <Typography variant="h6">
              오늘의 Todo List
            </Typography>
          </Grid>
          <Grid>
            <Button color="inherit" onClick={signout}>
              로그아웃
            </Button>
          </Grid>
        </Grid>
      </Toolbar>
    </AppBar>
  )
}

export default Navbar;
