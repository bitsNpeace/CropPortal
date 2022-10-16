import React from 'react'
import { doLogOut, getCurrentJWT, getCurrentUserDetail, isLoggedIn } from '../auth';
import { Navigate, useNavigate } from 'react-router-dom';
import background from "./Images/bg-crop1.jpg";
import "./styles.css";
import { Toast, ToastHeader, ToastBody, CardHeader, CardBody, CardTitle, CardSubtitle, Card, Button } from 'reactstrap';
import { deleteFarmer } from '../services/FarmerService';
import { deleteDealer } from '../services/DealerService';
const ProfileInfo = () => {
    const data = getCurrentUserDetail()
    const navigate=useNavigate()
    const role = data.roles.substring('ROLE_'.length);
    const deleteId=()=>{
        if(role==="FARMER"){
            deleteFarmer(getCurrentJWT())
            doLogOut(() => {
                navigate("/")
          
              })
        }
        else if(role==="DEALER"){
            deleteDealer(getCurrentJWT())
            doLogOut(() => {
                navigate("/")
          
              })
        }
        else if(role==="ADMIN"){
            console.log("hello");
        }
    }
    if (isLoggedIn()) {
        return (
            <div style={{width:'105%'}}>
                <div className="p-3 my-2 rounded" style={{ display: 'flex', height: '100%', position: 'absolute', justifyContent: 'center', alignItems: 'center', left: '0px', top: 0, width: '105%', overflow: 'hidden', backgroundColor: '#5CDBD95'}}>
                    <div style={{ position: 'absolute', overflow: 'hidden', height: '500px', display: 'flex', justifyContent: 'center', boxShadow: "20px 20px 50px rgba(0,0,0,0.5)", borderRadius: '15px', borderTop: '1px solid rgba(255,255,255,0.5)', borderLeft: '1px solid rgba(255,255,255,0.5)', overflow: 'hidden', color: "#000", background: "rgba(255,255,255,0.1)", backdropFilter: `blur(5px)` }}>
                        <div style={{fontFamily:'SilkScreen',margin: "10px 40px"}}>
                            <h1 style={{ fontSize: '3em', color: '#fff', textAlign: 'center' }}>Profile Info</h1>
                            <hr className='hr' />
                            <div style={{ fontSize: '2em', color: '#fff', fontWeight: 300, marginTop: 60 }}>

                                <span><h3>Name:  {data.fullName}</h3></span>
                                <span><h3>Email: {data.email}</h3></span>
                                <span><h3>Role:  {role}</h3></span>
                                <span><h3>Crops Listed: </h3></span>
                                <span><h3>Account status: Active</h3></span>
                            </div>
                            <Button onClick={deleteId} color="danger" style={{ color: '#fff', alignContent: 'center',float:'right',marginTop:'20px' }}>Delete Id</Button>
                        </div>

                    </div>
                </div>
            </div>
        )
        // return JSON.stringify(getCurrentUserDetail())
    } else {
        return <Navigate to={"/login"} />
    }

}

export default ProfileInfo;