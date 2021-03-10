import React, { useRef, useEffect, useState } from 'react';
import { BackHandler , SafeAreaView } from 'react-native';
import {styles} from "./Styles";
import { Container } from 'native-base';
import { WebView } from 'react-native-webview';
import LoginApi from "../api/LoginApi";

export default ({deep}) => {
    const webview = useRef('webview');
    const [url, setUrl] = useState(null);

    const goback = () => {
        webview.current.goBack();
        return true;
    };

    const getUrlAsync = async () => {
        // Get the deep link used to open the app
        const firebaseURL = `https://${deep}`;
        new LoginApi().getDeepLink(firebaseURL)
            .then(link => {
                if (link?.status === 200) {
                    setUrl(link?.request?.responseURL);
                }
            })
    };

    useEffect(() => {
        getUrlAsync();
        BackHandler.addEventListener('hardwareBackPress', goback);
        return () => {
            BackHandler.removeEventListener('hardwareBackPress', goback);
        }
    }, [])
    return (
        <Container style={styles.page_wrap}>
            <SafeAreaView style={{ flex: 1 }}>
                {url && <WebView
                    source={{uri: url}}
                    ref={webview}
                    // onNavigationStateChange={(navState) => onNavigationStateChange(navState)}
                    javaScriptEnabled={true}
                    domStorageEnabled={true}
                    setPluginsEnabled={true}
                    allowsInlineMediaPlayback={true}
                    originWhitelist={['*']}
                    javaScriptEnabledAndroid={true}
                    style={{marginTop: 0}}
                />}
            </SafeAreaView>
            {/* <View>
                <Button onPress={goback} mode='contained' color='#99001c'><Text>Go back</Text></Button>
            </View> */}
        </Container>
    );
}