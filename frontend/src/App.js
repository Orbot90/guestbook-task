import logo from './logo.svg';
import './App.css';
import Navigation from './navigation/Navigation';
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'

function App() {
  return (
    <div className="App">
      <Container className="justify-content-md-center">
        <Navigation isLoggedIn={false} userName="Vasya Pupkin" />
        
      </Container>
    </div>
  );
}

export default App;
