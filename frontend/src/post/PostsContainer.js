import { useApplicationContext } from '../ApplicationContext';
import Post from './Post'
import {useEffect, useState} from 'react'

export default function PostsContainer() {


    const postService = useApplicationContext().postService
    const loginService = useApplicationContext().loginService

    const reloadPosts = (token, user) => {
        const postsPromise = postService.getPosts(loginService.token)
        postsPromise.then(posts => setPosts(posts))
    }

    loginService.addSignInListener("postsContainer", reloadPosts)
    loginService.addSignOutListener("postsContainer", reloadPosts)

    postService.addPostSubmitListener("postsContainer", reloadPosts)
    postService.addDeletePostListener("postsContainer", reloadPosts)

    const [posts, setPosts] = useState(null)
    const [isRequested, setRequested] = useState(false)

    useEffect(() => {
        if (!isRequested) {
            const postsPromise = postService.getPosts(loginService.token)
            postsPromise.then(posts => setPosts(posts))
            setRequested(true)
        }
      }, []);

    const postsElements = posts ? posts.map((post, id) => {
        return <div key={post.date + "_" + post.userName}><Post data={post} /></div>;
    }) : [<div />]
    console.log("Rendering")
    return <div>
            {postsElements.reverse()}
        </div>
}