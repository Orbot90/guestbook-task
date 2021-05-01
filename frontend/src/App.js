import './App.css';
import Navigation from './navigation/Navigation';
import Container from 'react-bootstrap/Container'
import PostEditor from './editor/PostEditor'

function App() {
  return (
    <div className="App">
      <Container className="justify-content-md-center">
          <Navigation isLoggedIn={false} userName="Vasya Pupkin" />
        <PostEditor />
      </Container>
    </div>
  );
}

export default App;
