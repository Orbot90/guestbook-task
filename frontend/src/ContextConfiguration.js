import LoginService from './services/login'
import PostService from './services/post'

export const prepareContext = () => {
    const loginService = new LoginService()
    const postService = new PostService()

    return {
        loginService: loginService,
        postService: postService
    }
}