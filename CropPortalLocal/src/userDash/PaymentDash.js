import React, { useEffect, useState } from 'react';
import { Button, CardSubtitle, CardTitle } from 'reactstrap';
import { confirmOrder } from '../services/DealerService';
import { Card, CardHeader, CardBody } from 'reactstrap';

import { Form } from 'reactstrap';
import { FormGroup } from 'reactstrap';
import { Label } from 'reactstrap';
import { Input } from 'reactstrap';
import { useNavigate } from 'react-router-dom';
import { getCurrentJWT } from '../auth';
import PaymentDone from './PaymentDone';
const PaymentGate = () => {
    const[orderId,setOrderId]=useState({
        id:0
    })
    // const orderId=0
    const handleChange = (event, property) => {
        setOrderId({ ...orderId, [property]: event.target.value })
    }
    // useEffect(() => {
    //     setOrderId(orderId)
    //   }, [orderId])
    console.log(orderId.id)
    const navigate=useNavigate()
    const confirmOrd = (event) => {
        confirmOrder(getCurrentJWT(),orderId.id).then((resp)=>{
            navigate("/paymentDone")
        })
    }
    return (
        <>
            <div className='mt-4'>
                <Card className="d-inline-block ml-3" color='dark' inverse>
                    <CardHeader>Do you want to confirm? </CardHeader>
                    <Form>
                        <FormGroup>
                            <Label for="id">
                                orderId
                            </Label>
                            <Input
                                id="id"
                                name="id"
                                placeholder="Enter OrderId"
                                type="number"
                                onChange={(e) => handleChange(e, 'id')}
                                value={orderId.id}
                            // invalid={error.errors?.response?.data?.fullName?true:false}
                            />
                        </FormGroup>
                        <Button onClick={confirmOrd} style={{ padding: 5, margin: 5 }}>Confirm</Button>
                        <Button style={{ padding: 5, margin: 5 }}>Cancel</Button>
                    </Form>
                </Card>

            </div>
        </>
    )

}

export default PaymentGate;