import { useNavigate } from "react-router-dom";
import { Button } from "reactstrap";
import { generateReport, getAllUsers } from "../services/AdminService";
import AllUsers from "./AllUsers";
import "../components/styles.css"

const Admin = () => {
    const navigate = useNavigate()
    const AllUser = () => {
        navigate("/AllUsers");

    }
    const report = () => {
        generateReport().then((resp) => {

            let file = new File([resp], "UserData", { type: "application/vnd.ms-excel" });
            let exportUrl = URL.createObjectURL(file);
            window.location.assign(exportUrl);
            URL.revokeObjectURL(exportUrl);
        })
    }
    return (


        <div style={{ fontFamily: 'SilkScreen', alignItems: 'center', justifyContent: 'center' }}>
            <h1 style={{ color: '#fff', textAlign: 'center' }}>Welcome to Admin DashBoard</h1>
            <div className="centerButton" style={{marginTop:'100px'}}>
                <Button onClick={AllUser} style={{width:'200px', textAlign:'center', display: 'flex', margin: '30px 0', backgroundColor: 'white', color: '#05386B' }}>See all users</Button>
            </div>
            <div className="centerButton">
                <Button onClick={report} style={{ width:'200px',display: 'flex', margin: '30px 0', backgroundColor: 'white', color: '#05386B' }}>Generate Report</Button>
            </div>
            <div className="centerButton">
                <Button style={{ width:'200px',display: 'flex', margin: '30px 0', backgroundColor: 'white', color: '#05386B' }}>Delete User</Button>
            </div>
        </div>

    )
}

export default Admin;