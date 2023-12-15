import axios, { type AxiosRequestConfig, type AxiosResponse, type InternalAxiosRequestConfig, type ResponseType } from "axios";

import { handleGlobalBizError, handleGlobalHttpStatusError } from "./handler";
import type { IHttpApiResponse } from "./types";

const helper = {
  isDownload: function (response: AxiosResponse<IHttpApiResponse<any> | Blob>): boolean {
    return response.data instanceof Blob;
  },

  hasBizError: function (response: AxiosResponse<IHttpApiResponse<any> | Blob>): boolean {
    if (this.isDownload(response)) {
      return response.data.type === "application/json";
    }

    return (response.data as IHttpApiResponse<any>)?.code !== 200;
  },

  download: function (response: any, defaultFileName?: string): void {
    const url = window.URL.createObjectURL(response.data);
    const link = document.createElement("a");
    link.href = url;
    link.download = this.getFileName(response, defaultFileName);
    document.body.appendChild(link);
    link.click();

    document.body.removeChild(link);
    window.URL.revokeObjectURL(url);
  },

  getFileName: function (response: any, defaultFileName?: string) {
    if (defaultFileName) {
      return defaultFileName;
    }

    let fileName = "download";
    const contentDisposition = response.headers["content-disposition"];
    if (!contentDisposition) {
      return fileName;
    }

    const rfc5987Match = contentDisposition.match(/filename\*=(.*''.+)/); // Rfc5987
    if (rfc5987Match?.length === 2) {
      let tmp = rfc5987Match[1].split("''");
      if (tmp.length === 1) {
        fileName = decodeURI(tmp[0]);
      } else if (tmp.length === 2) {
        fileName = decodeURI(tmp[1]);
      }
    }

    if (!fileName) {
      let match = contentDisposition.match(/filename="(.+)"/); // other
      if (match?.length === 2) {
        fileName = decodeURI(match[1]);
      }
    }

    return fileName;
  },
};

const axiosInstance = axios.create({
  // baseURL: import.meta.env.BASE_URL, // 基础请求地址
  baseURL: "http://localhost:8080", // 基础请求地址
  timeout: 10000, // 请求超时设置
  withCredentials: false, // 跨域请求是否需要携带 cookie
});

axiosInstance.interceptors.request.use(
  function (config: InternalAxiosRequestConfig) {
    // config.headers.set({
    //   'Content-Type': 'application/json; charset=utf-8',
    // });

    // const token = localStorage.getItem('token');
    // if (token) {
    //   config.headers.set('Authorization', `Bearer ${token}`);
    // }
    return config;
  },
  function (error: any) {
    return Promise.reject(error);
  }
);

axiosInstance.interceptors.response.use(
  function (response: AxiosResponse<IHttpApiResponse<any> | Blob>) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    if (response.status !== 200) {
      return Promise.reject({
        isBizError: false,
        data: response,
      });
    }

    if (!helper.hasBizError(response)) {
      return response;
    }

    if (helper.isDownload(response)) {
      return new Promise((resolve, reject) => {
        let fileReader = new FileReader();
        fileReader.onload = function (e) {
          // BizError
          return reject({
            isBizError: true,
            data: fileReader.result,
          });
        };
        fileReader.readAsText(response.data as Blob);
      });
    }

    handleGlobalBizError(response.data as IHttpApiResponse<any>);
    return Promise.reject({
      isBizError: true,
      data: response.data,
    });
  },
  function (error) {
    // Any status codes that falls outside the range of 2xx cause this function to trigger
    handleGlobalHttpStatusError(error.status);
    return Promise.reject({
      isBizError: false,
      data: error.response ?? error,
    });
  }
);

