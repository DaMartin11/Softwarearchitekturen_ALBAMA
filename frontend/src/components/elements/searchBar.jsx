import React, {useState} from 'react'


const SearchBar = () => {

    const [searchInput, setSearchInput] = useState("");

    const handleChange = (e) => {
        e.preventDefault();
        setSearchInput(e.target.value);
    };

    const search = () => {
        if (searchInput.length > 0) {
            window.location.href = `http://localhost:3000/products/search/${searchInput}`;
        }
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        search();
    }

    const handleKeyDown = (e) => {
        if (e.key === 'Enter') {
            e.preventDefault();
            search();
        }
    }

    return <div>
        <input
            className={"searchbar"}
            type="search"
            placeholder="Search here..."
            onChange={handleChange}
            onKeyDown={handleKeyDown}
            onSubmit={handleSubmit}
            value={searchInput} />
    </div>


};

export default SearchBar;