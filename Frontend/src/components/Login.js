import React, { useState } from 'react';
import { toast } from 'react-toastify';
import { Button } from 'reactstrap';
import { Form } from 'reactstrap';
import { FormGroup } from 'reactstrap';
import { Label } from 'reactstrap';
import { Input } from 'reactstrap';
import { Card, Container, Row, Col, CardHeader, CardBody } from 'reactstrap';
import { loginUser } from '../services/userService';
import './RegisterStyle.css'
import { doLogin, isLoggedIn } from '../auth';
import {  useNavigate } from 'react-router-dom';



const Login=()=> {

    const navigate=useNavigate()

    const[loginDetail,setLoginDetail]=useState({
        email:'',
        password:'',
    })
    const handleChange=(e,field)=>{
        let actualValue=e.target.value
        setLoginDetail({
            ...loginDetail,
            [field]:actualValue
        })
    }
    
    const submitForm=(event)=>{
        event.preventDefault();

        //data from server
        loginUser(loginDetail).then((jwtToken)=>{

            //save data to local storage
            doLogin(jwtToken,()=>{
                console.log("login detail saved to local storage")
                
                // render(<Header/>)
                // {<Link to="/" color="white"></Link>}
                navigate("/userDash")
                
            })

            console.log("jwt: ")
            console.log(jwtToken)
            toast.success("Login success")
        }).catch(error=>{
            console.log(error)
        })
        setInterval(() => {
            console.log('Interval triggered');
            window.location.reload(false);
            
        }, 3000);

    }
    const resetForm = () => {
        setLoginDetail({
            
            email: '',
            password: '',
            
        })
    }

    return (
        <Container>
            <Row className='mt-4'>
                <Col sm={{ size: 6, offset: 3 }}>
                    <Card color='dark' inverse>
                        <CardHeader style={{textAlign:'center',fontSize:'30px'}}>
                            User Login
                        </CardHeader>
                        <CardBody>
                        {/* onSubmit={submitForm} */}
                            <Form onSubmit={submitForm}>
                                <FormGroup>
                                    <Label for="email">
                                        Email
                                    </Label>
                                    <Input
                                        id="email"
                                        name="email"
                                        placeholder="Enter Email"
                                        type="email"
                                        value={loginDetail.email}
                                        onChange={(e)=>handleChange(e,'email')}
                                    />
                                </FormGroup>
                                <FormGroup>
                                    <Label for="password">
                                        Password
                                    </Label>
                                    <Input
                                        id="password"
                                        name="password"
                                        placeholder="Enter Password"
                                        type="password"
                                        value={loginDetail.password}
                                        onChange={(e)=>handleChange(e,'password')}
                                    />
                                </FormGroup>
                                <Container className='text-center'>
                                    
                                    <Button color="light" outline>Login</Button>
                                    
                                    <Button onClick={resetForm} color="secondary" type="reset" className="ml-2">Reset</Button>
                                </Container>
                            </Form>
                        </CardBody>
                    </Card>
                </Col>
            </Row>
        </Container>
    )
}
export default Login;