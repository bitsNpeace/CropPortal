import React, { useEffect, useState } from 'react';
import { Button, CardSubtitle, CardTitle } from 'reactstrap';
import { confirmOrder } from '../services/DealerService';
import { Card, CardHeader, CardBody } from 'reactstrap';
import { deleteCropFrom } from '../services/FarmerService';
import { Form } from 'reactstrap';
import { FormGroup } from 'reactstrap';
import { Label } from 'reactstrap';
import { Input } from 'reactstrap';
import { useNavigate } from 'react-router-dom';
import { getCurrentJWT } from '../auth';

const DeleteGate = () => {
    const[cropId,setCropId]=useState({
        id:0
    })
    // const orderId=0
    const handleChange = (event, property) => {
        setCropId({ ...cropId, [property]: event.target.value })
    }
    // useEffect(() => {
    //     setOrderId(orderId)
    //   }, [orderId])
    console.log(cropId.id)
    const navigate=useNavigate()
    const confirmDel = (event) => {
        deleteCropFrom(getCurrentJWT(),cropId.id).then((resp)=>{
                console.log(resp)
                
            })
            navigate("/userDash")
    }
    return (
        <>
            <div className='mt-4'>
                <Card className="d-inline-block ml-3" color='dark' inverse>
                    <CardHeader>Do you want to Delete? </CardHeader>
                    <Form>
                        <FormGroup>
                            <Label for="id">
                                Crop ID
                            </Label>
                            <Input
                                id="id"
                                name="id"
                                placeholder="Enter CropId"
                                type="number"
                                onChange={(e) => handleChange(e, 'id')}
                                value={cropId.id}
                            // invalid={error.errors?.response?.data?.fullName?true:false}
                            />
                        </FormGroup>
                        <Button onClick={confirmDel} style={{ padding: 5, margin: 5 }}>Confirm</Button>
                        <Button style={{ padding: 5, margin: 5 }}>Cancel</Button>
                    </Form>
                </Card>

            </div>
        </>
    )

}

export default DeleteGate;