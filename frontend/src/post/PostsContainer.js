import { useApplicationContext } from '../ApplicationContext';
import Post from './Post'
import {useEffect, useState} from 'react'

export default function PostsContainer() {


    const postService = useApplicationContext().postService
    postService.addPostSubmitListener("postsContainer", () => {
        console.log("Inside listener")
        postService.getPosts()
            .then((posts) => setPosts(posts))
    })
    const postsPromise = postService.getPosts()

    const [posts, setPosts] = useState(null)
    const [isRequested, setRequested] = useState(false)

    useEffect(() => {
        if (!isRequested) {
            postsPromise.then(posts => setPosts(posts))
            setRequested(true)
        }
      }, []);

    const postsElements = posts ? posts.map((element, id) => {
        return <div key={id}><Post data={element} /></div>;
    }) : [<div />]
    console.log("Rendering")
    return <div>
            {postsElements.reverse()}
        </div>
}