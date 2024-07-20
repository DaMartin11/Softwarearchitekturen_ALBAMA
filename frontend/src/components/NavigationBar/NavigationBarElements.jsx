import { NavLink as Link } from "react-router-dom";
import styled from "styled-components";

//Style for the navbar
export const Nav = styled.nav`
  background: #FFFFFF;
  display: flex;
  justify-content: space-between;
  height: 60px;
  margin: 0px;
  padding-left: 5vh;
  padding-right: 5vh;
`;

//Style for the navlinks
export const NavLink = styled(Link)`
  color: #2E1E0F;
  display: flex;
  align-items: center;
  text-decoration: none;
  padding: 0 1rem;
  height: 100%;
  cursor: pointer;
  &.active {
    color: #88292f;
    text-decoration: underline dotted;
  }
`;

//
export const NavMenu = styled.div`
  display: flex;
  align-items: center;
  margin-right: -24px;
  /* Second Nav */
  /* margin-right: 24px; */
  /* Third Nav */
  /* width: 100vw;
white-space: nowrap; */
  @media screen and (max-width: 768px) {
    display: none;
  }
`;

