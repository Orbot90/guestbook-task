import './App.css';
import Navigation from './navigation/Navigation';
import Container from 'react-bootstrap/Container'
import PostEditor from './editor/PostEditor'
import Post from './post/Post'
import { ApplicationProvider } from './ApplicationContext';

function App() {
  return (
    <div className="App">
      <ApplicationProvider>
        <Container className="justify-content-md-center">
            <Navigation />
          <PostEditor />
          <Post />
        </Container>
      </ApplicationProvider>
    </div>
  );
}

export default App;
