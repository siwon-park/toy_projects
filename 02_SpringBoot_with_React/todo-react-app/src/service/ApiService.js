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
  }))
}