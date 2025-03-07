import React from 'react'

function Landing() {
    return (
        <div>
            <div className="container text-center mt-5">
                <div className="jumbotron">
                    <h1 className="display-4">Welcome to the Platform!</h1>
                    <p className="lead">Empowering you to achieve your goals with our amazing tools and resources.</p>
                    <hr className="my-4" />
                    <p>Join us and start your journey today.</p>
                    <div className="d-flex justify-content-center">
                        <a href="/register" className="btn btn-dark btn-lg mx-2" role="button">Register</a>
                        <a href="/login" className="btn btn-dark btn-lg mx-2" role="button">Login</a>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Landing
