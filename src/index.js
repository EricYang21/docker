import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import { BrowserRouter } from "react-router-dom";
import {ConfigProvider, message} from "antd";
import axios from "axios";
axios.defaults.baseURL="http://172.17.0.1:8080/";
axios.interceptors.response.use(success=>{
  return success;
},error=>{
    console.error("This error is:", error);
  message.error("There is an unexpected error. PleBase try again later.");
});
const root = ReactDOM.createRoot(document.getElementById("index"));
root.render(
  <React.StrictMode>
    <ConfigProvider
      theme={{
        token: {
          colorPrimary: "#F97432",
        },
      }}
    >
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </ConfigProvider>
  </React.StrictMode>
);
