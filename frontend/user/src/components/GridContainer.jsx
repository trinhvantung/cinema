import React from "react";

const GridContainer = ({ columns = 5, gap = 10, children }) => {
  const style = {
    gridTemplateColumns: `repeat(${columns}, 1fr)`,
    gap,
  };
  return (
    <div className="grid_container" style={style}>
      {children}
    </div>
  );
};

export default GridContainer;
