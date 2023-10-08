import React, { useEffect, useState } from "react";
import { KeycloakContext } from "../hooks/useKeycloak";

export function KeycloakProvider({ children, initOptions, keycloakInstance }) {
  const [keycloak, setKeycloak] = useState(null);

  useEffect(() => {
    if (!keycloak) {
      keycloakInstance.init(initOptions).then(() => {
        setKeycloak(keycloakInstance);

        // keycloakInstance.onTokenExpired = () => {
        //   console.log("Token expired")
        //   keycloakInstance.updateToken(60).success(() => {
        //     console.log("Update token success")
        //   });
        // };
      });
    }
  }, [keycloak]);

  const hasRoles = (roles) =>
    roles.some((role) => keycloakInstance.hasRealmRole(role));

  return (
    <KeycloakContext.Provider value={{ keycloak, hasRoles }}>
      {keycloak ? children : <div>Loading...</div>}
    </KeycloakContext.Provider>
  );
}
