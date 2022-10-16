import React, { useEffect, useState } from 'react';
import { signUp } from '../services/userService';
import './RegisterStyle.css'
import "./styles.css";
import { Button } from 'reactstrap';
import { Form } from 'reactstrap';
import { FormGroup } from 'reactstrap';
import { Label } from 'reactstrap';
import { Input } from 'reactstrap';
import { Card, Container, CardHeader, CardBody } from 'reactstrap';
import { Col, Row } from 'react-bootstrap';
import { toast } from 'react-toastify';
const Register = () => {

    const [data, setData] = useState({
        fullName: '',
        email: '',
        password: '',
        roles: '',
        active: '',
    })
    const [error, setError] = useState({
        errors: {},
        isError: false
    })
    // useEffect(() => {
    //     console.log(data)
    // }, [data])

    const handleChange = (event, property) => {
        setData({ ...data, [property]: event.target.value })
    }

    const resetData = () => {
        setData({
            fullName: '',
            email: '',
            password: '',
            roles: '',
            active: '',
        })
    }

    const submitForm = (event) => {
        event.preventDefault()
        if(error.isError)
        {
            toast.error("form data is invalid");
            return;
        }
        console.log(data)
        //data validate

        //call server api
        signUp(data).then((resp) => {
            console.log(resp)
            console.log("success")
            setError({ ...error, isError: false, errors: "" });
            toast.success("User is registered successfully")
            setData({
                fullName: '',
                email: '',
                password: '',
                roles: '',
                active: '',
            })
        }).catch((error) => {
            console.log(error)
            //handle errors
            setError({
                errors:error,
                isError:true,
            })
        })
    };


    return (
        <Container>

            <Row className='mt-4'>
                {/* {JSON.stringify(data)} */}
                <Col sm={{ size: 6, offset: .5 }}>
                    <Card style={{backgroundColor:'#05386B',fontFamily:'SilkScreen'}} inverse>
                        <CardHeader className='text-center'>
                            <h3>User Registration</h3>
                        </CardHeader>
                        <CardBody>
                            <Form onSubmit={submitForm}>
                                <FormGroup>
                                    <Label for="fullName">
                                        Name
                                    </Label>
                                    <Input
                                        id="fullName"
                                        name="fullName"
                                        placeholder="Enter Name"
                                        type="name"
                                        onChange={(e) => handleChange(e, 'fullName')}
                                        value={data.fullName}
                                        // invalid={error.errors?.response?.data?.fullName?true:false}
                                    />
                                </FormGroup>
                                <FormGroup>
                                    <Label for="email">
                                        Email
                                    </Label>
                                    <Input
                                        id="email"
                                        name="email"
                                        placeholder="Enter Email"
                                        type="email"
                                        onChange={(e) => handleChange(e, 'email')}
                                        value={data.email}
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
                                        onChange={(e) => handleChange(e, 'password')}
                                        value={data.password}
                                    />
                                </FormGroup>
                                <FormGroup>
                                    <Label for="roles">
                                        Select Role
                                    </Label>
                                    <Input
                                        id="roles"
                                        name="roles"
                                        type="select"
                                        className='ml-2'
                                        onChange={(e) => handleChange(e, 'roles')}
                                        value={data.roles}
                                    >
                                        <option>
                                            Select Any Role
                                        </option>
                                        <option>
                                            ROLE_FARMER
                                        </option>
                                        <option>
                                            ROLE_DEALER
                                        </option>
                                        <option>
                                            ROLE_ADMIN
                                        </option>
                                    </Input>
                                </FormGroup>
                                <FormGroup>
                                    <Label for="active">
                                        Active
                                    </Label>
                                    <Input
                                        id="active"
                                        name="active"
                                        type="select"
                                        className='ml-2'
                                        onChange={(e) => handleChange(e, 'active')}
                                        value={data.active}
                                    >
                                        <option>
                                            Select role
                                        </option>
                                        <option>
                                            true
                                        </option>
                                        <option>
                                            false
                                        </option>

                                    </Input>
                                </FormGroup>

                                <Container className='text-center'>
                                    <Button color="light" outline>Register</Button>
                                    <Button onClick={resetData} color="secondary" type="reset" className="ml-2">Reset</Button>
                                </Container>
                            </Form>
                        </CardBody>
                    </Card>
                </Col>
            </Row>


        </Container>
    )
}

export default Register;