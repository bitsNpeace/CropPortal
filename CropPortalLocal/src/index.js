import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
// import Context from "./context/Context";
import "bootstrap/dist/css/bootstrap.min.css";

ReactDOM.render(



  <React.StrictMode>
    {/* <Context> */}

    <div  style={{backgroundColor:'#5CDB95',fontFamily:'SilkScreen',width:'105%',backgroundAttachment:'fixed',backgroundRepeat:'no-repeat',backgroundSize:'cover',height:'100vh'}}>
      <App />
    </div>
    {/* </Context> */}
  </React.StrictMode>,
  document.getElementById("root")


);
