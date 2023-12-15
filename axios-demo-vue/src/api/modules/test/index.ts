import HttpHelper from "@/api/HttpHelper";
import type { IHttpApiError, IHttpApiResponse } from "@/api/types";
import type { CreateRequestV1, CreateResponseV1 } from "./types";

const Api = {
  v1: {
    get200: `/v1/test/get200`,
    get200BizError: "/v1/test/get200BizError",
    get2xx: "/v1/test/get2xx",
    get5xx: "/v1/test/get5xx",
    create: "/v1/test/create",
    upload: "/v1/test/upload",
    getDownload: "/v1/download/getDownload",
    getImage: "/v1/download/getImage",
    getDownloadBizError: "/v1/download/getDownloadBizError",
    postDownload: "/v1/download/postDownload",
  },
};

const test = {
  get200: function (): Promise<IHttpApiResponse<string>> {
    return HttpHelper.get(Api.v1.get200);
  },

  get200BizError: function (isThrow: boolean = false): Promise<IHttpApiResponse<string>> {
    return HttpHelper.get(Api.v1.get200BizError, { isThrow });
  },

  get2xx: function (isThrow: boolean = false): Promise<IHttpApiResponse<string>> {
    return HttpHelper.get(Api.v1.get2xx, { isThrow });
  },

  get5xx: function (isThrow: boolean = false): Promise<IHttpApiResponse<string>> {
    return HttpHelper.get(Api.v1.get5xx, { isThrow });
  },

  create: function (params: CreateRequestV1): Promise<IHttpApiResponse<CreateResponseV1>> {
    return HttpHelper.postJson(Api.v1.create, params);
  },

  upload: function (): Promise<IHttpApiResponse<Array<string>>> {
    return HttpHelper.uploadFiles(Api.v1.upload, {
      testId: 8001,
      files: (document.querySelector("#files") as HTMLInputElement).files,
    });
  },

  getDownload: function (): Promise<void> {
    return HttpHelper.getDownload(Api.v1.getDownload, { filename: "1.jpg" });
  },

  getJpgImage: function (): Promise<void> {
    return HttpHelper.getDownload(Api.v1.getImage + "?type=jpg");
  },

  getPngImage: function (): Promise<void> {
    return HttpHelper.getDownload(Api.v1.getImage + "?type=png");
  },

  getDownloadBizError: function (): Promise<void> {
    return HttpHelper.getDownload(Api.v1.getDownloadBizError, { isThrow: true });
  },

  postDownload: function (): Promise<void> {
    return HttpHelper.postDownload(
      Api.v1.postDownload,
      {
        fileName: "中文.jpg",
      },
      {
        filename: "自定义名称.jpg",
      }
    );
  },
};

export default test;
