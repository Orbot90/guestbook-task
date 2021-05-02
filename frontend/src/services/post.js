import {getApplicationProperty} from '../properties/applicationProperties'

class PostService {

    submitPost(editor, username) {
        const data = editor.getData()
        const requestOptions = {
            mode: 'cors',
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ data: data, userName: username})
        };
        editor.disabled = true;
        fetch(getApplicationProperty('applicationHost') + '/post', requestOptions)
            .then(response => {
                if (!response || response.error){
                alert("Could not send post");
            } else {
                editor.setData("")
                // update all posts when the posts display implemented
            }
            editor.disabled = false;
        })
    }
}

export default PostService;