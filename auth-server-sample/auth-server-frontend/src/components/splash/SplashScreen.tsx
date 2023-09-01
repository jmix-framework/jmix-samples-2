import JmixLogo from "../../assets/logo/logo.svg";

import "./SplashScreen.css";

export function SplashScreen() {
    return (
        <div className="splash-screen">
            <img
                src={JmixLogo}
                className="splash-screen__logo"
                alt="Jmix Logo"
            />
        </div>
    )
}