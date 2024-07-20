import './App.css';
import NavigationBar from "./components/NavigationBar";
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Home from './pages';
import Cart from './pages/cart';
import Login from './pages/login';
import Signup from './pages/signup';
import Products from './pages/products';
import jwtDecode from "jwt-decode";
import Profile from "./pages/profile";
import Details from "./pages/details";
import SearchResult from "./pages/searchResult";
import Completion from "./pages/payment/completion";


function App() {
    const token = localStorage.getItem("jwt-token");
    var loggedIn = false
    if (token) {
        var decodedToken = jwtDecode(token)
        var dateNow = new Date();
        if(decodedToken.exp*1000 > dateNow.getTime()) {
            loggedIn = true;
        } else {
            loggedIn = false;
        }
    }

  return (
      <Router>
        <NavigationBar  loggedIn={loggedIn}/>
        <Routes>
          <Route exact path='/'  element={<Home />} />
          <Route path='/signup' element={<Signup/>} />
          <Route path='/login' element={<Login/>} />
          <Route path='/cart' element={<Cart/>} />
          <Route path='/profile' element={<Profile/>} />
          <Route path='/products' element={<Products/>} />
          <Route path='/products/:id' element={<Details/>} />
          <Route path='/products/search/:query' element={<SearchResult/>} />
          <Route path='/completion' element={<Completion/>} />
        </Routes>
      </Router>
  );
}

export default App;
