import React from 'react';
import { Navigate } from 'react-router-dom';

const Private = ({ component: Component }) => {
  let isLoggedIn = localStorage.getItem("accessToken")
  if (isLoggedIn) {
    return <Component />
  } else {

    return <Navigate
      to={"/login"}
      replace={true}
    />
  }
};

export default Private;
