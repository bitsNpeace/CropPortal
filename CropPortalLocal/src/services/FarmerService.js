import { BASE_URL, myAxios } from "./helper";

export const getCrop = (jwt) => {
    return myAxios.get('/farmer/allCrop', { headers: {"Authorization" : `Bearer ${jwt}`} }).then((response) => response.data)
}

export const fillCropForm = (jwt,crop) => {
    return myAxios.post('/farmer/addCrop', crop,{ headers: {"Authorization" : `Bearer ${jwt}`} }).then((response) => response.data)
}
export const deleteCropFrom = (jwt,cropId) => {
    return myAxios.delete('/farmer/deleteCrop/'+cropId,{ headers: {"Authorization" : `Bearer ${jwt}`} }).then((response) => response.data)
}
export const deleteFarmer = (jwt) => {
    return myAxios.delete('/farmer/deleteId',{ headers: {"Authorization" : `Bearer ${jwt}`} })
}