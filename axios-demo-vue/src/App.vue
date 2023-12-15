<script setup lang="ts">
import HttpApi from "./api/HttpApi";
import type { CreateResponseV1 } from "./api/modules/test/types";
import type { IHttpApiResponse } from "./api/types";

function get200() {
  HttpApi.test.get200().then(function (response: IHttpApiResponse<string>) {
    console.log(response);
  });
}
function get200BizError() {
  HttpApi.test.get200BizError().then(function (response: IHttpApiResponse<string>) {
    console.log(response);
  });
}
function get200BizErrorThrow() {
  HttpApi.test
    .get200BizError(true)
    .then(function (response: IHttpApiResponse<string>) {
      console.log(response);
    })
    .catch((error: any) => {
      if (error.isBizError) {
        console.log("BizError", error.data);
      } else {
        console.log("SystemError", error.data);
      }
    });
}

function get2xx() {
  HttpApi.test.get2xx().then(function (response: IHttpApiResponse<string>) {
    console.log(response);
  });
}
function get2xxThrow() {
  HttpApi.test
    .get2xx(true)
    .then(function (response: IHttpApiResponse<string>) {
      console.log(response);
    })
    .catch((error: any) => {
      if (error.isBizError) {
        console.log("BizError", error.data);
      } else {
        console.log("SystemError", error.data);
      }
    });
}

function get5xx() {
  HttpApi.test.get5xx().then(function (response: IHttpApiResponse<string>) {
    console.log(response);
  });
}
function get5xxThrow() {
  HttpApi.test
    .get5xx(true)
    .then(function (response: IHttpApiResponse<string>) {
      console.log(response);
    })
    .catch((error: any) => {
      if (error.isBizError) {
        console.log("BizError", error.data);
      } else {
        console.log("SystemError", error.data);
      }
    });
}

function post() {
  HttpApi.test
    .create({
      testId: 3001,
      name: "Tom",
    })
    .then(function (response: IHttpApiResponse<CreateResponseV1>) {
      console.log(response);
    });
}
function uploadFiles() {
  HttpApi.test.upload().then(function (response: IHttpApiResponse<Array<string>>) {
    console.log(response);
  });
}

function getDownloadBizError() {
  HttpApi.test.getDownloadBizError().catch((error: any) => {
    if (error.isBizError) {
      console.log("BizError", error.data);
    } else {
      console.log("SystemError", error.data);
    }
  });
}
</script>

<template>
  <button @click="get200">GET (200)</button>
  <button @click="get200BizError">GET (200, BizError)</button>
  <button @click="get200BizErrorThrow">GET (200, BizError, Throw)</button><br />

  <button @click="get2xx">GET (2xx)</button>
  <button @click="get2xxThrow">GET (2xx, Throw)</button><br />

  <button @click="get5xx">GET (5xx)</button>
  <button @click="get5xxThrow">GET (5xx, Throw)</button>

  <button @click="post">POST</button>
  <button @click="uploadFiles">UPLOAD FILES</button><br />

  <button @click="HttpApi.test.getDownload">GET DOWNLOAD</button>
  <button @click="HttpApi.test.getJpgImage">GET JPG IMAGE</button>
  <button @click="HttpApi.test.getPngImage">GET PNG IMAGE</button>
  <button @click="getDownloadBizError">GET DOWNLOAD (BizError, Throw)</button>
  <button @click="HttpApi.test.postDownload">POST DOWNLOAD</button>
  <fieldset>
    <legend>Form</legend>
    <form method="post" enctype="multipart/form-data" action="http://localhost:8080/v1/test/upload">
      testId: <input name="testId" value="8001" /><br />
      files: <input id="files" type="file" name="files" multiple /><br />
      <input type="submit" value="Submit" />
    </form>
  </fieldset>
</template>

<style scoped>
button,
input {
  margin: 5px;
}
</style>
./api/HttpApi
