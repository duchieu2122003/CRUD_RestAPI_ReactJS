import { Link } from "react-router-dom";
import React from "react";

export const SideBar = () => {
  return (
    <div>
      <nav>
        <ul>
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/about">about</Link>
          </li>
        </ul>
      </nav>
    </div>
  );
};
