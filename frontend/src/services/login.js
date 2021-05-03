import {getApplicationProperty} from '../properties/applicationProperties'

class LoginService {
    constructor() {
        this.signInListeners = {}
        this.signOutListeners = {}

        this.token = localStorage.getItem("jwt")
        this.userName = localStorage.getItem("userName")
        this.roles = localStorage.getItem("roles")

    }

    addSignInListener(key, listener) {
        if (!listener) {
            throw 'Listener must not be null'
        }
        this.signInListeners[key] = listener
    }

    addSignOutListener(key, listener) {
        if (!listener) {
            throw 'Listener must not be null'
        }
        this.signOutListeners[key] = listener
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
          credentials: 'include',
          body: formBody
        }).then(response => {
            if (response.status == 200) {
                response.json().then(result => {
                    this.userName = result.name
                    this.roles = result.roles
                    this.token = result.token

                    localStorage.setItem("jwt", result.token)
                    localStorage.setItem("userName", result.name)
                    localStorage.setItem("roles", result.roles)

                    const user = {
                        name: this.userName
                    }

                    for (var key in this.signInListeners) {
                        if (this.signInListeners.hasOwnProperty(key)) {
                            (this.signInListeners[key])(result.token, user)
                        }
                    }
                })
            } else {
                alert("Unknown user name or password");
            }
        })
    }

    signOut() {
        this.token = ""
        this.userName = ""
        this.roles = []

        localStorage.removeItem("jwt")
        localStorage.removeItem("userName")
        localStorage.removeItem("roles")
        for (var key in this.signOutListeners) {
            if (this.signOutListeners.hasOwnProperty(key)) {
                (this.signOutListeners[key])()
            }
        }
    }
}

export default LoginService;