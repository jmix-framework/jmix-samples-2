import {Layout} from 'antd';
import {useContext} from 'react';
import {AuthContext, IAuthContext} from "react-oauth2-code-pkce"
import {SplashScreen} from "../splash/SplashScreen.tsx";
import {JmixHeader} from "../header/JmixHeader.tsx";
import {JmixFooter} from "../footer/JmixFooter.tsx";
import {JmixContent} from "../content/JmixContent.tsx";

import "./App.css";

function App() {
    const {loginInProgress} = useContext<IAuthContext>(AuthContext)

    if (loginInProgress) {
        return <SplashScreen/>
    }

    return (
        <>
            <Layout className="layout">
                <JmixHeader/>
                <JmixContent/>
                <JmixFooter/>
            </Layout>
        </>
    )
}

export default App
