import Logo from "../../assets/logo/react.svg";

import "./SplashScreen.css";

export function SplashScreen() {
    return (
        <div className="splash-screen">
            <img
                src={Logo}
                className="splash-screen__logo"
                alt="Logo"
            />
        </div>
    )
}