import React from 'react'
import { getCurrentUserDetail, isLoggedIn } from '../auth';
import Header from './Header';
// import {  } from 'react-router-dom';
import { Navigate } from "react-router-dom";
import Farmer from '../userDash/Farmer';
import Dealer from '../userDash/Dealer';
import Admin from '../userDash/Admin';
const UserDashboard = () => {

    if (isLoggedIn()) {
        // return JSON.stringify(getCurrentUserDetail().user);
        if (getCurrentUserDetail().roles === "ROLE_FARMER") {
            return (
                <div style={{backgroundColor:'#379683',width:'105%'}}>
                    
                    <Farmer />

                    
                </div>
            )
        }
        else if(getCurrentUserDetail().roles==="ROLE_DEALER"){
            return <Dealer />
        }
        else{
            return <Admin/>
        }
    } else {
        return <Navigate to={"/login"} />
    }

}

export default UserDashboard;