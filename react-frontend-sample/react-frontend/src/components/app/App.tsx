import {Layout} from 'antd';
import {useContext} from 'react';
import {AuthContext, IAuthContext} from "react-oauth2-code-pkce"
import {SplashScreen} from "../splash/SplashScreen.tsx";
import {DemoHeader} from "../header/DemoHeader.tsx";
import {DemoFooter} from "../footer/DemoFooter.tsx";
import {DemoContent} from "../content/DemoContent.tsx";

import "./App.css";

function App() {
    const {loginInProgress} = useContext<IAuthContext>(AuthContext)

    if (loginInProgress) {
        return <SplashScreen/>
    }

    return (
        <>
            <Layout className="layout">
                <DemoHeader/>
                <DemoContent/>
                <DemoFooter/>
            </Layout>
        </>
    )
}

export default App
