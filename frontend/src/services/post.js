import {getApplicationProperty} from '../properties/applicationProperties'

class PostService {

    constructor() {
        this.postSubmitListeners = {};
    }

    addPostSubmitListener(name, listener) {
        this.postSubmitListeners[name] = listener
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
                        (this.postSubmitListeners[key])()
                    }
                }
            }
            editor.disabled = false;
        })
    }

    async getPosts(onLoad) {
        const requestOptions = {
                mode: 'cors',
                method: 'GET',
                credentials: 'include' 
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