
export const isLoggedIn=()=>{
    let data=localStorage.getItem("data")
    if(data!=null){
        return true;
    }
    else{
        return false;
    }
};

export const doLogin=(data,next)=>{
    localStorage.setItem("data",JSON.stringify(data))
    next()
}

export const doLogOut=(next)=>{
    localStorage.removeItem("data")
    next()
}
//getCurrentUser
export const getCurrentUserDetail=()=>{
    if(isLoggedIn()){
        return JSON.parse(localStorage.getItem("data")).user;
    }else{
        return undefined;
    }
}

export const getCurrentJWT=()=>{
    if(isLoggedIn()){
        return JSON.parse(localStorage.getItem("data")).jwt;
    }else{
        return undefined;
    }
}