import './App.css';
import Navigation from './navigation/Navigation';
import Container from 'react-bootstrap/Container'
import PostEditor from './editor/PostEditor'
import Post from './post/Post'

function App() {
  return (
    <div className="App">
      <Container className="justify-content-md-center">
          <Navigation isLoggedIn={false} userName="Vasya Pupkin" />
        <PostEditor />
        <Post />
      </Container>
    </div>
  );
}

export default App;
