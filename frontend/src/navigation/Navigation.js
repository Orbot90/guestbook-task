import './Navigation.css';
import Navbar from 'react-bootstrap/Navbar'
import Nav from 'react-bootstrap/Nav'
import Dropdown from 'react-bootstrap/Dropdown'
import ButtonGroup from 'react-bootstrap/ButtonGroup'
import Button from 'react-bootstrap/Button'
import React, { Component } from 'react';

class Navigation extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      isLoggedIn: true,
      userName: "Vasya Pupkin"
    }

    this.signIn = this.signIn.bind(this)
    this.signOut = this.signOut.bind(this)
  }

  
  render() {
    return <div>
      <Navbar bg="light" variant="light" expand="lg">
        <Navbar.Brand href="#home">Guest Book</Navbar.Brand>
        <Nav
          activeKey="/home"
          onSelect={(selectedKey) => alert(`selected ${selectedKey}`)} className="mr-auto" >
          <Nav.Item>
            <Nav.Link href="/home">Home</Nav.Link>
          </Nav.Item>
          <Nav.Item>
          { this.state.isLoggedIn ?
            <div />
            : <Nav.Link onClick={this.signIn}>Sign in</Nav.Link>
          }
          </Nav.Item>
        </Nav>
        { this.state.isLoggedIn ? 
            <Dropdown as={ButtonGroup}>

            <Dropdown.Toggle variant="outline-dark" id="dropdown-user">
              {this.state.userName}
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item onSelect={this.signOut}>Sign out</Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown> :
            <Button variant="light" disabled={true} >Anonymous</Button>
          }
        
      </Navbar>
    </div>
  }

  signOut() {
    this.setState({
      isLoggedIn: false
    })
  }

  signIn() {
    this.setState({
      isLoggedIn: true,
      userName: "Vasya Pupkin"
    })
  }
}

export default Navigation;
