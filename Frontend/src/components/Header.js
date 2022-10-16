import { FaShoppingCart } from "react-icons/fa";
import { AiFillDelete } from "react-icons/ai";
import {
  Badge,

  Container,
  Dropdown,
  Nav,
  Navbar,
  NavItem,
} from "react-bootstrap";
import { Button } from "reactstrap";
import { FormControl } from "react-bootstrap";
import { Link, NavLink, useLocation, useNavigate, useRoutes } from "react-router-dom";
// import { CartState } from "../context/Context";
import "./styles.css";
import { useEffect, useState } from "react";
import { doLogOut, getCurrentUserDetail, isLoggedIn } from "../auth";

const Header = () => {

  // const [isOpen, setIsOpen] = useState(false)

  const [login, setLogin] = useState(false)
  const [user, setUser] = useState(undefined)

  useEffect(() => {
    setLogin(isLoggedIn())
    setUser(getCurrentUserDetail())
  }, [login])


  const logout = () => {
    doLogOut(() => {
      setLogin(false)

    })
  }
  const router = useLocation()
  // const[thing,setThing]=useState(false);
  // if(router.pathname==='/AllUsers'){
  //   setThing(true);
  // }
  // else{
  //   setThing(false);
  // }
  const navigate = useNavigate()
  const back = () => {
    navigate(-1)
  }
  return (
    <Navbar className="header" variant="dark" style={{ height: 80, width: '105%', backgroundColor: "#05386B" }}>
      <Container>
        {login && (
          <>
            <Button color="info" outline onClick={back} style={{ marginLeft: '-215px' }}>&larr;</Button>
            {/* <Link to={-1} style={{marginLeft:'-215px',color:'#fff'}}>←</Link> */}
          </>
        )
        }
        <Navbar.Brand>
          <Link to="/" > <span style={{ color: 'white', fontFamily: 'SilkScreen' }}>HomePage </span></Link>
        </Navbar.Brand>
        {useLocation().pathname.split("/")[1] !== "cart" && login && (
          <Navbar.Text className="search">
            <FormControl
              style={{ width: 500 }}
              type="search"
              placeholder="Search any Crop..."
              className="m-auto"
              aria-label="Search"


            />
          </Navbar.Text>
        )}
        {/* <Nav>
          <Dropdown alignRight>
            <Dropdown.Toggle variant="success">
              <FaShoppingCart color="white" fontSize="25px" />
              <Badge>{cart.length}</Badge>
            </Dropdown.Toggle>

            <Dropdown.Menu style={{ minWidth: 370 }}>
              {cart.length > 0 ? (
                <>
                  {cart.map((prod) => (
                    <span className="cartitem" key={prod.id}>
                      <img
                        src={prod.image}
                        className="cartItemImg"
                        alt={prod.name}
                      />
                      <div className="cartItemDetail">
                        <span>{prod.name}</span>
                        <span>₹ {prod.price.split(".")[0]}</span>
                      </div>
                      <AiFillDelete
                        fontSize="20px"
                        style={{ cursor: "pointer" }}
                        onClick={() =>
                          dispatch({
                            type: "REMOVE_FROM_CART",
                            payload: prod,
                          })
                        }
                      />
                    </span>
                  ))}
                  <Link to="/cart">
                    <Button style={{ width: "95%", margin: "0 10px" }}>
                      Go To Cart
                    </Button>
                  </Link>
                </>
              ) : (
                <span style={{ padding: 10 }}>Cart is Empty!</span>
              )}
            </Dropdown.Menu>
          </Dropdown>
        </Nav> */}
        <Nav style={{ margin: "0 20px" }}>
          {login && (
            <>
              <Link to="/" color="white" >
                <Button style={{ width: "95%", margin: "0 15px", backgroundColor: 'white' }} onClick={logout}>
                  <div style={{ fontFamily: 'SilkScreen', color: '#05386B' }}>
                    LogOut
                  </div>
                </Button>
              </Link>

              <Link to="/farmer" color="white" >
                <Button color="primary" style={{ width: "95%", margin: "0 15px",backgroundColor:'white'}}>
                  <div style={{ fontFamily: 'SilkScreen', color: '#05386B' }}>

                    {user.fullName}
                  </div>
                </Button>
              </Link>
            </>
          )
          }
          {
            !login && (
              <>
                <Link to="/register" color="white" style={{ margin: "0 15px" }} >
                  <Button style={{ width: "95%", height: "20px 30px", backgroundColor: 'white', margin: "0 15px" }}>
                    <div style={{ fontFamily: 'SilkScreen', color: '#05386B' }}>
                      Register
                    </div>
                  </Button></Link>
                <Link to="/login" color="white" >
                  <Button style={{ width: "95%", margin: "0 15px", backgroundColor: 'white' }}>

                    <div style={{ fontFamily: 'SilkScreen', color: '#05386B' }}>
                      Login
                    </div>

                  </Button></Link>
              </>
            )
          }

        </Nav>
      </Container>
    </Navbar >
  );
};

export default Header;
