// import { Card } from "react-bootstrap";
// import { Button } from "reactstrap";
// import { Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
// import { Form } from 'reactstrap';
// import { FormGroup } from 'reactstrap';
// import { Label } from 'reactstrap';
// import { Input } from 'reactstrap';
// // import { CartState } from "../context/Context";
// import { getCrop } from '../services/FarmerService';
// import { deleteCropFrom } from '../services/FarmerService';
// import { getCurrentJWT } from '../auth';
// import { useEffect, useState } from "react";
// import { getAllCrop } from "../services/DealerService";
// import CropData from "./CropData";
// import { useNavigate } from "react-router-dom";
// function Crop() {

//     // var [Crops, setCrops] = useState([])
//     // const handleChange = (property) => {
//     //     setCrops(property)
//     // };
//     // useEffect(() => {
//     //     CropData().then((data) => {
//     //         handleChange(data)
//     //         console.log(Crops)
//     //     });
//     // }, [])
//     // const navigate = useNavigate()
//     const [modal, setModal] = useState(false);

//     const [cropId, setCropId] = useState({
//         id: 0
//     })
    
//     const handleChange = (event, property) => {
//         setCropId({ ...cropId, [property]: event.target.value })
//     }
//     const deleteCrop = () => {
//         setModal(!modal);
//         // deleteCropFrom(getCurrentJWT(),cropId).then((resp)=>{
//         //     console.log(resp)
//         // })
//     }
    
//     const confirmDel = (event) => {

//         deleteCropFrom(getCurrentJWT(), cropId.id).then((resp) => {
//             console.log(resp)

//         })
//         setModal(!modal);
//         window.location.reload(false);
//     }
//     const closeBtn = (
//         <button className="close" onClick={deleteCrop} type="button">
//           &times;
//         </button>
//       );
    
//     const Crops = CropData()
    
//     const Cropss = Crops.map(
//         (element) => {

//             return (
//                 <div className="d-inline-block" style={{ paddingBottom: 10 }}>
//                     <Card className="d-inline-block ml-3" >
//                         <Card.Img variant="top" src="https://thumbs.dreamstime.com/b/crops-linear-icon-modern-outline-logo-concept-white-ba-background-agriculture-farming-gardening-collection-suitable-133513789.jpg" height="200px" />
//                         <Card.Body>
//                             <Card.Title>Id: {element.cropId} {element.cropName}</Card.Title>
//                             <Card.Subtitle style={{ paddingBottom: 10 }}>
//                                 <span>Type: {element.cropType}</span>
//                             </Card.Subtitle>
//                             <Card.Subtitle style={{ paddingBottom: 10 }}>
//                                 <span>Quantity: {element.cropQuantity}</span>
//                             </Card.Subtitle>
//                             <Card.Subtitle style={{ paddingBottom: 10 }}>
//                                 <span>Location: {element.cropLoaction}</span>
//                             </Card.Subtitle>
//                             <Card.Subtitle style={{ paddingBottom: 10 }}>
//                                 <span>Price:₹ {element.price}</span>
//                             </Card.Subtitle>
//                             <Button onClick={deleteCrop} color="danger" outline>Delete</Button>
//                         </Card.Body>
//                     </Card>
//                     <Modal isOpen={modal} toggle={deleteCrop} backdropTransition={{ timeout: 1300 }}>
//                         <ModalHeader toggle={deleteCrop} close={closeBtn}>Do You want to Delete?</ModalHeader>
//                         <ModalBody>
//                             <Form>
//                                 <FormGroup>
//                                     <Label for="id">
//                                         Crop ID
//                                     </Label>
//                                     <Input
//                                         id="id"
//                                         name="id"
//                                         placeholder="Enter CropId"
//                                         type="number"
//                                         onChange={(e) => handleChange(e, 'id')}
//                                         value={cropId.id}
//                                     // invalid={error.errors?.response?.data?.fullName?true:false}
//                                     />
//                                 </FormGroup>
//                                 {/* <Button onClick={confirmDel} style={{ padding: 5, margin: 5 }}>Confirm</Button>
//                         <Button style={{ padding: 5, margin: 5 }}>Cancel</Button> */}
//                             </Form>
//                         </ModalBody>
//                         <ModalFooter>
//                             <Button color="danger" outline onClick={confirmDel}>
//                                 Delete
//                             </Button>{' '}
//                             <Button outline onClick={deleteCrop}>
//                                 Cancel
//                             </Button>
//                         </ModalFooter>
//                     </Modal>
//                 </div>

//             )
//         }
//     )
//     return (
//         <div>
//             {Cropss}
//         </div>

//     )


// }

// export default Crop;