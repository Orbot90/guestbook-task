import './Navigation.css';
import Navbar from 'react-bootstrap/Navbar'
import Nav from 'react-bootstrap/Nav'
import Dropdown from 'react-bootstrap/Dropdown'
import ButtonGroup from 'react-bootstrap/ButtonGroup'
import Button from 'react-bootstrap/Button'
import React, { useState } from 'react';
import LoginForm from '../loginform/LoginForm'
import { useApplicationContext } from '../ApplicationContext';
import Modal from 'react-bootstrap/Modal'

export default function Navigation() {

    const loginService = useApplicationContext().loginService;

    const [isLoggedIn, setLoggedIn] = useState(loginService.userName)
    const [userName, setUserName] = useState(loginService.userName)
    const [loginFormDisplay, setLoginFormDisplay] = useState(false)

    loginService.addSignInListener("navigation", (token, user) => {
      setLoggedIn(true)
      setUserName(user.name)
      setLoginFormDisplay(false)
    })
    loginService.addSignOutListener("navigation", () => {
      setLoggedIn(false)
      setUserName(null)
    })

    const signOut = () => {
      loginService.signOut();
    }

  return <div>
              <Modal show={loginFormDisplay}>
                <Modal.Header>
                    <h5 className="modal-title">Sign in</h5>
                    <Button variant="light" onClick={() => setLoginFormDisplay(false)}>Close</Button>
                </Modal.Header>
                <Modal.Body>
                  <LoginForm />
                </Modal.Body>
              </Modal>
      <Navbar bg="light" variant="light" expand="lg">
        <Navbar.Brand href="#home">Guest Book</Navbar.Brand>
        <Nav
          activeKey="/home" className="mr-auto" >
          <Nav.Item>
            <Nav.Link href="/home">Home</Nav.Link>
          </Nav.Item>
          <Nav.Item>
          { isLoggedIn ?
            <div />
            : <Nav.Link onClick={ () => setLoginFormDisplay(true) }>Sign in</Nav.Link>
          }
          </Nav.Item>
        </Nav>
        { isLoggedIn ? 
            <Dropdown as={ButtonGroup}>

            <Dropdown.Toggle variant="outline-dark" id="dropdown-user">
              {userName}
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item>Profile</Dropdown.Item>
            </Dropdown.Menu>
            <Dropdown.Menu>
              <Dropdown.Item onSelect={signOut}>Sign out</Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown> :
            <Button variant="light" disabled={true} >Anonymous</Button>
          }
        
      </Navbar>
    </div>
}
