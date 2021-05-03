import {getApplicationProperty} from '../properties/applicationProperties'

class PostService {

    constructor() {
        this.postSubmitListeners = {};
        this.deletePostListeners = {};
    }

    addPostSubmitListener(name, listener) {
        this.postSubmitListeners[name] = listener
    }

    addDeletePostListener(name, listener) {
        this.deletePostListeners[name] = listener
    }


    approvePost(post, token, onComplete) {
        post.approved = true
        const requestOptions = {
            mode: 'cors',
            method: 'PUT',
            headers: { 'Content-Type': 'application/json',
                        'Authorization': token},
            body: JSON.stringify(post)
        };
        fetch(getApplicationProperty('applicationHost') + '/post/' + post.id, requestOptions)
            .then(response => {
                if (!response || response.error){
                alert("Could not update post");
            } else {
                if (onComplete) {
                    onComplete()
                }
            }
        })
    }

    deletePost(id, token, onComplete) {
        const requestOptions = {
            mode: 'cors',
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json',
                        'Authorization': token}
        };
        fetch(getApplicationProperty('applicationHost') + '/post/' + id, requestOptions)
            .then(response => {
                if (!response || response.error){
                alert("Could not delete post");
            } else {
                for (var key in this.deletePostListeners) {
                    if (this.deletePostListeners.hasOwnProperty(key)) {
                        (this.deletePostListeners[key])(token)
                    }
                }
                if (onComplete) {
                    onComplete()
                }
            }
        })
    }

    editPost(editor, post, token, onSuccess) {
        const data = editor.getData()
        const requestOptions = {
            mode: 'cors',
            method: 'PUT',
            headers: { 'Content-Type': 'application/json',
                        'Authorization': token},
            body: JSON.stringify({ data: data, approved: post.approved})
        };
        editor.disabled = true;
        fetch(getApplicationProperty('applicationHost') + '/post/' + post.id, requestOptions)
            .then(response => {
                if (!response || response.error){
                alert("Could not update post");
            } else {
                if (onSuccess) {
                    onSuccess()
                }
                for (var key in this.postSubmitListeners) {
                    if (this.postSubmitListeners.hasOwnProperty(key)) {
                        (this.postSubmitListeners[key])(token)
                    }
                }
            }
            editor.disabled = false;
        })
    }

    submitPost(editor, username, token) {
        const data = editor.getData()
        const requestOptions = {
            mode: 'cors',
            method: 'POST',
            headers: { 'Content-Type': 'application/json',
                        'Authorization': token},
            body: JSON.stringify({ data: data, userName: username})
        };
        editor.disabled = true;
        fetch(getApplicationProperty('applicationHost') + '/post', requestOptions)
            .then(response => {
                if (!response || response.error){
                alert("Could not send post");
            } else {
                editor.setData("")
                for (var key in this.postSubmitListeners) {
                    if (this.postSubmitListeners.hasOwnProperty(key)) {
                        (this.postSubmitListeners[key])(token)
                    }
                }
            }
            editor.disabled = false;
        })
    }

    async getPosts(token) {
        const requestOptions = {
                mode: 'cors',
                method: 'GET',
                headers: {
                        'Authorization': token
                }
            };

        const response = await fetch(getApplicationProperty('applicationHost') + '/post', requestOptions)
        if (response.status == 200) {
            return await response.json()
        } else {
            return null
        }
    }
}

export default PostService;