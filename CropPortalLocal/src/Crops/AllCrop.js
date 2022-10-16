import { Card, Button } from "react-bootstrap";
import { Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import { getCurrentJWT } from '../auth';
import { Form } from 'reactstrap';
import { FormGroup } from 'reactstrap';
import "../components/styles.css"
import { Label } from 'reactstrap';
import { Input } from 'reactstrap';
import { useEffect, useState } from "react";
import { confirmOrder, getAllCrop, getOrderReceipt, placeOrder, showOrder } from "../services/DealerService";
import { Link, useNavigate } from "react-router-dom";
const AllCrop = () => {
    const navigate = useNavigate()
    var [Crops, setCrops] = useState([])
    const handleChange = (property) => {
        setCrops(property)
    };
    const [orderData, setOrderData] = useState(0);
    useEffect(() => {
        getAllCrop(getCurrentJWT()).then((data) => {
            handleChange(data)
            console.log(Crops)
        });
    }, [])
    const [modal, setModal] = useState(false);
    const cancelOrder=()=>{
        setModal(!modal);
    }
    const closeBtn = (
        <button className="close" onClick={cancelOrder} type="button">
            &times;
        </button>
    );
    const[ordersId,setOrdersId]=useState({
        id:0
    })
    
    // const dealerCrops=getAllCrop(getCurr)
    const Cropss = Crops.map(
        (element) => {


            let id = element.cropId
            
            const handleChan = (property) => {
                setOrderData(property)
            };
            
            const buyCrop = (event) => {
                placeOrder(getCurrentJWT(), id).then((resps) => {
                    console.log(resps)
                    
                    handleChan(resps)
                });

                // getOrderReceipt(getCurrentJWT(), id).then((resps) => {
                //     handleChan(resps);
                //     orderUg=resps;
                // });
                setModal(!modal);
            }
            
            const handleChanges = (event, property) => {
                setOrdersId({ ...ordersId, [property]: event.target.value })
            }
            const confirmOrd = (event) => {
                confirmOrder(getCurrentJWT(),ordersId.id).then((resp)=>{
                    // navigate("http://localhost:9090")
                    window.open('http://localhost:9090', '_blank');
                })
                setModal(!modal);
            }
            



            return (
                <div className="d-inline-block" style={{ paddingBottom: 10,fontFamily:'SilkScreen' }}>
                    <Card className="d-inline-block ml-3 cardIn" >
                        <Card.Img variant="top" src="https://thumbs.dreamstime.com/b/crops-linear-icon-modern-outline-logo-concept-white-ba-background-agriculture-farming-gardening-collection-suitable-133513789.jpg" height="200px" />
                        <Card.Body>
                            <Card.Title>{element.cropName}</Card.Title>
                            <Card.Subtitle style={{ paddingBottom: 10 }}>
                                <span>Type: {element.cropType}</span>
                            </Card.Subtitle>
                            <Card.Subtitle style={{ paddingBottom: 10 }}>
                                <span>Quantity: {element.cropQuantity}</span>
                            </Card.Subtitle>
                            <Card.Subtitle style={{ paddingBottom: 10 }}>
                                <span>Location: {element.cropLoaction}</span>
                            </Card.Subtitle>
                            <Card.Subtitle style={{ paddingBottom: 10 }}>
                                <span>Price:₹ {element.price}</span>
                            </Card.Subtitle>
                            <Button onClick={buyCrop} >Buy Crop</Button>
                        </Card.Body>
                    </Card>
                    <Modal isOpen={modal} toggle={buyCrop} backdropTransition={{ timeout: 1300 }} style={{fontFamily:'SilkScreen'}}>
                        <ModalHeader toggle={buyCrop} close={closeBtn}>Do You want to Confirm?</ModalHeader>
                        <ModalBody>
                            <div>{orderData}</div>
                            <Form>
                                <FormGroup>
                                    <Label for="id">
                                        Order ID
                                    </Label>
                                    <Input
                                        id="id"
                                        name="id"
                                        placeholder="Enter OrderId to confirm"
                                        type="number"
                                        onChange={(e) => handleChanges(e, 'id')}
                                        value={ordersId.id}
                                    // invalid={error.errors?.response?.data?.fullName?true:false}
                                    />
                                </FormGroup>
                                {/* <Button onClick={confirmDel} style={{ padding: 5, margin: 5 }}>Confirm</Button>
                                <Button style={{ padding: 5, margin: 5 }}>Cancel</Button> */}
                            </Form>
                        </ModalBody>
                        <ModalFooter>
                            <Button color="danger" outline onClick={confirmOrd} >
                                Confirm
                            </Button>{' '}
                            <Button outline onClick={cancelOrder}>
                                Cancel
                            </Button>
                        </ModalFooter>
                    </Modal>
                </div>
            )
        }
    )
    return (
        <div>
            {Cropss}
        </div>

    )


}

export default AllCrop;