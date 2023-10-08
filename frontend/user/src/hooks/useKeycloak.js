import { createContext, useContext } from "react";

// Tạo một context cho Keycloak
export const KeycloakContext = createContext();

const useKeycloak = () => {
  const { keycloak, hasRoles } = useContext(KeycloakContext);
  return { keycloak, hasRoles };
};

export default useKeycloak;
