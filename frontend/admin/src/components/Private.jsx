import React from "react";
import useKeycloak from "../hooks/useKeycloak";

const PrivatePage = ({ page: Page, rolesAccept }) => {
  const { keycloak, hasRoles } = useKeycloak();

  if (Array.isArray(rolesAccept)) {
    if (keycloak.authenticated) {
      if (hasRoles(rolesAccept)) {
        return <Page />;
      } else {
        return <h1>403 Forbidden</h1>;
      }
    } else {
      keycloak.login();
    }
  } else {
    if (keycloak.authenticated) {
      return <Page />;
    } else {
      keycloak.login();
    }
  }
};

export default PrivatePage;
