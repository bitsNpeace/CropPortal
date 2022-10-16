import { getAllUsers } from "../services/AdminService";
import { Card, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import "../components/styles.css"
const AllUsers = () => {
    const navigate=useNavigate()
    var [Users, setUsers] = useState([])
    const handleChange = (property) => {
        setUsers(property)
    };
    console.log()
    useEffect(() => {
        getAllUsers().then((data) => {
            handleChange(data)
            console.log(data)
        });
    }, [])
    var str="No"
    // const dealerCrops=getAllCrop(getCurr)
    const Userss = Users.map(
        (element) => {
            if(element.active){
                str="Yes"
            }
            return (
                <div className="d-inline-block" style={{marginTop:'20px',marginBottom:'20px'}}>
                    <Card className="d-inline-block ml-5 cardInto" style={{width:'450px'}}>
                        <Card.Img variant="top" src="https://miro.medium.com/max/1000/1*N5w9Ay0VlQBKF4b11C0LdQ.png" height="200px" />
                        <Card.Body>
                            <Card.Title>{element.fullName}</Card.Title>
                            <Card.Subtitle style={{ paddingBottom: 10 }}>
                                <span>UserId: {element.userId}</span>
                            </Card.Subtitle>
                            <Card.Subtitle style={{ paddingBottom: 10 }}>
                                <span>UserEmail: {element.email}</span>
                            </Card.Subtitle>
                            <Card.Subtitle style={{ paddingBottom: 10 }}>
                                <span>UserRole: {element.roles}</span>
                            </Card.Subtitle>
                            <Card.Subtitle style={{ paddingBottom: 10 }}>
                                <span>isActive: {str}</span>
                            </Card.Subtitle>
                            
                        </Card.Body>
                    </Card>
                </div>
            )
        }
    )
    return (
        <div style={{backgroundColor:'#5CDB95'}}>
            
            {Userss}
        </div>

    )


}

export default AllUsers;