import { Card } from "react-bootstrap";
import { Button, CardImg } from "reactstrap";
// import { CartState } from "../context/Context";
import AllCrop from "../Crops/AllCrop";
import Crop from "../Crops/Crop";
import "../components/styles.css"
import { Link, useNavigate } from "react-router-dom";
import { isLoggedIn } from "../auth";


const Home = () => {
  const navigate = useNavigate()
  const goto = () => {

    if (isLoggedIn()) {
      navigate("/userDash")
    } else {
      navigate("/login")
    }
  }

  return (
    <div className="home">
      {/* <Card>
        <CardImg variant="top" src="https://i.pinimg.com/originals/f0/e0/96/f0e0962734376c22ddaa36213694f480.jpg" height="120%"></CardImg>
      </Card> */}

      <div className="productContainer">
        <div style={{ alignItems: 'center', marginTop: 100, fontFamily: 'SilkScreen' }}>
          <h1 style={{ color: 'white' }}>Welcome to Crop Portal</h1>
        </div>
        <div style={{ justifyContent: 'center' }}>
          <Button  onClick={goto} style={{backgroundColor: "#05386B",marginTop:50}}>
            <div style={{fontFamily:'SilkScreen'}}>
              Go to DashBoard
            </div>
          </Button>
        </div>
      </div>
    </div>
  );
};

export default Home;
