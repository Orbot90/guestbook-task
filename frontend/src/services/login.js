import {getApplicationProperty} from '../properties/applicationProperties'

class LoginService {
    constructor() {
        this.signInListeners = []
        this.signOutListeners = []

        this.token = null
        this.userName = null
        this.roles = []
    }

    addSignInListener(listener) {
        this.signInListeners.push(listener)
    }

    addSignOutListener(listener) {
        this.signOutListeners.push(listener)
    }

    signIn(userName, password) {
        var details = {
            'username': userName,
            'password': password
        };
        
        var formBody = [];
        for (var key in details) {
            if (details.hasOwnProperty(key)) {
                var encodedKey = encodeURIComponent(key);
                var encodedValue = encodeURIComponent(details[key]);
                formBody.push(encodedKey + "=" + encodedValue);
            }
        }
        formBody = formBody.join("&");
        
        fetch(getApplicationProperty('applicationHost') + '/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
          },
          body: formBody
        }).then(response => {
            if (response.status == 200) {
                response.json().then(result => {
                    this.userName = result.name
                    this.roles = result.roles
                    this.token = result.token

                    const user = {
                        name: this.userName
                    }
                    this.signInListeners.forEach(listener => {
                        listener(user)
                    });
                })
            } else {
                alert("Unknown user name or password");
            }
        })
    }

    signOut() {
        this.token = ""
        this.userName = ""
        this.signOutListeners.forEach(listener => {
            listener()
        })
    }
}

export default LoginService;