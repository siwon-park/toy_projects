import { API_BASE_URL } from "../app-config";

export function call(api, method, request) {
  const options = {
    headers: new Headers({
      "Content-Type": "application/json",
    }),
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
    console.log("response: ", res)
    alert("로그인 토큰: " + res.token)
  })
}