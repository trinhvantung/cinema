import React, { useEffect, useState } from "react";
import { KeycloakContext } from "../hooks/useKeycloak";

export function KeycloakProvider({ children, initOptions, keycloakInstance }) {
  const [keycloak, setKeycloak] = useState(null);

  useEffect(() => {
    if (!keycloak) {
      keycloakInstance.init(initOptions).then(() => {
        setKeycloak(keycloakInstance);
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
