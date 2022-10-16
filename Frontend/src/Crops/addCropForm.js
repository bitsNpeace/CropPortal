// import React, { useEffect, useState } from 'react';

// import { Button } from 'reactstrap';
// import { Form } from 'reactstrap';
// import { FormGroup } from 'reactstrap';
// import { Label } from 'reactstrap';
// import { Input } from 'reactstrap';
// import { Card, Container, CardHeader, CardBody } from 'reactstrap';
// import { toast } from 'react-toastify';
// import { getCurrentJWT } from '../auth';
// import { fillCropForm } from '../services/FarmerService';
// import Crop from './Crop';
// import { render } from '@testing-library/react';
// import CropData from './CropData';



// const CropForm = () => {


//     const [data, setData] = useState({
//         cropId: '',
//         cropName: '',
//         cropType: '',
//         cropDescription: '',
//         cropQuantity: '',
//         cropLoaction: '',
//         price: '',
//     })
//     const [error, setError] = useState({
//         errors: {},
//         isError: false
//     })
//     // useEffect(() => {
//     //     console.log(data)
//     // }, [data])

//     const handleChange = (event, property) => {
//         setData({ ...data, [property]: event.target.value })
//     }

//     const resetData = () => {
//         setData({
//             cropId: '',
//             cropName: '',
//             cropType: '',
//             cropDescription: '',
//             cropQuantity: '',
//             cropLoaction: '',
//             price: '',
//         })
//     }

//     const submitForm = (event) => {
//         event.preventDefault()
//         if (error.isError) {
//             toast.error("form data is invalid");
//             return;
//         }
//         console.log(data)
//         //data validate

//         //call server api
//         fillCropForm(getCurrentJWT(), data).then((resp) => {
//             console.log(resp)
//             console.log("success")
            
//             setError({ ...error, isError: false, errors: "" });
//             toast.success("User is registered successfully")
//             setData({
//                 cropId: '',
//                 cropName: '',
//                 cropType: '',
//                 cropDescription: '',
//                 cropQuantity: '',
//                 cropLoaction: '',
//                 price: '',
//             })
//         }).catch((error) => {
//             console.log(error)
//             //handle errors
//             setError({
//                 errors: error,
//                 isError: true,
//             })
//         })
        
//     };

//     return (
//         <Card color='dark' inverse >
//             <CardHeader className='text-center'>
//                 <h3>Add Crop</h3>
//             </CardHeader>
//             <CardBody>
//                 <Form onSubmit={submitForm}>
//                     <FormGroup>
//                         <Label for="cropId">
//                             Crop ID
//                         </Label>
//                         <Input
//                             id="cropId"
//                             name="cropId"
//                             placeholder="Enter CropId"
//                             type="number"
//                             onChange={(e) => handleChange(e, 'cropId')}
//                             value={data.cropId}
//                         // invalid={error.errors?.response?.data?.fullName?true:false}
//                         />
//                     </FormGroup>
//                     <FormGroup>
//                         <Label for="cropName">
//                             Crop Name
//                         </Label>
//                         <Input
//                             id="cropName"
//                             name="cropName"
//                             placeholder="Enter Crop Name"
//                             type="name"
//                             onChange={(e) => handleChange(e, 'cropName')}
//                             value={data.cropName}
//                         // invalid={error.errors?.response?.data?.fullName?true:false}
//                         />
//                     </FormGroup>
//                     <FormGroup>
//                         <Label for="cropType">
//                             Crop Type
//                         </Label>
//                         <Input
//                             id="cropType"
//                             name="cropType"
//                             placeholder="Enter Crop Type"
//                             type="name"
//                             onChange={(e) => handleChange(e, 'cropType')}
//                             value={data.cropType}
//                         />
//                     </FormGroup>
//                     <FormGroup>
//                         <Label for="cropDescription">
//                             Crop Description
//                         </Label>
//                         <Input
//                             id="cropDescription"
//                             name="cropDescription"
//                             placeholder="Enter Crop Desc"
//                             type="name"
//                             onChange={(e) => handleChange(e, 'cropDescription')}
//                             value={data.cropDescription}
//                         />
//                     </FormGroup>
//                     <FormGroup>
//                         <Label for="cropQuantity">
//                             Crop Quantity
//                         </Label>
//                         <Input
//                             id="cropQuantity"
//                             name="cropQuantity"
//                             placeholder="Enter Quantity"
//                             type="name"
//                             onChange={(e) => handleChange(e, 'cropQuantity')}
//                             value={data.cropQuantity}
//                         />
//                     </FormGroup>
//                     <FormGroup>
//                         <Label for="cropLoaction">
//                             Crop Location
//                         </Label>
//                         <Input
//                             id="cropLoaction"
//                             name="cropLoaction"
//                             type="name"
//                             placeholder="Enter Location"
//                             onChange={(e) => handleChange(e, 'cropLoaction')}
//                             value={data.cropLoaction}
//                         >

//                         </Input>
//                     </FormGroup>
//                     <FormGroup>
//                         <Label for="price">
//                             Price
//                         </Label>
//                         <Input
//                             id="price"
//                             name="price"
//                             type="name"
//                             placeholder="Enter Price"
//                             onChange={(e) => handleChange(e, 'price')}
//                             value={data.price}
//                         >

//                         </Input>
//                     </FormGroup>

//                     <Container className='text-center'>
//                         <Button color="light" outline>Add Crop</Button>
//                         <Button color="secondary" type="reset" className="ml-2" onClick={resetData}>Reset</Button>
//                     </Container>
//                 </Form>
//             </CardBody>
//         </Card>
//     )

// }

// export default CropForm