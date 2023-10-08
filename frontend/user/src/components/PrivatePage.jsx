import React from "react";
import useKeycloak from "../hooks/useKeycloak";

const PrivatePage = ({ page: Page, rolesAccept }) => {
  const { keycloak, hasRoles } = useKeycloak();

  if (Array.isArray(rolesAccept)) {
    console.log("1");
    if (keycloak.authenticated) {
      console.log("2");
      if (hasRoles(rolesAccept)) {
        console.log("3");
        return <Page />;
      } else {
        console.log("4");
        return <h1>403 Forbidden</h1>;
      }
    } else {
      console.log("5");
      keycloak.login();
    }
  } else {
    console.log("6");
    if (keycloak.authenticated) {
      console.log("7");
      return <Page />;
    } else {
      console.log("8");
      keycloak.login();
    }
  }
};

export default PrivatePage;
