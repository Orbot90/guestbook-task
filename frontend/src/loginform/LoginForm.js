import React, { useState } from 'react'
import { Button } from 'react-bootstrap';
import { useApplicationContext } from '../ApplicationContext';

export default function LoginForm() {

    const loginService = useApplicationContext().loginService;
    const [userName, setUsername] = useState("")
    const [password, setPassword] = useState("")

    const login = () => {
        loginService.signIn(userName, password)
    }

    return <div class="col-md-6 login-form-1">
            <form>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Username" 
                        onChange={event => setUsername(event.target.value)} />
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="Password" 
                        onChange={event => setPassword(event.target.value)} />
                </div>
                <div class="form-group">
                    <Button className="btnSubmit" onClick={login}>Login</Button>
                </div>
            </form>
        </div>
}