import { BASE_URL, myAxios } from "./helper";

export const signUp = (user) => {
    return myAxios.post('/users/create', user).then((response) => response.data)
}

export const loginUser=(loginDetail)=>{
    return myAxios.post('/users/authenticate',loginDetail).then((response)=>response.data)
}

// export const currentUser=()=>{
//     return myAxios.get('/users/check')
// }