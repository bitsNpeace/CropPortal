import React, { useEffect, useState } from 'react';
import { Col, Row } from 'react-bootstrap';
import Crop from '../Crops/Crop';
import { Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import "../components/styles.css"
import { Container } from 'reactstrap';
import { Button } from 'reactstrap';
import { Form } from 'reactstrap';
import { FormGroup } from 'reactstrap';
import { Label } from 'reactstrap';
import { Input } from 'reactstrap';
import { Card, CardHeader, CardBody,CardTitle,CardSubtitle,CardImg } from 'reactstrap';
import { toast } from 'react-toastify';
import { getCurrentJWT } from '../auth';
import { deleteCropFrom, fillCropForm } from '../services/FarmerService';
import CropData from '../Crops/CropData';

// function func(){

// }
function Farmer() {

    const [data, setData] = useState({
        cropId: '',
        cropName: '',
        cropType: '',
        cropDescription: '',
        cropQuantity: '',
        cropLoaction: '',
        price: '',
    });
    const [error, setError] = useState({
        errors: {},
        isError: false
    });

    // useEffect(() => {
    //     console.log(data)
    // }, [data])


    const handleChange = (event, property) => {
        setData({ ...data, [property]: event.target.value })

    };
    const resetData = () => {
        setData({
            cropId: '',
            cropName: '',
            cropType: '',
            cropDescription: '',
            cropQuantity: '',
            cropLoaction: '',
            price: '',
        })
    };


    const submitForm = (event) => {
        event.preventDefault()
        if (error.isError) {
            toast.error("form data is invalid");
            return;
        }

        console.log(data)
        //data validate
        toast.success("Crop is added successfully!! Reload Page to see in Dashboard")
        //call server api
        fillCropForm(getCurrentJWT(), data).then((resp) => {
            console.log(resp)
            console.log("success")

            setError({ ...error, isError: false, errors: "" });

            setData({
                cropId: '',
                cropName: '',
                cropType: '',
                cropDescription: '',
                cropQuantity: '',
                cropLoaction: '',
                price: '',
            })
        }).catch((error) => {
            console.log(error)
            //handle errors
            setError({
                errors: error,
                isError: true,
            })
        })
        // setCropo(CropData())
        
        setInterval(() => {
            console.log('Interval triggered');
            window.location.reload(false);

        }, 3000);
        // window.setInterval(func(), 100000000000); 
        // window.location.reload(false);

    };
    
    var crops=CropData()
    // const[cropo,setCropo]=useState(CropData())

    //Crop.js

    const [modal, setModal] = useState(false);

    const [cropId, setCropId] = useState({
        id: 0
    })

    const handleChang = (event, property) => {
        setCropId({ ...cropId, [property]: event.target.value })
    }
    const deleteCrop = () => {
        setModal(!modal);
        // deleteCropFrom(getCurrentJWT(),cropId).then((resp)=>{
        //     console.log(resp)
        // })
    }

    const confirmDel = (event) => {

        deleteCropFrom(getCurrentJWT(), cropId.id).then((resp) => {
            console.log(resp)

        })
        setModal(!modal);
        window.location.reload(false);
    }
    const closeBtn = (
        <button className="close" onClick={deleteCrop} type="button">
            &times;
        </button>
    );

    return (
        <>

            <div >

                <Row max-width="100%">
                    {/* {JSON.stringify(data)} */}
                    <Col className='d-inline-block mt-4' style={{ paddingLeft: 30, paddingRight: 15, marginBottom: 15 }} md="3">
                        {/* <CropForm/> */}
                        <Card style={{backgroundColor:'#05386B',color:'white',fontFamily:'SilkScreen'}} >
                            <CardHeader className='text-center'>
                                <h3>Add Crop</h3>
                            </CardHeader>
                            <CardBody>
                                <Form onSubmit={submitForm}>
                                    <FormGroup>
                                        <Label for="cropId">
                                            Crop ID
                                        </Label>
                                        <Input
                                            id="cropId"
                                            name="cropId"
                                            placeholder="Enter CropId"
                                            type="number"
                                            onChange={(e) => handleChange(e, 'cropId')}
                                            value={data.cropId}
                                        // invalid={error.errors?.response?.data?.fullName?true:false}
                                        />
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="cropName">
                                            Crop Name
                                        </Label>
                                        <Input
                                            id="cropName"
                                            name="cropName"
                                            placeholder="Enter Crop Name"
                                            type="name"
                                            onChange={(e) => handleChange(e, 'cropName')}
                                            value={data.cropName}
                                        // invalid={error.errors?.response?.data?.fullName?true:false}
                                        />
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="cropType">
                                            Crop Type
                                        </Label>
                                        <Input
                                            id="cropType"
                                            name="cropType"
                                            placeholder="Enter Crop Type"
                                            type="name"
                                            onChange={(e) => handleChange(e, 'cropType')}
                                            value={data.cropType}
                                        />
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="cropDescription">
                                            Crop Description
                                        </Label>
                                        <Input
                                            id="cropDescription"
                                            name="cropDescription"
                                            placeholder="Enter Crop Desc"
                                            type="name"
                                            onChange={(e) => handleChange(e, 'cropDescription')}
                                            value={data.cropDescription}
                                        />
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="cropQuantity">
                                            Crop Quantity
                                        </Label>
                                        <Input
                                            id="cropQuantity"
                                            name="cropQuantity"
                                            placeholder="Enter Quantity"
                                            type="name"
                                            onChange={(e) => handleChange(e, 'cropQuantity')}
                                            value={data.cropQuantity}
                                        />
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="cropLoaction">
                                            Crop Location
                                        </Label>
                                        <Input
                                            id="cropLoaction"
                                            name="cropLoaction"
                                            type="name"
                                            placeholder="Enter Location"
                                            onChange={(e) => handleChange(e, 'cropLoaction')}
                                            value={data.cropLoaction}
                                        >

                                        </Input>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="price">
                                            Price
                                        </Label>
                                        <Input
                                            id="price"
                                            name="price"
                                            type="name"
                                            placeholder="Enter Price"
                                            onChange={(e) => handleChange(e, 'price')}
                                            value={data.price}
                                        >

                                        </Input>
                                    </FormGroup>

                                    <Container className='text-center'>
                                        <Button color="light" outline>Add Crop</Button>
                                        <Button color="secondary" type="reset" className="ml-2" onClick={resetData}>Reset</Button>
                                    </Container>
                                </Form>
                            </CardBody>
                        </Card>
                    </Col>
                    <Col className='d-inline-block mt-4'>
                        {/* <div>
                            <Crop />
                            {/* {Cropss} */}
                        {/* </div>} */}
                        <div>


                            {crops.map((element) => (
                                <div className="d-inline-block" style={{ paddingBottom: 10 }}>
                                    <Card className="d-inline-block ml-3 cardIn" style={{fontFamily:'SilkScreen'}} >
                                        <CardImg variant="top" src="https://thumbs.dreamstime.com/b/crops-linear-icon-modern-outline-logo-concept-white-ba-background-agriculture-farming-gardening-collection-suitable-133513789.jpg" height="200px" />
                                        <CardBody>
                                            <CardTitle>Id: {element.cropId} {element.cropName}</CardTitle>
                                            <CardSubtitle style={{ paddingBottom: 10 }}>
                                                <span>Type: {element.cropType}</span>
                                            </CardSubtitle>
                                            <CardSubtitle style={{ paddingBottom: 10 }}>
                                                <span>Quantity: {element.cropQuantity}</span>
                                            </CardSubtitle>
                                            <CardSubtitle style={{ paddingBottom: 10 }}>
                                                <span>Location: {element.cropLoaction}</span>
                                            </CardSubtitle>
                                            <CardSubtitle style={{ paddingBottom: 10 }}>
                                                <span>Price:₹ {element.price}</span>
                                            </CardSubtitle>
                                            <Button onClick={deleteCrop} color="danger" outline>Delete</Button>
                                        </CardBody>
                                    </Card>
                                    <Modal isOpen={modal} toggle={deleteCrop} backdropTransition={{ timeout: 1300 }} style={{fontFamily:'SilkScreen'}}>
                                        <ModalHeader toggle={deleteCrop} close={closeBtn}>Do You want to Delete?</ModalHeader>
                                        <ModalBody>
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
                                                        onChange={(e) => handleChang(e, 'id')}
                                                        value={cropId.id}
                                                    // invalid={error.errors?.response?.data?.fullName?true:false}
                                                    />
                                                </FormGroup>
                                                {/* <Button onClick={confirmDel} style={{ padding: 5, margin: 5 }}>Confirm</Button>
                                <Button style={{ padding: 5, margin: 5 }}>Cancel</Button> */}
                                            </Form>
                                        </ModalBody>
                                        <ModalFooter>
                                            <Button color="danger" outline onClick={confirmDel}>
                                                Delete
                                            </Button>{' '}
                                            <Button outline onClick={deleteCrop}>
                                                Cancel
                                            </Button>
                                        </ModalFooter>
                                    </Modal>
                                </div>
                            ))

                            }
                        </div>
                    </Col>
                </Row>


            </div>

        </>
    )

}

export default Farmer;