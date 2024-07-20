import React, { useState } from 'react';
import styled from 'styled-components';

const SignupContainer = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #f0f2f5;
`;

const FormContainer = styled.div`
    background: white;
    padding: 40px;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
`;

const Form = styled.form`
    display: flex;
    flex-direction: column;
`;

const FormHeading = styled.h1`
    margin-bottom: 20px;
`;

const Label = styled.label`
    margin-bottom: 5px;
`;

const Input = styled.input`
    padding: 10px;
    margin-bottom: 15px;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 16px;
`;

const Button = styled.button`
    padding: 10px 15px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;

    &:disabled {
        background-color: #cccccc;
        cursor: not-allowed;
    }
`;

const ErrorMessage = styled.div`
    background-color: red;
    color: white;
    padding: 10px;
    border-radius: 5px;
    margin-top: 10px;
`;

const Signup = () => {
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        address: 'Teststreet 12',
        country: 'Germany',
        city: 'Berlin',
        zipCode: '12345'
    });

    const [repeatPassword, setRepeatPassword] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const [formValid, setFormValid] = useState(false);

    const handleInputChange = (event) => {
        setFormData({
            ...formData,
            [event.target.name]: event.target.value
        });
        checkFormValidity();
    };

    const handleInputChangeRepeatPassword = (event) => {
        setRepeatPassword(event.target.value);
        checkFormValidity();
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        const userBackend = process.env.REACT_APP_BACKEND_USER_API;

        fetch(userBackend + '/api/v1/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
            .then(response => {
                if (!response.ok) {
                    setErrorMessage("Error: Registration could not be completed.");
                }
                return response.json();
            })
            .then(data => {
                const jwtToken = data.token;
                localStorage.setItem("jwt-token", jwtToken);
                window.location.href = "/";
            })
            .catch(error => {
                console.error('There was a problem with the form submission:', error);
                setErrorMessage("Error: Registration could not be completed.");
            });
    };

    const checkFormValidity = () => {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(formData.email)) {
            setFormValid(false);
        } else if (formData.password === '' || formData.password.length < 6) {
            setFormValid(false);
        } else if (repeatPassword === '' || formData.password !== repeatPassword) {
            setFormValid(false);
        } else if (formData.firstName === '' || formData.lastName === '') {
            setFormValid(false);
        } else {
            setFormValid(true);
        }
    };

    return (
        <SignupContainer>
            <FormContainer>
                <Form onSubmit={handleSubmit}>
                    {errorMessage && <ErrorMessage>{errorMessage}</ErrorMessage>}
                    <FormHeading>Sign Up</FormHeading>
                    <Label>Email address</Label>
                    <Input
                        type="email"
                        placeholder="Enter your Email"
                        name="email"
                        value={formData.email}
                        onChange={handleInputChange}
                    />
                    <Label>First Name</Label>
                    <Input
                        placeholder="Enter your First Name"
                        name="firstName"
                        value={formData.firstName}
                        onChange={handleInputChange}
                    />
                    <Label>Last Name</Label>
                    <Input
                        placeholder="Enter your Last Name"
                        name="lastName"
                        value={formData.lastName}
                        onChange={handleInputChange}
                    />
                    <Label>Password</Label>
                    <Input
                        type="password"
                        placeholder="Enter your Password"
                        name="password"
                        value={formData.password}
                        onChange={handleInputChange}
                    />
                    <Label>Repeat your Password</Label>
                    <Input
                        type="password"
                        placeholder="Repeat your Password"
                        name="repeatPassword"
                        value={repeatPassword}
                        onChange={handleInputChangeRepeatPassword}
                    />
                    <Button type="submit" disabled={!formValid}>Sign Up</Button>
                </Form>
            </FormContainer>
        </SignupContainer>
    );
};

export default Signup;
