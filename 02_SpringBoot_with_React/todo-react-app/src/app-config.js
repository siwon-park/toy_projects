// 백엔드 호스팅
let backendHost

const hostname = window && window.location && window.location.hostname

if (hostname === "localhost") {
  backendHost = "http://localhost:8080"
}

export const API_BASE_URL = `${backendHost}` // API_BASE_URL이라는 이름으로 내보냄(변수로서 사용 가능)