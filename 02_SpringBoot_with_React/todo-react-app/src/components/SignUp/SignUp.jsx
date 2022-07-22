import React from "react";
import { Button, Container, Grid, Link, TextField, Typography } from "@mui/material";
import { signup } from "../../service/ApiService";

const SignUp = (props) => {
  
  const submitHandler = (event) => {
    event.preventDefault()
    const data = new FormData(event.target)
    const username = data.get("username")
    const email = data.get("email")
    const password = data.get("password")
    const newUser = {email: email, username: username, password: password}
    signup(newUser).then((res) => {
      alert("성공적으로 회원가입되었습니다")
      window.location.href = "/login"
    })
  }
  
  return (
    <Container component="main" maxWidth="xs" style={{marginTop: "8%"}}>
      <form noValidate onSubmit={submitHandler}>
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <Typography component="h1" variant="h5">
              계정 생성
            </Typography>
          </Grid>
          <Grid item xs={12}>
            <TextField
              autoComplete="fname"
              name="username"
              variant="outlined"
              required
              fullWidth
              id="username"
              label="사용자 이름"
              autoFocus
            >
            </TextField>
          </Grid>
          <Grid item xs={12}>
            <TextField
              autoComplete="email"
              name="email"
              variant="outlined"
              required
              fullWidth
              id="email"
              label="이메일 주소"
            >
            </TextField>
          </Grid>
          <Grid item xs={12}>
            <TextField
              autoComplete="current-password"
              name="password"
              variant="outlined"
              required
              fullWidth
              id="password"
              label="패스워드"
              type="password"
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
              계정 생성
            </Button>
          </Grid>
          <Grid item xs={12}>
            <Grid container justify="flex-end">
              <Grid item>
                <Link href="/login" variant="body2">
                  이미 계정이 있습니까? 로그인하세요
                </Link>
              </Grid>
            </Grid>
          </Grid>
        </Grid>
      </form>
    </Container>
  )
}

export default SignUp;