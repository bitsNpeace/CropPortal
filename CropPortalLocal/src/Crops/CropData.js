import { useEffect, useState } from "react";
import { getCrop } from '../services/FarmerService';
import { getCurrentJWT } from '../auth';
function CropData() {
    var [Crops, setCrops] = useState([])
    const handleChange = (property) => {
        setCrops(property)
    };
    var datas=[]
    
    
    useEffect(() => {
        getCrop(getCurrentJWT()).then((data) => {
            handleChange(data)
            console.log(Crops)
        });
    }, [])
    console.log(Crops)
    return (Crops)

}

export default CropData