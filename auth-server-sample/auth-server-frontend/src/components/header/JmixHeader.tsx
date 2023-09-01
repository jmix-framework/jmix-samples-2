import {Layout, Menu} from "antd";
import JmixLogo from "../../assets/logo/logo.svg";
import {Link} from "react-router-dom";
import {LogoutOutlined} from "@ant-design/icons";
import {useContext} from "react";
import {AuthContext, IAuthContext} from "react-oauth2-code-pkce";
import {useLocation} from "react-router";

import {RouteUtils} from "../../utils/RouteUtils.ts";

import "./JmixHeader.css";

const {Header} = Layout;

export function JmixHeader() {
    const {logOut} = useContext<IAuthContext>(AuthContext);

    const {pathname} = useLocation();
    const currentManu = RouteUtils.getBreadcrumb(pathname);

    return (
        <Header
            style={{
                position: 'sticky',
                top: 0,
                zIndex: 1,
                width: '100%',
                display: 'flex',
                alignItems: 'center',
            }}
        >
            <div>
                <img src={JmixLogo} alt="Jmix logo" className="jmix-header__logo"/>
            </div>
            <Menu
                theme="light"
                mode="horizontal"
                defaultSelectedKeys={[currentManu.id]}
                items={[
                    {
                        key: "home",
                        label: <Link to="/home">Home</Link>,
                    },
                    {
                        key: "users",
                        label: <Link to="/users">Users</Link>,
                    }
                ]}
            />
            <div className="jmix-header__logout">
                <LogoutOutlined className="jmix-header__logout-logo" onClick={() => {
                    logOut();
                }}/>
            </div>
        </Header>
    );
}