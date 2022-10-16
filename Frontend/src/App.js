import "./App.css";
import Header from "./components/Header";
import { Link, BrowserRouter, Routes } from "react-router-dom"
import { Route } from "react-router-dom";
import Home from "./components/Home";
// import Cart from "./components/Cart";
import Register from "./components/Register";
import Login from "./components/Login";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import ProfileInfo from "./components/ProfileInfo";
import UserDashboard from "./components/userDashboard";
import PaymentGate from "./userDash/PaymentDash";
import PaymentDone from "./userDash/PaymentDone";

import AllUsers from "./userDash/AllUsers";


function App() {
  return (

    <div>


      <BrowserRouter>
        <ToastContainer position="bottom-center" />
        <Header />
        {/* <div className="App"> */}
        <Routes>
          <Route path="/" exact element={<Home />} />
          {/* <Route path="/cart" element={<Cart />} /> */}
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route path="/farmer" element={<ProfileInfo />} />
          <Route path="/userDash" element={<UserDashboard />} />
          {/* <Route path="/userDash1" element={<UserDashboard />} /> */}
          <Route path="/paymentGate" element={<PaymentGate />} />
          <Route path="/paymentDone" element={<PaymentDone />} />
          <Route path="/AllUsers" element={<AllUsers />} />
          
        </Routes>
        {/* </div> */}
      </BrowserRouter>
    </div>
  );
}

export default App;
