import axios from "axios";
import { BASE_URL, myAxios } from "./helper";

export const getAllCrop = (jwt) => {
    return myAxios.get('/dealer/getAllCrop', { headers: {"Authorization" : `Bearer ${jwt}`} }).then((response) => response.data)
}
export const getOrderReceipt = (jwt,cropId) => {
    return myAxios.get('/dealer/genReceipt/'+cropId, { headers: {"Authorization" : `Bearer ${jwt}`} }).then((response) => response.data)
}

export const placeOrder = (jwt,cropId) => {
    return myAxios.post('/dealer/order/'+cropId,cropId ,{ headers: {"Authorization" : `Bearer ${jwt}`} }).then((response) => response.data)
}


export const confirmOrder = (jwt,orderId) => {
    return myAxios.put('/dealer/confirmOrder/'+orderId,orderId,{ headers: {"Authorization" : `Bearer ${jwt}`} } ).then((response) => response.data)
}

export const deleteDealer = (jwt) => {
    return myAxios.delete('/dealer/deleteId',{ headers: {"Authorization" : `Bearer ${jwt}`} })
}