const HttpHelper = {
  get: function <T>(
    url: string,
    options?: {
      config?: AxiosRequestConfig<any>; // request配置
      isThrow?: boolean; // 是否使用reject(error)外抛错误。默认为false
    }
  ): Promise<IHttpApiResponse<T>> {
    return new Promise((resolve, reject) => {
      axiosInstance
        .get(url, options?.config)
        .then(function (response) {
          resolve(response.data);
        })
        .catch(function (error) {
          if (options?.isThrow) {
            reject(error);
          }
        })
        .finally(function () {
          // always executed
        });
    });
  },

  post: function <T>(
    url: string,
    data?: any,
    options?: {
      config?: AxiosRequestConfig<any>; // request配置
      isThrow?: boolean; // 是否使用reject(error)外抛错误。默认为false
    }
  ): Promise<IHttpApiResponse<T>> {
    return new Promise((resolve, reject) => {
      axiosInstance
        .post(url, data, options?.config)
        .then(function (response) {
          resolve(response.data);
        })
        .catch(function (error) {
          if (options?.isThrow) {
            reject(error);
          }
        })
        .finally(function () {
          // always executed
        });
    });
  },

  put: function <T>(
    url: string,
    data?: any,
    options?: {
      config?: AxiosRequestConfig<any>; // request配置
      isThrow?: boolean; // 是否使用reject(error)外抛错误。默认为false
    }
  ): Promise<IHttpApiResponse<T>> {
    return new Promise((resolve, reject) => {
      axiosInstance
        .put(url, data, options?.config)
        .then(function (response) {
          resolve(response.data);
        })
        .catch(function (error) {
          if (options?.isThrow) {
            reject(error);
          }
        })
        .finally(function () {
          // always executed
        });
    });
  },

  delete: function <T>(
    url: string,
    options?: {
      config?: AxiosRequestConfig<any>; // request配置
      isThrow?: boolean; // 是否使用reject(error)外抛错误。默认为false
    }
  ): Promise<IHttpApiResponse<T>> {
    return new Promise((resolve, reject) => {
      axiosInstance
        .delete(url, options?.config)
        .then(function (response) {
          resolve(response.data);
        })
        .catch(function (error) {
          if (options?.isThrow) {
            reject(error);
          }
        })
        .finally(function () {
          // always executed
        });
    });
  },

  /**
   * post JSON数据
   *
   * @param url url
   * @param data JSON格式数据
   * @param isThrow  是否使用reject(error)外抛错误。默认为false
   * @returns
   */
  postJson: function <T>(url: string, data: {}, isThrow?: boolean): Promise<IHttpApiResponse<T>> {
    const config = {
      headers: {
        "Content-Type": "application/json;charset:utf-8;",
      },
    };

    return this.post(url, data, {
      config: config,
      isThrow: isThrow,
    });
  },

  /**
   * post HTML form作为JSON数据
   * @param url url
   * @param formId form表单的id
   * @param isThrow  是否使用reject(error)外抛错误。默认为false
   * @returns
   */
  postFormAsJson: function <T>(url: string, formId: string, isThrow?: boolean): Promise<IHttpApiResponse<T>> {
    const config = {
      headers: {
        "Content-Type": "application/json;charset:utf-8;",
      },
    };

    const data = document.querySelector("#" + formId);
    return this.post(url, data, {
      config: config,
      isThrow: isThrow,
    });
  },

  /**
   * 使用post 'Content-Type': 'application/x-www-form-urlencoded'
   *
   * @param url url
   * @param data JSON格式数据
   * @param isThrow  是否使用reject(error)外抛错误。默认为false
   * @returns
   */
  postUrlencoded: function <T>(url: string, data: {}, isThrow?: boolean): Promise<IHttpApiResponse<T>> {
    const config = {
      headers: {
        "Content-Type": "application/x-www-form-urlencoded;charset:utf-8;",
      },
    };
    return this.post(url, data, {
      config: config,
      isThrow: isThrow,
    });
  },

  /**
   * 使用post 'Content-Type': 'multipart/form-data'
   *
   * @param url url
   * @param data JSON格式数据。包含文件信息<br/>示例
   * @param isThrow  是否使用reject(error)外抛错误。默认为false
   * {
   *    userId: 1,
   *    avatars: document.querySelector('#fileInput').files
   *  }
   *
   * input示例：
   * <input id="fileInput" type="file" name="avatars" multiple />
   * @returns
   */
  postMultipart: function <T>(url: string, data: {}, isThrow?: boolean): Promise<IHttpApiResponse<T>> {
    const config = {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    };

    return this.post(url, data, {
      config: config,
      isThrow: isThrow,
    });
  },

  /**
   * 上传多个文件（可包含其他数据字段）
   *
   * @param url url
   * @param data JSON格式数据。包含文件信息<br/>示例
   * @param isThrow  是否使用reject(error)外抛错误。默认为false
   * {
   *    userId: 1,
   *    avatars: document.querySelector('#fileInput').files
   *  }
   *
   * input示例：
   * <input id="fileInput" type="file" name="avatars" multiple />
   * @returns
   * @see HttpHelper.postMultipart()
   */
  uploadFiles: function <T>(url: string, data: {}, isThrow?: boolean): Promise<IHttpApiResponse<T>> {
    return this.postMultipart(url, data, isThrow);
  },

  /**
   * 使用GET请求下载
   *
   * @param url url
   * @param options 其他参数
   */
  getDownload: function (
    url: string,
    options?: {
      filename?: string; // 默认文件名
      isThrow?: boolean; // 是否使用reject(error)外抛错误。默认为false
    }
  ): Promise<void> {
    const config = {
      responseType: "blob" as ResponseType,
    };

    return new Promise((resolve, reject) => {
      axiosInstance
        .get(url, config)
        .then((response) => {
          helper.download(response, options?.filename);
          resolve();
        })
        .catch(function (error) {
          if (options?.isThrow) {
            reject(error);
          }
        });
    });
  },

  /**
   * 使用POST请求下载
   *
   * @param url url
   * @param data 请求参数
   * @param options 其他参数
   */
  postDownload: function (
    url: string,
    data: {},
    options?: {
      filename?: string; // 默认文件名
      isThrow?: boolean; // 是否使用reject(error)外抛错误。默认为false
    }
  ): Promise<void> {
    const config = {
      responseType: "blob" as ResponseType,
    };

    return new Promise((resolve, reject) => {
      axiosInstance
        .post(url, data, config)
        .then((response) => {
          helper.download(response, options?.filename);
          resolve();
        })
        .catch(function (error) {
          if (options?.isThrow) {
            reject(error);
          }
        });
    });
  },
};

export default HttpHelper;
