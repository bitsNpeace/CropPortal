import { BASE_URL, myAxios } from "./helper";

export const getAllUsers = () => {
    return myAxios.get('/admin/allUser').then((response) => response.data)
};
export const generateReport = () => {
    return myAxios.get('/admin/generateReport', {headers: {"Content-Type": "application/vnd.ms-excel","Content-Disposition": "attachment;filename=report.xlsx"}, responseType: 'blob' }).then((response) => response.data)
};