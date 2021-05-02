import { useApplicationContext } from '../ApplicationContext';
import Post from './Post'
import {useEffect, useState} from 'react'

export default function PostsContainer() {


    const postService = useApplicationContext().postService
    const postsPromise = postService.getPosts()

    const [posts, setPosts] = useState([])
    const [isRequested, setRequested] = useState(false)

    useEffect(() => {
        if (!isRequested) {
            postsPromise.then(posts => setPosts(posts))
            setRequested(true)
        }
      });


    const renderPosts = (posts) => {
        
    }

    return <div>
            {
                posts.map(element => {
                    return <Post data={element} />;
                })
            }
        </div>
}