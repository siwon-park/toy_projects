import { API_BASE_URL } from "../app-config";
const ACCESS_TOKEN = "ACCESS_TOKEN"; // 엑세스 토큰 헤더

let headers = new Headers({
  "Content-Type": "application/json",
});

export function call(api, method, request) {

  const accessToken = localStorage.getItem("ACCESS_TOKEN")
  if (accessToken && accessToken !== null) {
    headers.append("Authorization", "Bearer " + accessToken)
  }

  let options = {
    headers: headers,
    url: API_BASE_URL + api,
    method: method,
  }

  if (request) {
    // GET
    options.body = JSON.stringify(request)
  }

  return fetch(options.url, options).then((res) => res.json().then((json) => {
    if (!res.ok) {
      return Promise.reject(json)
    }
    return json
  })).catch((error) => {
    console.log(error.status)
    if (error.status === 403) { // 403 forbidden이면
      window.location.href = "/login" // 로그인 페이지로 리다이렉트
    }
    return Promise.reject(error)
  })
}

export function signin(userDto) {
  return call("/auth/signin", "POST", userDto).then((res) => {
    // console.log("response: ", res)
    // alert("로그인 토큰: " + res.token)
    if (res.token) { // 토큰이 존재하면 메인 페이지로 이동함
      localStorage.setItem(ACCESS_TOKEN, res.token)
      window.location.href = "/"
    }
  })
}

export function signout() {
  localStorage.setItem(ACCESS_TOKEN, null)
  window.location.href = "/login"
}