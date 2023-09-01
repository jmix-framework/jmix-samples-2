import axios from "axios";

export type User = {
    id: string,
    username: string;
    lastName: string;
    firstName: string;
    email: string,
    active: boolean,
}

export const getUsers = (token: string) => {
    return axios.get('http://localhost:8080/rest/entities/ad_User', {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
}

export const getUser = (id: string, token: string) => {
    return axios.get('http://localhost:8080/rest/entities/ad_User/' + id, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
}