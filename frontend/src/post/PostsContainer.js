import { useApplicationContext } from '../ApplicationContext';
import Post from './Post'
import {useEffect, useState} from 'react'

export default function PostsContainer() {


    const postService = useApplicationContext().postService
    const loginService = useApplicationContext().loginService

    const reloadPosts = (token, user) => {
        postService.getPosts(token)
            .then((posts) => setPosts(posts))
    }

    loginService.addSignInListener(reloadPosts)
    loginService.addSignOutListener(reloadPosts)

    postService.addPostSubmitListener("postsContainer", reloadPosts)
    postService.addDeletePostListener("postsContainer", reloadPosts)
    const postsPromise = postService.getPosts(loginService.token)

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