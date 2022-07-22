import { Button, Container, Grid, Link, TextField, Typography } from "@mui/material";
import React from "react";
import { signin } from "../../service/ApiService";

const Login = (props) => {
  const submitHandler = (event) => {
    event.preventDefault()
    const data = new FormData(event.target)
    const email = data.get("email")
    const password = data.get("password")
    signin({email: email, password: password})
  }

  return (
    <Container component="main" maxWidth="xs" style={{marginTop: "8%"}}>
      <Grid container spacing={2}>
        <Grid item xs={12}>
          <Typography component="h1" variant="h5">
            로그인
          </Typography>
        </Grid>
      </Grid>
      <form noValidate onSubmit={submitHandler}>
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <TextField
              variant="outlined"
              required
              fullWidth
              id="email"
              label="이메일"
              name="email"
              autoComplete="email"
            >
            </TextField>
          </Grid>
          <Grid item xs={12}>
            <TextField
              variant="outlined"
              required
              fullWidth
              id="password"
              type="password"
              name="password"
              label="비밀번호"
              autoComplete="current-password"
            >
            </TextField>
          </Grid>
          <Grid item xs={12}>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              color="primary"
            >
              로그인
            </Button>
          </Grid>
          <Grid item xs={12}>
            <Grid container justify="flex-end">
              <Grid item>
                <Link href="/signup" variant="body2">
                  계정이 없습니까? 여기서 가입하세요
                </Link>
              </Grid>
            </Grid>
          </Grid>
        </Grid>
      </form>
    </Container>
  )
}

export default Login;