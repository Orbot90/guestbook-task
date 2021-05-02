import './App.css';
import Navigation from './navigation/Navigation';
import Container from 'react-bootstrap/Container'
import PostEditor from './editor/PostEditor'
import { ApplicationProvider } from './ApplicationContext';
import PostsContainer from './post/PostsContainer';

function App() {

  return (
    <div className="App">
      <ApplicationProvider>
        <Container className="justify-content-md-center">
            <Navigation />
          <PostEditor />
          <PostsContainer />
        </Container>
      </ApplicationProvider>
    </div>
  );
}

export default App;
