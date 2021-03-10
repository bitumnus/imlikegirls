import axios from 'axios';

export default class LoginApi {
    getDeepLink(firebaseLink) {
        return axios.get(firebaseLink)
            .then(response => {
                return response;
            })
            .catch(e => console.log('~~~getDeepLink_error~~~', e))
    }
}
