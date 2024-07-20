import products from "./products";
import ProductList from "../components/Products/ProductList";
import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import withRouter from "../withRouter";
import axios from "axios";
import Loader from "../components/elements/loader";

const SearchResult =  () => {
    const [products, setProducts] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const { query } = useParams();

    const getSearchResult = () => {
        axios
            .get(`http://localhost:8085/api/v1/products/search/${query}`)
            .then((response) => {
                setProducts(response.data);
                setIsLoading(false);
            })
            .catch((error) => {
                console.log('Error: product not found.')
                setIsLoading(false);
            })
    }

    useEffect(() => {
        getSearchResult();
    }, [query]);

    return (
        <div>
            <div style={{display: isLoading ? 'flex' : 'none'}}>
                <Loader />
            </div>
            {products && !isLoading && <ProductList products={products}/> }
            {products.length === 0 && !isLoading &&
                <div className="emptySearchResultContainer">
                    <h2>Search Result for "{query}"</h2>
                    <p className="errorMsg">No matching products found.</p>
                    <a href="http://localhost:3000/products"><button>Back to products</button></a>
                </div>
            }
        </div>



    );

}
export default withRouter(SearchResult);