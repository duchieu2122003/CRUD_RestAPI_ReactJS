import "./App.css";
import React from "react";
import { Header } from "./component/Header";
import { Footer } from "./component/Footer";
import { SideBar } from "./component/SideBar";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import Home from "./component/Home";
import { About } from "./component/About";

function App() {
  return (
    <div className="App">
        <Router>
          <Header />
          <SideBar />
          <Routes>
            <Route path="/" element={<Navigate to="/home" />} />
            <Route path="/home" element={<Home />} />
            <Route path="/about" element={<About />} />
          </Routes>
          <Footer />
        </Router>
    </div>
  );
}
export default App;
