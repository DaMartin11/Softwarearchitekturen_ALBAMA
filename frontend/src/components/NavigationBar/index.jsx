import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { NavLink as Link } from "react-router-dom";
import { BsFillCartFill } from "react-icons/bs";
import SearchBar from "../elements/searchBar";

const Nav = styled.nav`
    background: #fff;
    height: 80px;
    display: flex;
    justify-content: space-between;
    padding: 0.5rem calc((100vw - 1000px) / 2);
    z-index: 10;
`;

const NavLink = styled(Link)`
    color: #000;
    display: flex;
    align-items: center;
    text-decoration: none;
    padding: 0 1rem;
    height: 100%;
    cursor: pointer;

    &.active {
        color: #15cdfc;
    }

    &:hover {
        color: #ff6347;
    }
`;

const NavMenu = styled.div`
    display: flex;
    align-items: center;
    margin-right: -24px;

    @media screen and (max-width: 768px) {
        display: none;
    }
`;

const NavHeading = styled.h1`
    font-size: 2rem;
    color: #ff6347;
`;

const NavigationBar = ({ loggedIn }) => {
    const [isLoggedOut, setIsLoggedOut] = useState(!loggedIn);

    useEffect(() => {
        setIsLoggedOut(!loggedIn);
    }, [loggedIn]);

    const handleLogout = () => {
        localStorage.removeItem("jwt-token");
        setIsLoggedOut(true);
    };

    return (
        <Nav>
            <NavLink to="/">
                <NavHeading id="homeLink">{isLoggedOut ? "ALBAMA" : "ALBAMA"}</NavHeading>
            </NavLink>
            <NavMenu>
                <NavLink to="/products" activeClassName="active">
                    Products
                </NavLink>
                {!isLoggedOut && (
                    <NavLink to="/profile" activeClassName="active">
                        Profile
                    </NavLink>
                )}
                {isLoggedOut ? (
                    <>
                        <NavLink to="/login" activeClassName="active">
                            Login
                        </NavLink>
                        <NavLink to="/signup" activeClassName="active">
                            Sign Up
                        </NavLink>
                    </>
                ) : (
                    <NavLink as="div" onClick={handleLogout}>
                        Log Out
                    </NavLink>
                )}
                <SearchBar />
                <NavLink to="/cart" activeClassName="active">
                    <BsFillCartFill />
                </NavLink>
            </NavMenu>
        </Nav>
    );
};

export default NavigationBar;
