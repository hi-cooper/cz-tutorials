import type { IHttpApiResponse } from "./types";

const handleGlobalHttpStatusError = (status: number): void => {
  let message = "未知错误";
  if (status) {
    switch (status) {
      case 400:
        message = "错误的请求";
        break;
      case 401:
        message = "未授权，请重新登录";
        break;
      case 403:
        message = "拒绝访问";
        break;
      case 404:
        message = "请求错误,未找到该资源";
        break;
      case 405:
        message = "请求方法未允许";
        break;
      case 408:
        message = "请求超时";
        break;
      case 500:
        message = "服务器端出错";
        break;
      case 501:
        message = "网络未实现";
        break;
      case 502:
        message = "网络错误";
        break;
      case 503:
        message = "服务不可用";
        break;
      case 504:
        message = "网络超时";
        break;
      case 505:
        message = "http版本不支持该请求";
        break;
      default:
        message = `其他错误 --${status}`;
    }
  } else {
    message = `无法连接到服务器！`;
  }

  console.log(message);
};

const handleGlobalBizError = (resp: IHttpApiResponse<any>): void => {
  switch (resp.code) {
    case 200:
      break;
    case 30000:
      console.log(`Business Error: ${resp.message}`);
      break;
    default:
  }
};

export { handleGlobalHttpStatusError, handleGlobalBizError };

export default {};
