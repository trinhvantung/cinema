import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import { BrowserRouter } from "react-router-dom";
import Keycloak from "keycloak-js";
import { KeycloakProvider } from "./components/KeycloakProvider";
import { ConfigProvider } from "antd";

export const keycloakInstance = new Keycloak({
  url: "http://localhost:8080",
  realm: "master",
  clientId: "cinema-fe-admin",
});

const initOptions = {
  // onLoad: "check-sso",
  onLoad: "login-required",
  enableLogging: true,
  // silentCheckSsoRedirectUri: window.location.origin + "/silent-check-sso.html",
  pkceMethod: "S256",
  // silentCheckSsoFallback: false,
  // checkLoginIframe: false,
};

ReactDOM.createRoot(document.getElementById("root")).render(
  <BrowserRouter>
    <ConfigProvider
      theme={{
        token: {
          borderRadius: 2,
        },
      }}
    >
      <KeycloakProvider
        keycloakInstance={keycloakInstance}
        initOptions={initOptions}
      >
        <App />
      </KeycloakProvider>
    </ConfigProvider>
  </BrowserRouter>
);
