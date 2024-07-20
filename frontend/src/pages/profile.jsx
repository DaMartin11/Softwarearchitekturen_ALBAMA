import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import jwtDecode from "jwt-decode";
import CartContainer from "../components/cart/CartContainer";

const Profile = () => {
    const [loggedIn, setLogin] = useState(false);
    const [userId, setUserId] = useState("");
    const [token, setToken] = useState(null);

    const checkLoginStatus = async () => {
        if (localStorage.getItem("jwt-token") === null) {
            setLogin(false);
            window.location.replace(process.env.BASE_URL + "/login");
        } else {
            const token = localStorage.getItem("jwt-token");
            var decodedToken = jwtDecode(token)
            var dateNow = new Date();

            if (decodedToken.exp * 1000 < dateNow.getTime()) {
                localStorage.removeItem("jwt-token");
                setLogin(false); // Update login status
            } else {
                setLogin(true); // Update login status
                setUserId(decodedToken.sub);
            }
            return decodedToken;
        }
    };



    const fetchData = async () => {
        const decodedToken = await checkLoginStatus();
        if (decodedToken != null) {
            setToken(decodedToken);
        }
    };

    useEffect(() => {
        fetchData();
    }, []);

    if (token !== null) {
        return (
            <div className="content">
                <div className="profile-form">
                    <h1>Profile</h1>
                    <h4>Name:</h4>
                    <p>{token.firstName} {token.lastName}</p>
                    <h4>Email:</h4>
                    <p>{token.sub}</p>
                    <h4>Address:</h4>
                    <p>{token.address}</p>
                    <p>{token.zipCode} {token.city}</p>
                    <p>{token.country}</p>
                </div>

            </div>
        );
    }

};

export default Profile;