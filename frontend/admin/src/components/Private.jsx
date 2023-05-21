import React from 'react';
import { Route, Navigate } from 'react-router-dom';

const Private = ({ component: Component, rolesAccept }) => {
  let roles = JSON.parse(localStorage.getItem("roles"))
  roles = roles ? roles : []
  rolesAccept = rolesAccept ? rolesAccept : [-1]
  if (roles.some(role => rolesAccept.includes(role))) {
    return <Component />
  } else {

    return <Navigate
      to={{
        pathname: roles.length !== 0 ? "/" : "/login"
      }}
      replace={true}
    />
  }
};

export default Private;
