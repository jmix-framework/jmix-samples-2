import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './components/app/App.tsx'
import './index.css'

import {AuthProvider, TAuthConfig, TRefreshTokenExpiredEvent} from "react-oauth2-code-pkce"
import {BrowserRouter} from "react-router-dom";

const authConfig: TAuthConfig = {
    clientId: 'client',
    authorizationEndpoint: 'http://localhost:8080/oauth2/authorize',
    tokenEndpoint: 'http://localhost:8080/oauth2/token',
    redirectUri: 'http://localhost:5173/',
    decodeToken: false,
    onRefreshTokenExpire: (event: TRefreshTokenExpiredEvent) => window.confirm('Session expired. Refresh page to continue using the site?') && event.login(),
}

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
    <AuthProvider authConfig={authConfig}>
        <React.StrictMode>
            <BrowserRouter>
                <App/>
            </BrowserRouter>
        </React.StrictMode>
    </AuthProvider>,
)
